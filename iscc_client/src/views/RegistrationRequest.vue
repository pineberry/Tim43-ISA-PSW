<template>
    <div class="container">
        <div class="row">
            <div class="col-8">
                <div class="form-group">
                    <label>First Name: {{firstName}}</label>
                </div>
                <div class="form-group">
                    <label>Last name: {{lastName}}</label>
                </div>
                <div class="form-group">
                    <label>Email address: {{email}}</label>
                </div>
                <div class="form-group">
                    <label>Address: {{address}}</label>
                </div>
                <div class="form-group">
                    <label>City: {{city}}</label>
                </div>
                <div class="form-group">
                    <label>State: {{state}}</label>
                </div>
                <div class="form-group">
                    <label>Phone number: {{phoneNumber}}</label>
                </div>
                <div class="form-group">
                    <label>Insurance number: {{healthCareNumber}}</label>
                </div>
                <button class="btn btn-success btn-sm" type="button" v-on:click="acceptRegistration">Accept</button>
            </div>
            <form class="col-8" v-on:submit.prevent="sendNotification" accept-charset="UTF-8">
                <div class="form-group">
                    <label for="inputExplanation">Explanation</label>
                    <textarea id="inputExplanation" rows="3" v-model="explanation" class="form-control"></textarea>
                </div>
                <button class="btn btn-danger btn-sm" type="submit">Deny</button>
            </form>
        </div>
    </div>
</template>

<script>
    export default {
        name : "RegistrationRequest",
        data : function () {
            return{
                firstName : undefined,
                lastName : undefined,
                email : undefined,
                address : undefined,
                city : undefined,
                state : undefined,
                phoneNumber : undefined,
                healthCareNumber : undefined,
                explanation : undefined
            }
        },
        mounted() {
            this.axios.get("http://localhost:8080/patient/" + this.$route.params.id)
                .then(response => {
                    this.firstName = response.data.firstName;
                    this.lastName = response.data.lastName;
                    this.email = response.data.email;
                    this.address = response.data.address;
                    this.city = response.data.city;
                    this.state = response.data.state;
                    this.phoneNumber = response.data.phoneNumber;
                    this.healthCareNumber = response.data.healthCareNumber;
                })
        },
        methods : {
            acceptRegistration : function() {
                this.axios.put("http://localhost:8080/center/admin/accept/" + this.$route.params.id)
                    .then(response => {alert("Uspjesno prihvacen pacijent")})
                    .catch(error => {alert(error.response.data)})
            },
            sendNotification : function () {
                var responseDTO = {
                    "email" : this.email,
                    "explanation" : this.explanation
                }
                this.axios.delete("http://localhost:8080/center/admin/deny/" + this.$route.params.id, responseDTO)
                    .then(response => {alert("Uspjesno odbijen zahtjev")})
                    .catch(error => {alert(error.response.data)})
            }
        }
    }
</script>