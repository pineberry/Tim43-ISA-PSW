<template>
    <div class="container">
        <h4>Procedure types</h4>
        <form @submit.prevent="searchTypes" accept-charset="UTF-8" method="get">
            <div class="row">
                <div class="col-6">
                    <div class="form-group">
                        <label for="inputName">Room name:</label>
                        <input id="inputName" type="text" class="form-control" placeholder="Enter type name" v-model="typeName">
                    </div>
                    <button type="submit" class="btn btn-primary">Search</button>
                </div>
            </div>
        </form>

        <div v-if="procedureTypes" class="marginTop">
            <table class="table">
                <thead class="thead-dark">
                <th scope="col">#</th>
                <th scope="col">Type name</th>
                <th scope="col">Type description</th>
                <th scope="col">Edit</th>
                <th scope="col">Delete</th>
                </thead>
                <tbody>
                <tr v-for="type in procedureTypes" :key="type.id">
                    <td>{{procedureTypes.indexOf(type) + 1}}</td>
                    <td>{{type.typeName}}</td>
                    <td>{{type.typeDescription}}</td>
                    <td><router-link class="btn btn-primary" :to="{path: '/editType',query: {typeId: type.id}}">Edit</router-link></td>
                    <td><button class="btn btn-warning" v-on:click="deleteType(type)">Delete</button></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
    import axios from "axios"
    export default {
        name: "searchProcedureTypes",
        data: function() {
            return {
                procedureTypes: null,
                typeName: undefined
            }
        },
        mounted: function(){
            this.axios.get("http://localhost:8080/procedure/type/all")
                .then(response => {
                    this.procedureTypes = response.data;
                })
        },
        methods: {
            searchTypes: function(){
                this.axios.get("http://localhost:8080/procedure/type/" + this.typeName)
                    .then(response => {
                        this.procedureTypes = response.data;
                    })
            },
            deleteType: function(type){
                this.axios.delete("http://localhost:8080/procedure/type/" + type.id)
                    .then(response => {
                        var index = this.procedureTypes.indexOf(type)
                        this.procedureTypes.splice(index, 1)
                    })
            }
        }
    }
</script>

<style scoped>
    .marginTop {
        margin-top: 10px;
    }
</style>