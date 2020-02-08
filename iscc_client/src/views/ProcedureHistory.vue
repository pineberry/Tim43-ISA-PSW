<template>
    <div class="container row justify-content-center m-0">
        <div v-if="appointments.length>0" class="col-10 card mt-5 ml-1 mr-1 p-5">
            <table class="table">
                <thead>
                    <tr>
                        <th v-on:click="dateSort()" class="pointer" scope="col">Date and time</th>
                        <th v-on:click="doctorSort()" class="pointer" scope="col">Performed by</th>
                        <th v-on:click="clinicSort()" class="pointer" scope="col">Done in clinic</th>
                        <th v-on:click="procedureSort()" class="pointer" scope="col">Type of procedure</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="appointment in appointments" :key="appointment.id">
                        <td>{{appointment.dateOfProcedure | formatDate}} at {{appointment.startTime}}h</td>
                        <td>
                            <b>
                                <router-link class="special-link" :to="'doctor_'+appointment.doctor.id">
                                    dr. {{appointment.doctor.firstName}} {{appointment.doctor.lastName}}
                                </router-link>
                                <small><button v-if="!appointment.doctorRated" v-on:click="rateDoctorData(appointment.doctor.id, appointment.id)" class="btn btn-sm btn-outline-secondary pt-0 pb-0 pl-3 pr-3 w-100">Rate</button></small>
                            </b>
                        </td>
                        <td>
                            <b>
                                <router-link class="special-link" :to="'clinic_'+appointment.doctor.clinic.id">
                                    {{appointment.doctor.clinic.name}}
                                </router-link>
                            </b>
                            <p class="text-muted mb-0"><small>{{appointment.doctor.clinic.address}}</small></p>
                            <small><button v-if="!appointment.clinicRated" v-on:click="rateClinicData(appointment.doctor.clinic.id, appointment.id)" class="btn btn-sm btn-outline-secondary pt-0 pb-0 pl-3 pr-3 w-100">Rate</button></small>
                        </td>
                        <td>
                            {{appointment.procedureType.typeName}}
                            <p class="text-muted"><small>{{appointment.procedureType.typeDescription}}</small></p>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div class="card m-1 pb-1" v-if="rateDoctorF">
                <div class="card-body">
                    <h3 class="card-title">{{doctor.firstName}} {{doctor.lastName}}</h3>
                    <hr class="my-1">
                    <div class="row mt-2">
                        <div class="col-2">
                            <div class="row">
                                <div class="col">
                                    <img src="../images/doctor.png" alt="..." class="rounded float-left">
                                </div>
                            </div>
                        </div>
                        <div class="col-8 mb-2">
                            <p class="m-0 border-bottom"><small>Specialization:</small> <br> <b> {{doctor.specialized.typeName}} - {{doctor.specialized.typeDescription}}</b></p>
                            <p class="m-0 border-bottom"><small>Clinic:</small> <br>
                                <b>
                                    <router-link class="special-link" :to="'clinic_'+doctor.clinic.id"> {{doctor.clinic.name}}</router-link>
                                </b>
                                <small class="text-muted"><br>{{doctor.address}}, {{doctor.city}}</small>
                            </p>
                            <p class="m-0"><small>Average rating: <b>{{doctor.averageRating}}</b></small></p>
                        </div>
                    </div>
                    <hr class="my-1">
                    <div class="row mt-4">
                        <div class="col-2"></div>
                        <form class="col" v-on:submit.prevent="rateDoctor" method="post">
                            <div class="row">
                                <div class="col align-self-center text-right pr-0">
                                    <label for="doctorRate" class="p-0 m-0">Rate:</label>
                                </div>
                                <div class="col-4">
                                    <select class="form-control pt-0 pb-0" id="doctorRate" v-model="doctorRate">
                                        <option>5</option>
                                        <option>4</option>
                                        <option>3</option>
                                        <option>2</option>
                                        <option>1</option>
                                    </select>
                                </div>
                                <div class="col-6 align-self-center text-left">
                                    <div class="row">
                                        <button type="submit" class="btn btn-sm btn-outline-primary pl-3 pr-3 w-special pt-2 pb-2">Submit</button>
                                        <button v-on:click="rateDoctorData(-1)" class="btn btn-sm btn-outline-danger pl-3 pr-3 w-special pt-2 pb-2 ml-2">Cancel</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="card m-1 pb-1" v-if="rateClinicF">
                <div class="card-body">
                    <h3 class="card-title m-0">{{clinic.name}}</h3>
                    <hr class="my-1">
                    <small class="card-subtitle m-0 text-muted">Address: {{clinic.address}}</small>
                    <p>Average rating: <span><b>{{clinic.averageRating}}</b></span></p>
                    <hr class="my-1">
                    <footer class="blockquote-footer"><small>{{clinic.description}}</small></footer>
                </div>
                <div class="card-body pt-0">
                    <hr class="my-1">
                    <div class="row mt-4">
                        <div class="col-2"></div>
                        <form class="col" v-on:submit.prevent="rateClinic" method="post">
                            <div class="row">
                                <div class="col align-self-center text-right pr-0">
                                    <label for="clinicRate" class="p-0 m-0">Rate:</label>
                                </div>
                                <div class="col-4">
                                    <select class="form-control pt-0 pb-0" id="clinicRate" v-model="clinicRate">
                                        <option>5</option>
                                        <option>4</option>
                                        <option>3</option>
                                        <option>2</option>
                                        <option>1</option>
                                    </select>
                                </div>
                                <div class="col-6 align-self-center text-left">
                                    <div class="row">
                                        <button type="submit" class="btn btn-sm btn-outline-primary pl-3 pr-3 w-special pt-2 pb-2">Submit</button>
                                        <button v-on:click="rateClinicData(-1)" class="btn btn-sm btn-outline-danger pl-3 pr-3 w-special pt-2 pb-2 ml-2">Cancel</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>    
        </div>
    </div>
</template>

<script>
export default {
    name: "procedureHistory",
    data: function() {
        return{
            reports : [],
            appointments : [],
            rateClinicF : false,
            rateDoctorF : false,
            doctor: Object,
            clinic: Object,
            doctorRate: undefined,
            clinicRate: undefined,
            clinicToRate: undefined,
            doctorToRate: undefined,
            appointmentToUpdate: undefined
        }
    }, 
    methods: {
        dateSort: function() {
            this.appointments.sort((a,b) => (a.dateOfProcedure > b.dateOfProcedure) ? 1 : -1);
        },
        doctorSort: function() {
            this.appointments.sort((a,b) => (a.doctor.firstName > b.doctor.firstName) ? 1 : -1);
        },
        clinicSort: function() {
            this.appointments.sort((a,b) => (a.doctor.clinic.name > b.doctor.clinic.name) ? 1 : -1);
        },
        procedureSort: function() {
            this.appointments.sort((a,b) => (a.procedureType.typeName > b.procedureType.typeName) ? 1 : -1);
        },
        rateClinicData: function(clinicID, appointmentID) {
            if(clinicID == -1) {
                this.rateClinicF = false;
            } else if (this.rateClinicF) {
                this.axios.get("http://localhost:8080/clinic/single/" + clinicID)
                .then(response => {
                    this.clinic = response.data;
                    this.clinicToRate = clinicID;
                    this.appointmentToUpdate = appointmentID;
                });
            } else {
                this.rateClinicF = true
                this.axios.get("http://localhost:8080/clinic/single/" + clinicID)
                .then(response => {
                    this.clinic = response.data;
                    this.clinicToRate = clinicID;
                    this.appointmentToUpdate = appointmentID;
                });
            }
        },
        rateDoctorData: function(doctorID, appointmentID) {
            if(doctorID == -1) {
                this.rateDoctorF = false;
            } else if (this.rateDoctorF) {
                this.axios.get("http://localhost:8080/doctor/" + doctorID)
                .then(response => {
                    this.doctor = response.data;
                    this.doctorToRate = doctorID;
                    this.appointmentToUpdate = appointmentID;
                });
            } else {
                this.rateDoctorF = true;
                this.axios.get("http://localhost:8080/doctor/" + doctorID)
                .then(response => {
                    this.doctor = response.data;
                    this.doctorToRate = doctorID;
                    this.appointmentToUpdate = appointmentID;
                });
            }
        },
        rateDoctor: function () {
            this.axios.put("http://localhost:8080/rate/doctor/"+ this.doctorRate + "/" + this.doctorToRate + "/" + this.appointmentToUpdate + "/" + localStorage.getItem('user_id'))
				.then(response => {
                    this.appointments = response.data;
                    this.rateDoctorF = false;
                });
        },
        rateClinic: function () {
            this.axios.put("http://localhost:8080/rate/clinic/"+ this.clinicRate + "/" + this.clinicToRate + "/" + this.appointmentToUpdate+ "/" + localStorage.getItem('user_id'))
				.then(response => {
                    this.appointments = response.data;
                    this.rateClinicF = false;
                });
        }
    },
    filters : {
        formatDate: function(date){
                if (!date) return '';
                let formatedDate = new Date(date);
                var month = ('0' + (formatedDate.getMonth() + 1)).slice(-2);
                var day = ('0' + formatedDate.getDate()).slice(-2);
                var year = formatedDate.getFullYear();
                return day + '.' + month + '.' + year + '.';
            }
    },
    mounted : function() {
		this.axios.get("http://localhost:8080/record/patient/" + localStorage.getItem('user_id'))
		.then(response => {
            this.reports = response.data.reports;
        });

        this.axios.get("http://localhost:8080/medical/procedure/patient/past", {
            params: {
                patient : localStorage.getItem('user_id')}
        })
        .then(response => {
            this.appointments = response.data;
            console.log(this.appointments)
        });
	}
}
</script>

<style>
.pointer {
    cursor: pointer;
}
.pointer:hover {
    color: #b4d8ff;
}
.w-special {
    width: 45%;
}

</style>