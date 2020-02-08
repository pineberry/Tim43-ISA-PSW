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
                <div class="row">
                    <p><router-link class="btn btn-primary" :to="{path: '/patientRecord', query: {id: patient.id}}">Medical record</router-link></p>
                </div>
                <div class="row">
                    <p v-if="user === 'doctor'"><router-link class="btn btn-outline-primary" :to="{path: '/reschedule/' + id}">Schedule appointment</router-link></p>
                </div>
                <div class="row" v-if="patient && medicalProcedures">
                    <table class="table">
                        <thead class="thead-dark">
                            <th scope="col">#</th>
                            <th scope="col">Procedure date</th>
                            <th scope="col">Examinate</th>
                        </thead>
                        <tbody>
                        <tr v-for="procedure in medicalProcedures" :key="procedure.id">
                            <td v-if="procedure.patient && procedure.medicalRoom && procedure.patient.id == patient.id">{{medicalProcedures.indexOf(procedure) + 1}}</td>
                            <td v-if="procedure.patient && procedure.medicalRoom && procedure.patient.id == patient.id">{{formDate(procedure.dateOfProcedure)}}</td>
                            <td v-if="procedure.patient && procedure.medicalRoom && procedure.patient.id == patient.id">
                                <router-link class="btn btn-primary" :to="{path: '/report', query: {id: patient.id, proc: procedure.id}}">
                                    Exam
                                </router-link>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
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
                id: this.$route.query.id,
                user: localStorage.getItem("typeOfUser"),
                doctor: null,
                medicalProcedures: null
            }
        },
        mounted: function() {
            this.axios.get("http://localhost:8080/patient/" + this.$route.query.id)
                .then(response => {this.patient = response.data})
                .catch(error => {console.log(error);})
            this.axios.get("http://localhost:8080/doctor/" + localStorage.getItem("user_id")) 
                .then(response => {
                    this.doctor = response.data;
                    this.medicalProcedures = this.doctor.medicalProcedures;
                })
                .catch(error => {console.log(error);})
        },
        methods : {
             formDate(date) {
                let d = new Date(date);
                return formatDate(d);
            }
        }
    }

    function formatDate(date) {
        if (date != null) {
            var options = { year: "numeric", month: "2-digit", day: "2-digit" };
            let string = date.toLocaleDateString("sv-SE", options);
            return string;
        }
        return "";
    }

   
</script>

<style scoped>

</style>