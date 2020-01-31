<template>
    <div class="container">
        <h4>Podaci o pacijentu</h4>
        <div v-if="patient" class="row">
            <div class="col-6">
                <p>Name: {{patient.firstName}}</p>
                <p>Last name: {{patient.lastName}}</p>
                <p>Health care number: {{patient.healthCareNumber}}</p>
                <p>Address: {{patient.address}}</p>
                <p>City: {{patient.city}}</p>
                <p>State: {{patient.state}}</p>
                <p>Phone number: {{patient.phoneNumber}}</p>
                <p>E-mail: {{patient.email}}</p>
            </div>
            <div class="col-6">
               <p><router-link class="btn btn-primary" :to="{path: '/patientRecord', query: {id: patient.id}}">
                    Medical record
               </router-link></p>
                <p><router-link class="btn btn-outline-primary" to="/reschedule">Schedule appointment</router-link></p>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "PatientData",
        data: function() {
            return {
                patient: null,
            }
        },
        mounted: function() {
            this.axios.get("http://localhost:8080/patient/" + this.$route.query.id)
                .then(response => {this.patient = response.data})
        }
    }
</script>

<style scoped>

</style>