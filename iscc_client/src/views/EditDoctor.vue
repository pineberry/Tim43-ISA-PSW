<template>
    <div class="container">
        <form @submit.prevent="editDoctor" class="row">
            <div class="col-6">
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
        name: "editDoctor",
        data : function() {
            return{
                email : undefined,
                firstName : undefined,
                lastName : undefined,
                phoneNumber : undefined,
                address : undefined,
                city : undefined,
                state : undefined,
                doctor: null
            }
        },
        mounted : function () {
            this.axios.get("http://localhost:8080/doctor/" + localStorage.getItem('user_id'))
                .then(response => {
                    this.doctor = response.data;
                    this.email = this.doctor.email;
                    this.firstName = this.doctor.firstName;
                    this.lastName = this.doctor.lastName;
                    this.phoneNumber = this.doctor.phoneNumber;
                    this.address = this.doctor.address;
                    this.city = this.doctor.city;
                    this.state = this.doctor.state;
                })
                .catch(error => {alert(error.response.data)})
        },
        methods: {
            editDoctor: function() {

                this.doctor.firstName = this.firstName;
                this.doctor.lastName = this.lastName;
                this.doctor.email = this.email;
                this.doctor.phoneNumber = this.phoneNumber;
                this.doctor.address = this.address;
                this.doctor.city = this.city;
                this.doctor.state = this.state;

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
                    this.state.trim();
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
                else {
                    valid = true;
                }

                if (valid) {
                    this.axios.put("http://localhost:8080/doctor/update", this.doctor)
                        .then(response =>{
                            this.$router.go(-1);
                        })
                }
            }
        }

    }
</script>

<style scoped>

</style>