<template>
    <div>
        <div class="jumbotron" v-for="patient in patients" :key="patient.id">
            First Name: {{patient.firstName}}
            <br>Last Name: {{patient.lastName}}
        </div>
    </div>
</template>

<script>
    export default {
        name : "Patients",
        data : function () {
            return{
                patients: null
            }
        },
        mounted() {
            this.axios.get("http://localhost:8080/patient/patients")
                .then(response => {this.patients = response.data})
                .catch(error => {alert(error.data())})
        }
    }
</script>