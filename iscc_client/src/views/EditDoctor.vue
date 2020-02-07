<template>
    <div class="container">
        <form @submit.prevent="editDoctor" class="row">
            <div class="col-6">
                <div class="form-group">
                    <label for="inputFirstName">Name</label>
                    <input class="form-control" type="text" v-model="firstName" id="inputFirstName" placeholder="Enter name">
                    <span class="val">{{valName}}</span>
                </div>
                <div class="form-group">
                    <label for="inputLasttName">Last Name</label>
                    <input class="form-control" type="text" v-model="lastName" id="inputLasttName" placeholder="Enter last name">
                    <span class="val">{{valSurname}}</span>
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
                    <span class="val">{{valCity}}</span>
                </div>
                <div class="form-group">
                    <label for="inputState">State</label>
                    <input class="form-control" type="text" v-model="state" id="inputState" placeholder="Enter state">
                    <span class="val">{{valState}}</span>
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
        computed: {
            valName: function () {
                if (this.firstName != undefined && this.firstName.length > 0) {
                    if (this.firstName[0].match('[A-Z]') === null)
                        return 'First letter must be capital!';
                    else return null;
                } else return null;
            },
            valSurname: function () {
                if (this.lastName != undefined && this.lastName.length > 0) {
                    if (this.lastName[0].match('[A-Z]') === null)
                        return 'First letter must be capital!';
                    else return null;
                } else return null;
            },
            valCity: function () {
                if (this.city != undefined && this.city.length > 0) {
                    if (this.city[0].match('[A-Z]') === null)
                        return 'First letter must be capital!';
                    else return null;
                } else return null;
            },
            valState: function(){
                if(this.state != undefined && this.state.length > 0){
                    if(this.state[0].match('[A-Z]') === null)
                        return 'First letter must be capital!';
                    else return null;
                }
                else return null;
            }
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

                if (this.firstName != undefined){
                    this.firstName.trim();
                }else {
                    this.firstName = '';
                }
                if (this.lastName != undefined){
                    this.lastName.trim();
                }else {
                    this.lastName = '';
                }
                if (this.city != undefined){
                    this.city.trim();
                }else {
                    this.city = '';
                }
                if (this.state != undefined){
                    this.state.trim();
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

                if (this.firstName === '' || this.lastName === ''  ||  this.city === '' || this.state === ''
                    || this.address === '' || this.email === '' || this.phoneNumber === ''
                    || this.workingtimeStart === '' || this.workingtimeEnd === '') {

                    alert('All fields should be filled!');
                    return;
                } else if (this.firstName[0].match('[A-Z]') === null || this.lastName[0].match('[A-Z]') === null
                                || this.city[0].match('[A-Z]') === null || this.state[0].match('[A-Z]') === null) {
                    return;
                }

                this.axios.put("http://localhost:8080/doctor/update", this.doctor)
                    .then(response =>{
                        this.$router.go(-1);
                    })
                }
            }
    }
</script>

<style scoped>
    .val {
        color: darkred;
    }
</style>