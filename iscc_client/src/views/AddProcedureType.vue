<template>
    <div class="container">
        <h4>New Procedure Type</h4>
        <form v-on:submit.prevent="addProcedureType" accept-charset="UTF-8" method="post">
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
                    </div>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Add</button>
        </form>
    </div>
</template>

<script>
    import axios from "axios"

    export default {
        name: "AddProcedureType",
        data: function(){
            return{
                typeName: undefined,
                typeDescription: undefined,
                price: undefined
            }
        },
        computed: {
            valName: function(){
                if(this.typeName === '') 
                    return 'This field is required!';
                else
                    return null;
            },
            valDescription: function(){
                if(this.typeDescription === '')
                    return 'This field is required!';
                else
                    return null;
            }
        },
        methods: {
            addProcedureType: function(){
                var procedureType = {
                    "typeName": this.typeName,
                    "typeDescription": this.typeDescription,
                    "price": this.price
                }

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
                        || this.typeDescription === undefined || this.typeDescription === ''
                        || this.price === '' || this.price < 0){
                    valid = false
                }

                if (valid){
                    this.axios.post("http://localhost:8080/procedure/type/add", procedureType)
                }
            }
        }
    }
</script>

<style scoped>
</style>