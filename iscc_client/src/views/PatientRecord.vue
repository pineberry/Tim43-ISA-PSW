<template>
    <div class="container" v-if="medicalRecord">
        <div class="row">
            <div class="col-4">
                <h4>Medical record</h4>
                <p>Date of birth: {{fromDat(medicalRecord.dateOfBirth)}}</p>
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
                                <div class="row">
                                    <div class="col-6">
                                        <p>{{report.diagnosis}}</p>
                                    </div>
                                    <div class="col-4"></div>
                                    <div class="col-2" v-if="report.doctor && user === 'doctor' && report.doctor.id == id">
                                        <router-link class="btn btn-primary btn-sm" :to="{path: '/editReport/' + report.id}">
                                            Edit
                                        </router-link>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <blockquote class="blockquote mb-0">
                                    <p>{{report.notes}}</p>
                                    <footer class="blockquote-footer">Dr. {{report.doctor.firstName}} {{report.doctor.lastName}}</footer>
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
                medicalRecord: null,
                id: localStorage.getItem("user_id"),
                user: localStorage.getItem("typeOfUser")
            }
        },
        mounted: function() {
            this.axios.get("http://localhost:8080/record/patient/" + this.$route.query.id)
                .then(response => {this.medicalRecord = response.data;})
        },
        methods : {
            fromDat(date) {
                return formatDate(date);
            }
        }
    }

    function formatDate(dat) {
        if (dat != null) {
            let date = new Date(dat);
            var options = { year: "numeric", month: "2-digit", day: "2-digit" };
            let string = date.toLocaleDateString("sv-SE", options);
            return string;
        }
        return "";
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