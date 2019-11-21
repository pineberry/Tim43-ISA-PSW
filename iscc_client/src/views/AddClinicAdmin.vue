<template>
    <div class="container">
        <div class="row">
            <form @submit.prevent="submitRegistration" class="col-6">
                <div class="form-group">
                    <label for="selectClinic">Clinic</label>
                    <select class="form-control" v-model="clinicName" id="selectClinic">
                        <option v-for="clinic in clinics" :key="clinic.id" :value="clinic.name">{{clinic.name}}</option>
                    </select>
                </div>
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
                <button type="submit" class="btn btn-primary mb-2">Register</button>
            </form>
        </div>
    </div>
</template>

<script>
    export default {
        name: "AddClinicAdmin",
        data : function() {
          return{
                email : undefined,
                password : undefined,
                firstName : undefined,
                lastName : undefined,
                clinicName : undefined,
                phoneNumber : undefined,
                address : undefined,
                city : undefined,
                state : undefined,
                clinics : undefined
          }
        },
        mounted : function () {
            this.axios.get("http://localhost:8080/clinic/clinics")
                .then(response => {this.clinics = response.data})
                .catch(error => {alert(error.response.data)})
        },
        methods : {
            submitRegistration : function () {
                var admin = {
                    "email" : this.email,
                    "password" : this.password,
                    "firstName" : this.firstName,
                    "lastName" : this.lastName,
                    "clinicName" : this.clinicName,
                    "phoneNumber" : this.phoneNumber,
                    "address" : this.address,
                    "city" : this.city,
                    "state" : this.state
                }

                this.axios.post("http://localhost:8080/center/admin/register/clinic/administrator", admin)
                    .then(response => {
                        this.email = "";
                        this.password = "";
                        this.firstName = "";
                        this.lastName = "";
                        this.clinicName = "";
                        this.phoneNumber = "";
                        this.address = "";
                        this.city = "";
                        this.state = "";
                        alert(response.data.firstName + " admin uspjesno dodan!")
                    })
                    .catch(error => {
                        alert(error.response.data)
                    })
            }
        }
    }

</script>