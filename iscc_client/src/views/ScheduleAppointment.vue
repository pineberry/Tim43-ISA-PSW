<template>
    <div class="container-fluid row justify-content-center">
        <div class="card m-2 col-6">
            <div class="card-body">
                <h3 class="card-title">{{doctor.firstName}} {{doctor.lastName}}</h3>
                <div class="row">
                    <div class="col-6">
                        <div class="row">
                            <div class="col-6">
                                <img src="../images/doctor.png" alt="..." class="rounded float-left">
                            </div>
                        </div>
                        <p>Average rating: {{doctor.averageRating}}</p>
                    </div>
                    <div class="col-6">
                        <p>Specialization: {{doctor.specialized.typeName}} - {{doctor.specialized.typeDescription}}</p>
                        <p>Location: <b><a class="card-link" :href="'http://localhost:8081/clinic/'+doctor.clinic.id">{{doctor.clinic.name}}</a></b> {{doctor.address}} - {{doctor.city}}</p>
                        <p>Working time: {{doctor.workingtimeStart}} - {{doctor.workingtimeEnd}}</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-6">
            <p>Choose date and time:</p>
            <div class="row justify-content-center">
                <input type="date" class="form-control col m-1" :min="today" v-on:change="setDate(date)"  id="date" v-model="date">
                <div class="col p-0 m-1">
                    <button class="btn btn-outline-primary w-100 dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Choose time
                    </button>
                    <div class="dropdown-menu w-100" aria-labelledby="dropdownMenuButton">
                        <div v-for="hour in workingtimeEnd" :key="hour" >
                            <a class="dropdown-item" href="#" v-if="hour >= workingtimeStart  && hour < workingtimeEnd" v-on:click="requestAppointment(hour)">
                                {{hour}}:00 - {{hour + 1}}:00
                            </a>
                        </div>
                    </div>
                </div>
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
            console.log(hour)
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
				.catch(error => {alert(error.response.data)})
        },
        setDate : function(date) {
            this.date = date
        }
    }, 
}
</script>

<style>

</style>