<template>
    <div class="container">
        <div class="row">
            <div class="col-4">
                <h4>Medical record</h4>
                <p>Date of birth: {{medicalRecord.dateOfBirth}}</p>
                <p>Height: {{medicalRecord.height}}</p>
                <p>Weight: {{medicalRecord.weight}}</p>
                <p>Blood type: {{medicalRecord.bloodType}}</p>
            </div>
            <div class="col-8">
                <h4>Medical reports</h4>
                <div v-if="medicalRecord.reports.length != 0" class="contentScroll">
                    <div class="row" v-for="report in medicalRecord.reports" :key="report.id">
                        <div class="card col-12 marginTop">
                            <div class="card-header">
                                <p>{{report.diagnosis}}</p>
                            </div>
                            <div class="card-body">
                                <blockquote class="blockquote mb-0">
                                    <p>{{report.notes}}</p>
                                    <footer class="blockquote-footer">Dr. {{report.doctorFirstName}} {{report.doctorLastName}}</footer>
                                </blockquote>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "PatientRecord",
        data: function() {
            return {
                medicalRecord: null
            }
        },
        mounted: function() {
            this.axios.get("http://localhost:8080/record/patient/" + this.$route.query.id)
                .then(response => {this.medicalRecord = response.data;})
        }
    }
</script>

<style scoped>
    .marginTop {
        margin-top: 5px;
    }

    .contentScroll {
        height: 450px;
        overflow-y: scroll;
        overflow-x: hidden;
    }
</style>