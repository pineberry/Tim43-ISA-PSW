<template>
    <div class="container">
            <form @submit.prevent="editAdmin" class="row">
                <div class="col-6">
                    <div class="form-group">
                        <label for="inputEmail">Email</label>
                        <input class="form-control" type="email" v-model="email" id="inputEmail" placeholder="Enter email">
                    </div>
                    <div class="form-group">
                        <label for="inputFirstName">Name</label>
                        <input class="form-control" type="text" v-model="firstName" id="inputFirstName" placeholder="Enter name">
                    </div>
                    <div class="form-group">
                        <label for="inputLasttName">Last Name</label>
                        <input class="form-control" type="text" v-model="lastName" id="inputLasttName" placeholder="Enter last name">
                    </div>
                    <div class="form-group">
                        <label for="inputPhoneNumber">Phone Number</label>
                        <input class="form-control" type="text" v-model="phoneNumber" id="inputPhoneNumber" placeholder="Enter phone number">
                    </div>
                </div>
                <div class="col-6">
                    <div class="form-group">
                        <label for="inputAddress">Address</label>
                        <input class="form-control" type="text" v-model="address" id="inputAddress" placeholder="Enter address">
                    </div>
                    <div class="form-group">
                        <label for="inputCity">City</label>
                        <input class="form-control" type="text" v-model="city" id="inputCity" placeholder="Enter city">
                    </div>
                    <div class="form-group">
                        <label for="inputState">State</label>
                        <input class="form-control" type="text" v-model="state" id="inputState" placeholder="Enter state">
                    </div>
                    <div class="d-flex flex-row-reverse"><button type="submit" class="btn btn-primary">Edit</button></div>
                </div>
            </form>
        </div>
</template>

<script>
    export default {
        name: "editClinicAdmin",
        data : function() {
            return{
                email : undefined,
                firstName : undefined,
                lastName : undefined,
                phoneNumber : undefined,
                address : undefined,
                city : undefined,
                state : undefined,
                admin: null
            }
        },
        mounted : function () {
            this.axios.get("http://localhost:8080/clinic/admin/" + localStorage.getItem('user_id'))
                .then(response => {
                    this.admin = response.data;
                    this.email = this.admin.email;
                    this.firstName = this.admin.firstName;
                    this.lastName = this.admin.lastName;
                    this.phoneNumber = this.admin.phoneNumber;
                    this.address = this.admin.address;
                    this.city = this.admin.city;
                    this.state = this.admin.state;
                })
                .catch(error => {alert(error.response.data)})
        },
        methods: {
            editAdmin: function() {

                this.admin.firstName = this.firstName;
                this.admin.lastName = this.lastName;
                this.admin.email = this.email;
                this.admin.phoneNumber = this.phoneNumber;
                this.admin.address = this.address;
                this.admin.city = this.city;
                this.admin.state = this.state;

                var valid = true;
                var tempName = '';
                var tempSurname = '';
                var tempCity = '';
                var tempState = '';

                if (this.firstName != undefined){
                    this.firstName.trim();
                    tempName = this.firstName.match('[A-Za-z]*');
                }else {
                    this.firstName = '';
                }
                if (this.lastName != undefined){
                    this.lastName.trim();
                    tempSurname = this.lastName.match('[A-Za-z]*');
                }else {
                    this.lastName = '';
                }
                if (this.city != undefined){
                    this.city.trim();
                    tempCity = this.city.match('[A-Za-z]*');
                }else {
                    this.city = '';
                }
                if (this.state != undefined){
                    this.city.trim();
                    tempState = this.state.match('[A-Za-z]*');
                }else {
                    this.state = '';
                }
                if (this.address != undefined)
                    this.address.trim();
                else
                    this.address = '';
                if (this.email != undefined)
                    this.email.trim();
                else
                    this.email = '';
                if (this.phoneNumber != undefined)
                    this.phoneNumber.trim();
                else
                    this.phoneNumber = '';

                if (this.firstName === undefined || this.firstName === '' || this.lastName === undefined || this.lastName === ''
                    || this.city === undefined || this.city === '' || this.state === undefined || this.state === ''
                    || this.address === undefined || this.address === '' || this.email === undefined || this.email === ''
                    || this.phoneNumber === undefined || this.phoneNumber === '') {

                    valid = false;
                }
                else if ((tempName != this.firstName) || (tempSurname != this.lastName) || (tempCity != this.city) || (tempCity != this.city)
                    || (tempState != this.state) || (this.firstName[0].match('[A-Z]') === null) || (this.lastName[0].match('[A-Z]') === null)
                    || (this.city[0].match('[A-Z]') === null) || (this.state[0].match('[A-Z]') === null)){
                    valid = false;
                }
                else {
                    valid = true;
                }

                if (valid) {
                    this.axios.put("http://localhost:8080/clinic/admin/update", this.admin)
                        .then(response =>{
                            this.$router.push('/adminProfile')
                        })
                }
            }
        }
    }
</script>

<style scoped>

</style>