<template>
    <div class="container">
        <div class="row">
            <form class="col-6" v-on:submit.prevent="registerSubmit" accept-charset="UTF-8" method="post">
                <div class="form-group">
                    <label for="inputName">Name</label>
                    <input type="text" class="form-control" id="inputName" placeholder="Enter clinic name" v-model="name">
                </div>
                <div class="form-group">
                    <label for="inputAddress">Address</label>
                    <input type="text" class="form-control" id="inputAddress" placeholder="Enter clinic adress" v-model="address">
                </div>
                <div class="form-group">
                    <label for="inputDescription">Description</label>
                    <textarea id="inputDescription" class="form-control" rows="3" v-model="description"></textarea>
                </div>
                <div v-if="types" class="form-group">
                    <label for="selectType">Types</label>
                    <select class="custom-select" v-model="clinicTypes" id="selectType" multiple>
                        <option v-for="t in types" :key="t.id" :value="t">{{t.typeName}}</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary mb-2">Register</button>
            </form>
        </div>
    </div>
</template>

<script>
    export default {
        name: "AddClinic",
        data : function () {
            return {
                name : undefined,
                address : undefined,
                description : undefined,
                types: null,
                clinicTypes: []
            }
        },
        mounted: function() {
            this.axios.get("http://localhost:8080/procedure/type/all")
                .then(response => {
                    this.types = response.data;
                })
        },
        methods : {
            registerSubmit : function () {
                var clinic = {
                    "name" : this.name,
                    "address" : this.address,
                    "description" : this.description,
                    "types": this.clinicTypes
                }

               this.axios.post('http://localhost:8080/center/admin/register/clinic', clinic, {headers : {
                   "Authorization" : localStorage.getItem('auth')
                   }})
                    .then(response => {
                        this.name = "";
                        this.address = "";
                        this.description = ""
                        this.clinicTypes = []
                        alert(response.data.name + " klinika uspjesno dodata!")
                    })
                   .catch(error => {alert(error.response.data)})
            }
        }
    }
</script>

<style scoped>

</style>