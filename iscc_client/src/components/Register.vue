<template>
	 <div class="container">
        <div class="row">
            <form class="col-6" v-on:submit.prevent="submit" accept-charset="UTF-8" method="post">
                <div class="form-group">
                    <label for="firstName">First Name</label>
                    <input type="text" class="form-control" id="firstName" v-model="firstName">
                </div>
                <div class="form-group">
                    <label for="lastName">Last name</label>
                    <input type="text" class="form-control" id="lastName" v-model="lastName">
                </div>
                <div class="form-group">
                    <label for="email">Email address</label>
                    <input type="text" class="form-control" id="email" v-model="email">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" v-model="password">
                </div>
                <div class="form-group">
                    <label for="passwordF">Repeat password</label>
                    <input type="password" class="form-control" id="passwordF" v-model="passwordF">
                </div>
                <div class="form-group">
                    <label for="address">Address</label>
                    <input type="text" class="form-control" id="address" v-model="address">
                </div>
                <div class="form-group">
                    <label for="city">City</label>
                    <input type="text" class="form-control" id="city" v-model="city">
                </div>
                <div class="form-group">
                    <label for="state">State</label>
                    <input type="text" class="form-control" id="state" v-model="state">
                </div>
                <div class="form-group">
                    <label for="phoneNumber">Phone number</label>
                    <input type="text" class="form-control" id="phoneNumber" v-model="phoneNumber">
                </div>
                <div class="form-group">
                    <label for="healthCareNumber">Insurance number</label>
                    <input type="text" class="form-control" id="healthCareNumber" v-model="healthCareNumber">
                </div>
                <button type="submit" class="btn btn-primary mb-2">Register</button>
            </form>
        </div>
    </div>
</template>
<script>

    import axios from "axios";

	export default{
        name: "Register",
        data : function () {
            return {
                firstName : undefined,
                lastName : undefined,
                email : undefined,
                password : undefined,
                passwordF : undefined,
                address : undefined,
                city : undefined,
                state : undefined,
                phoneNumber : undefined,
                healthCareNumber : undefined
            }
        },
        methods : {
            submit : function () {
                var user = {
                	"firstName" : this.firstName,
	                "lastName" : this.lastName,
	                "email" : this.email,
	                "password" : this.password,
                    "passwordF" : this.passwordF,
	                "address" : this.address,
	                "city" : this.city,
	                "state" : this.state,
	                "phoneNumber" : this.phoneNumber,
	                "healthCareNumber" : this.healthCareNumber
                }
               console.log(user)
               axios.post('http://localhost:8080/register', user)
            }
        }
    }
</script>
<style scoped>
	
</style>