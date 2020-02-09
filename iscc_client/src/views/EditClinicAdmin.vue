<template>
    <div class="container">
            <form @submit.prevent="editAdmin" class="row">
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
            editAdmin: function() {

                if(localStorage.getItem("typeOfUser") != "clinicAdministrator") {
                    alert('Only clinic admin has permission for this action!');
                    return;
                }

                this.admin.firstName = this.firstName;
                this.admin.lastName = this.lastName;
                this.admin.email = this.email;
                this.admin.phoneNumber = this.phoneNumber;
                this.admin.address = this.address;
                this.admin.city = this.city;
                this.admin.state = this.state;

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
                    this.city.trim();
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
                         || this.address === '' || this.email === '' || this.phoneNumber === ''){
                    alert('All fields should be filled!');
                    return;
                } else if (this.firstName[0].match('[A-Z]') === null || this.lastName[0].match('[A-Z]') === null
                    || this.city[0].match('[A-Z]') === null || this.state[0].match('[A-Z]') === null) {
                    return;
                }

                this.axios.put("http://localhost:8080/clinic/admin/update", this.admin)
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