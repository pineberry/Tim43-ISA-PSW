<template>
    <div class="container" v-if="reports">
        <div v-for="report in reports" :key="report.id" class="jumbotron">
            <form>
                <div class="row">
                    <div class="col-4">
                        <div class="form-group">
                            <label>Notes</label>
                            <textarea class="form-control" readonly="true" v-model="report.notes"></textarea>
                        </div>
                        <div class="form-group" v-if="report.diagnosis">
                            <label>Diagnosis</label>
                            <input type="text" class="form-control" readonly="true" v-model="report.diagnosis">
                        </div>
                        <div class="form-group" v-if="report.medicines">
                            <label>Medicines</label>
                            <input type="text" class="form-control" readonly="true" v-model="report.medicines">
                        </div>
                        <button v-on:click.prevent="edit(report.id)" class="btn btn-primary">Edit</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
    export default {
        name : "ExaminationReports",
        data : function () {
            return{
                reports : null,
                id : 0,
            }
        },
        mounted() {
            this.getReportsByDoctor();
        },
        methods : {
            getReportsByDoctor : function () {
                this.axios.get("http://localhost:8080/doctor/reports/"  + localStorage.getItem('user_id'))
                    .then(response =>
                    {
                        this.reports = response.data
                    })
                    .catch(error => {alert(error)})
            },

            edit : function (id) {
                this.$router.push("/editReport/" + id)
            }
        }
    }
</script>