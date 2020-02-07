<template>
    <div class="container">
        <table class="table">
            <thead class="thead-dark">
                <th scope="col">#</th>
                <th>Patient</th>
                <th>Diagnosis</th>
                <th>Medication</th>
            </thead>
            <tbody>
                <tr v-for="pres in prescriptions" :key="pres.id">
                    <td>{{prescriptions.indexOf(pres) + 1}}</td>
                    <td>{{pres.reportDTO.medicalRecord.patientDTO.firstName}} {{pres.reportDTO.medicalRecord.patientDTO.lastName}}</td>
                    <td>{{pres.reportDTO.diagnosis}}</td>
                    <td>{{pres.medication}}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    export default {
        name : "CheckedPrescriptions.vue",
        data : function () {
            return{
                nurse : localStorage.getItem('user_id'),
                prescriptions : null
            }
        },
        mounted() {
            this.getPrescriptions()
        },
        methods : {
            getPrescriptions : function () {
                this.axios.get("http://localhost:8080/nurse/checked/prescriptions/" + this.nurse)
                    .then(response => {this.prescriptions = response.data;})
                    .catch(error => {alert(error)})
            }
        }
    }
</script>