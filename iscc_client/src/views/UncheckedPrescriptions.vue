<template>
    <div class="container" v-if="prescriptions">
        <div class="card" v-for="pres in prescriptions" :key="pres.id">
            <div class="card-header">
                <div class="row">
                    <div class="col-6">
                        <p>{{pres.medication}}</p>
                    </div>
                    <div class="col-4"></div>
                    <div class="col-2">
                        <button class="btn btn-success btn-sm" v-on:click="check(pres.id)">Check</button>
                    </div>
                </div>
            </div>
            <div class="card-body" v-if="pres &&pres.reportDTO">
                <blockquote>
                    <b>Diagnosis: </b>{{pres.reportDTO.diagnosis}} <br/>
                    <b>Patient: </b>{{pres.reportDTO.medicalRecord.patientDTO.firstName}} {{pres.reportDTO.medicalRecord.patientDTO.lastName}}
                </blockquote>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name : "UncheckedPrescriptions",
        data : function () {
            return {
                prescriptions : null
            }
        },
        mounted : function () {
           this.getPrescriptions();
        },
        methods : {
            check : function (id) {
                var nurse = {
                    "email" : localStorage.getItem("email")
                }
                this.axios.put("http://localhost:8080/nurse/check/prescription/" + id, nurse)
                    .then(response => { 
                        this.getPrescriptions()
                        this.$router.go();})
                    .catch(error => {alert(error)})
            },
            getPrescriptions : function () {
                var nurse = {
                    "email" : localStorage.getItem("email")
                }
                this.axios.post("http://localhost:8080/nurse/unchecked/prescriptions", nurse)
                    .then(response => {
                        this.prescriptions = response.data
                    })
                    .catch(error => {
                        alert(error);
                    })
            }
        }
    }
</script>