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
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Edit</button>
        </form>
    </div>
</template>

<script>
    export default {
        name: "editProcedureType",
        data: function() {
            return {
                typeName: undefined,
                typeDescription: undefined,
                procedureType: null
            }
        },
        mounted: function(){
            this.axios.get("http://localhost:8080/procedure/type/" + this.$route.query.typeId)
                .then(response => {
                    this.procedureType = response.data;
                    this.typeName = this.procedureType.typeName;
                    this.typeDescription = this.procedureType.typeDescription;
                })
        },
        computed: {
            valName: function() {
                if (this.typeName === '')
                    return 'This field is required!';
                else
                    return null;
            },
            valDescription: function() {
                if (this.typeDescription === '')
                    return 'This field is required!';
                else
                    return null;
            }
        },
        methods: {
            editProcedureType: function() {

                this.procedureType.typeName = this.typeName;
                this.procedureType.typeDescription = this.typeDescription;

                var valid = true;

                if (this.typeName != undefined)
                    this.typeName.trim();
                else
                    this.typeName = '';

                if (this.typeDescription != undefined)
                    this.typeDescription.trim();
                else
                    this.typeDescription = '';

                if (this.typeName === undefined || this.typeName === ''
                    || this.typeDescription === undefined || this.typeDescription === '') {
                    valid = false
                }

                if (valid) {
                    this.axios.put("http://localhost:8080/procedure/type/update", this.procedureType)
                }
            }
        }
    }
</script>

<style scoped>

</style>