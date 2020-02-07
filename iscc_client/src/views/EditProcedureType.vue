<template>
    <div class="container">
        <h4>Edit Procedure Type</h4>
        <form v-if="procedureType != null" @submit.prevent="editProcedureType" accept-charset="UTF-8" method="post">
            <div class="row">
                <div class="col-6">
                    <div class="form-group">
                        <label for="inputName">Type name:</label>
                        <input id="inputName" type="text" class="form-control" placeholder="Enter type name" v-model="typeName">
                    </div>
                    <div class="form-group">
                        <label for="inputDescription">Type description:</label>
                        <textarea id="inputDescription" rows="3" class="form-control" placeholder="Enter type description" v-model="typeDescription"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="inputPrice">Price:</label>
                        <input id="inputPrice" type="number" class="form-control" v-model="price">
                        <span class="val">{{valPrice}}</span>
                    </div>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Edit</button>
        </form>
    </div>
</template>

<script>
    export default {
        name: "editProcedureType",
        data: function () {
            return {
                typeName: undefined,
                typeDescription: undefined,
                price: undefined,
                procedureType: null
            }
        },
        mounted: function () {
            this.axios.get("http://localhost:8080/procedure/type/" + this.$route.query.typeId)
                .then(response => {
                    this.procedureType = response.data;
                    this.typeName = this.procedureType.typeName;
                    this.typeDescription = this.procedureType.typeDescription;
                    this.price = this.procedureType.price;
                })
        },
        computed: {
            valName: function () {
                if (this.typeName === '')
                    return 'This field is required!';
                else
                    return null;
            },
            valDescription: function () {
                if (this.typeDescription === '')
                    return 'This field is required!';
                else
                    return null;
            },
            valPrice: function () {
                if (this.price != undefined && this.price != '' && this.price < 0)
                    return 'Price has to be positive number';
                else
                    return null;
            },
            methods: {
                editProcedureType: function () {

                    this.procedureType.typeName = this.typeName;
                    this.procedureType.typeDescription = this.typeDescription;
                    this.procedureType.price = this.price;

                    if (this.typeName != undefined)
                        this.typeName.trim();
                    else
                        this.typeName = '';

                    if (this.typeDescription != undefined)
                        this.typeDescription.trim();
                    else
                        this.typeDescription = '';

                    if (this.typeName === '' || this.typeDescription === '' || this.price === '') {
                        alert('All fields should be filled!');
                        return;
                    }

                    if (this.price != undefined && this.price < 0) {
                        return;
                    }

                    this.axios.put("http://localhost:8080/procedure/type/update", this.procedureType)
                        .then(response => {
                            this.$router.go(-1);
                        })
                }
            }
        }
    }
</script>

<style scoped>
    .val {
        color: darkred;
    }
</style>