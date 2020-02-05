<template>
  <div class="container row justify-content-center m-0">
        <div class="col-10 card m-5 p-5">
            <div class="row justify-content-center">
                <p><small>Blood type: </small>{{medicalRecord.bloodType}}</p>
            </div>
            <div class="row justify-content-center">
                <p><small>Height: </small>{{medicalRecord.height}}cm</p>
            </div>
            <div class="row justify-content-center">
                <p><small>Weight: </small>{{medicalRecord.weight}}kg</p>
            </div>
            <div class="row justify-content-center">
                <p><small>Date of birth: </small>{{medicalRecord.dateOfBirth | formatDate}}</p>
            </div>
        </div>
        <div v-if="reports.length>0" class="col-10 card m-5 p-5">
            <div class="card-title h3">Reports:</div>
            <table class="table">
                <thead>
                    <tr>
                        <td>Diagnosis</td>
                        <td>Medicine</td>
                        <td>Diagnosed by</td>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="report in reports" :key="report.id">
                        <td>{{report.diagnosis}}</td>
                        <td><span v-for="medicine in report.medicines" :key="medicine"><p>{{medicine}}</p></span></td>
                        <td>
                            <b>
                                <router-link class="special-link" :to="'doctor_'+report.doctor.id">dr. {{report.doctor.firstName}} {{report.doctor.lastName}}</router-link>
                            </b>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
  </div>
</template>

<script>
export default {
    name: "medicalRecord",
    data: function() {
        return {
            medicalRecord : Object,
            reports : []
        }
    }, 
    methods: {

    },
    filters : {
        formatDate: function(date){
                if (!date) return '';
                let formatedDate = new Date(date);
                var month = ('0' + (formatedDate.getMonth() + 1)).slice(-2);
                var day = ('0' + formatedDate.getDate()).slice(-2);
                var year = formatedDate.getFullYear();
                return day + '.' + month + '.' + year;
            }
    },
    mounted : function() {
		this.axios.get("http://localhost:8080/record/" + localStorage.getItem('user_id'))
		.then(response => {
            this.medicalRecord = response.data;
            console.log(this.medicalRecord);
            this.reports = response.data.reports;
        });
	}

}
</script>

<style>

</style>