<template>
    <div class="container-fluid">
        <p class="h5">Appointment requested with doctor:</p>
        <div class="card m-1 col-5">
            <div class="card-body" v-if="doctor">
                <h3 class="card-title">{{doctor.firstName}} {{doctor.lastName}}</h3>
                <div class="row">
                    <div class="col-2">
                        <img src="../images/doctor.png" alt="..." class="rounded float-left">
                    </div>
                    <div class="col-5">
                        <p>Average rating: {{doctor.averageRating}}</p>
                    </div>
                    <div class="col-5">
                        <p>Specialization: {{doctor.specialized.typeName}} - {{doctor.specialized.typeDescription}}</p>
                        <p>Location: <b><a class="card-link" :href="'http://localhost:8081/clinic/'+doctor.clinic.id">{{doctor.clinic.name}}</a></b> {{doctor.address}} - {{doctor.city}}</p>
                        <p>Working time: {{doctor.workingtimeStart}} - {{doctor.workingtimeEnd}}</p>
                    </div>
                </div>
            </div>
        </div>
        <form class="col-5 m-1">
            <div class="form-group row">
                <label for="date">Date for the appointment</label>
                <div class="col-sm-8 text-right">
                    <input type="date" class="form-control" :min="today" v-on:change="setDate(date)"  id="date" v-model="date">
                </div>
            </div>
        </form>
        
        <div class="col-5">
            <div class="row" v-for="hour in workingtimeEnd" :key="hour">
                <button class="col-sm-6 btn btn-primary btn-sm m-2" v-if="hour >= workingtimeStart  && hour < workingtimeEnd" 
                    v-on:click="requestAppointment(hour)">
                    {{hour}}:00 - {{hour + 1}}:00
                </button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "ScheduleAppointment",
    data: function() {
        return {
            doctor : undefined,
            doctorsAppointmets: [],
            today : undefined,
            date : undefined,
            unavailableAppointments : [],
            response : undefined,
            workingtimeStart : undefined,
            workingtimeEnd : undefined
        }
    },
    mounted: function() {
        this.today = new Date();
        this.today = this.today.getFullYear() + '-' + ('0' + (this.today.getMonth() + 1)).slice(-2) + '-' + ('0' + this.today.getDate()).slice(-2);
        this.axios.get("http://localhost:8080/doctor/"+this.$route.query.doctor)
        .then(response => {
                this.response = response
                this.doctor = this.response.data
                this.doctorsAppointmets = this.doctor.medicalProcedures
                
                this.workingtimeEnd = +this.doctor.workingtimeEnd.slice(0,2)
                this.workingtimeStart = +this.doctor.workingtimeStart.slice(0,2)
            })
        .catch(error => {
            alert(error.response.data)
            })
        
    },
    computed: {
        
    },
    methods : {
        requestAppointment : function(hour) {
            this.axios.get("http://localhost:8080/patient/schedule-appointment", {
					params: {
						date : this.date,
						hour : hour,
						patient : localStorage.getItem('user_id'),
						doctor : this.doctor.id
					}
				})
				.then(response => {
					this.response = response
					console.log(this.response)
				})
				.catch(error => {alert(error)})
        },
        setDate : function(date) {
            this.date = date
        }
    }, 
}
</script>

<style>

</style>