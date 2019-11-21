<template>
    <div class="container">
        <div class="row">
            <form @submit.prevent="submitRegistration" class="col-6">
                <div class="form-group">
                    <label for="inputEmail">Email</label>
                    <input class="form-control" type="email" v-model="email" id="inputEmail" placeholder="Enter admins email">
                </div>
                <div class="form-group">
                    <label for="inputPassword">Password</label>
                    <input class="form-control" type="password" v-model="password" id="inputPassword" placeholder="Enter admins password">
                </div>
                <div class="form-group">
                    <label for="inputFirstName">Name</label>
                    <input class="form-control" type="text" v-model="firstName" id="inputFirstName" placeholder="Enter admins name">
                </div>
                <div class="form-group">
                    <label for="inputLasttName">Last Name</label>
                    <input class="form-control" type="text" v-model="lastName" id="inputLasttName" placeholder="Enter admins last name">
                </div>
                <div class="form-group">
                    <label for="inputPhoneNumber">Phone Number</label>
                    <input class="form-control" type="text" v-model="phoneNumber" id="inputPhoneNumber" placeholder="Enter admins phone number(optional)">
                </div>
                <div class="form-group">
                    <label for="inputAddress">Address</label>
                    <input class="form-control" type="text" v-model="address" id="inputAddress" placeholder="Enter admins address(optional)">
                </div>
                <div class="form-group">
                    <label for="inputCity">City</label>
                    <input class="form-control" type="text" v-model="city" id="inputCity" placeholder="Enter admins city(optional)">
                </div>
                <div class="form-group">
                    <label for="inputState">State</label>
                    <input class="form-control" type="text" v-model="state" id="inputState" placeholder="Enter admins state(optional)">
                </div>
                <button type="submit" class="btn btn-primary">Register</button>
            </form>
        </div>
    </div>
</template>

<script>
    export default {
        name: "AddCenterAdmin",
        data : function() {
            return{
                email : undefined,
                password : undefined,
                firstName : undefined,
                lastName : undefined,
                phoneNumber : undefined,
                address : undefined,
                city : undefined,
                state : undefined,
            }
        },
        methods : {
            submitRegistration : function () {
                var admin = {
                    "email" : this.email,
                    "password" : this.password,
                    "firstName" : this.firstName,
                    "lastName" : this.lastName,
                    "phoneNumber" : this.phoneNumber,
                    "address" : this.address,
                    "city" : this.city,
                    "state" : this.state
                }

                this.axios.post("http://localhost:8080/center/admin/center/administrator", admin)
                    .then(response => {
                        this.email = "";
                        this.password = "";
                        this.firstName = "";
                        this.lastName = "";
                        this.phoneNumber = "";
                        this.address = "";
                        this.city = "";
                        this.state = "";
                        alert(response.data.firstName + " admin uspjesno dodat!")
                    })
                    .catch(error => {
                        alert(error.response.data)
                    })
            }
        }
    }

</script>