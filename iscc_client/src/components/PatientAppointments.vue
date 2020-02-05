<template>
    <div class="container p-0 row m-0">
        <div class="col mt-1 accordion" id="appointments">
            <div class="row justify-content-between m-1">
                <a class="btn btn-outline-custom btn-sm mr-1 w-05" data-toggle="collapse" href="#booked" aria-expanded="true" aria-controls="booked">Booked</a>
                <a class="btn btn-outline-custom btn-sm ml-1 w-05" data-toggle="collapse" href="#review" aria-expanded="false" aria-controls="review">Review</a>
            </div>
            <div class="collapse show" id="booked" data-parent="#appointments">
                <div class="row m-0" v-for="appoint in appointments" :key="appoint.id">
                    <div class="col card mt-1 p-3" v-if="appoint.booked">
                        <div class="row m-0">
                            <div class="col p-0">
                                <p class="m-0">Date: <b>{{appoint.dateOfProcedure | formatDate}}</b></p>
                                <p class="m-0">Room: <b>{{appoint.medicalRoom.roomName}}-{{appoint.medicalRoom.roomNumber}}</b></p>
                            </div>
                            <div class="col p-0">
                                <p class="m-0">Medic: <span><a data-toggle="collapse" :href="'#doctor'+appoint.id" aria-expanded="false" :aria-controls="'doctor'+appoint.id">
                                    dr. {{appoint.doctor.firstName}} {{appoint.doctor.lastName}}
                                </a></span></p>
                                <div class="collapse" :id="'doctor'+appoint.id">
                                    <small>Specialization: {{appoint.doctor.specialized.typeName}} <br> 
                                    <span class="text-muted">Rating: <b>{{appoint.doctor.averageRating}}</b>, working time: <b>{{appoint.doctor.workingtimeStart}} - {{appoint.doctor.workingtimeEnd}}</b></span></small>
                                    <hr class="my-1">
                                </div>
                                <p class="m-0">At clinic: <span><a class="font-weight-bold" data-toggle="collapse" :href="'#clinic'+appoint.id" aria-expanded="false" :aria-controls="'clinic'+appoint.id">
                                        {{appoint.doctor.clinic.name}}
                                </a></span></p>
                                <div class="collapse" :id="'clinic'+appoint.id">
                                    <small>{{appoint.doctor.clinic.description}} <br>
                                    <span class="text-muted">Rating: <b>{{appoint.doctor.clinic.averageRating}}</b>, address: <b>{{appoint.doctor.clinic.address}}</b></span>
                                    </small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="collapse p-2 justify-content-center" id="review" data-parent="#appointments">
                <p class="m-2"><b>You need to review the following appointment suggestions:</b></p>
                <div class="row m-0" v-for="appoint in appointments" :key="appoint.id">
                    <div class="col card mt-1 p-3" v-if="!appoint.booked">
                        <div class="row m-0">
                            <div class="col p-0">
                                <p class="m-0">Date: <b>{{appoint.dateOfProcedure | formatDate}}</b></p>
                                <p class="m-0">Room: <b>{{appoint.medicalRoom.roomName}}-{{appoint.medicalRoom.roomNumber}}</b></p>
                                
                            </div>
                            <div class="col p-0">
                                <p class="m-0">Medic: <span><a data-toggle="collapse" :href="'#doctor'+appoint.id" aria-expanded="false" :aria-controls="'doctor'+appoint.id">
                                    dr. {{appoint.doctor.firstName}} {{appoint.doctor.lastName}}
                                </a></span></p>
                                <div class="collapse" :id="'doctor'+appoint.id">
                                    <small>Specialization: {{appoint.doctor.specialized.typeName}} <br> 
                                    <span class="text-muted">Rating: <b>{{appoint.doctor.averageRating}}</b>, working time: <b>{{appoint.doctor.workingtimeStart}} - {{appoint.doctor.workingtimeEnd}}</b></span></small>
                                    <hr class="my-1">
                                </div>
                                <p class="m-0">At clinic: <span><a class="font-weight-bold" data-toggle="collapse" :href="'#clinic'+appoint.id" aria-expanded="false" :aria-controls="'clinic'+appoint.id">
                                        {{appoint.doctor.clinic.name}}
                                </a></span></p>
                                <div class="collapse" :id="'clinic'+appoint.id">
                                    <small>{{appoint.doctor.clinic.description}} <br>
                                    <span class="text-muted">Rating: <b>{{appoint.doctor.clinic.averageRating}}</b>, address: <b>{{appoint.doctor.clinic.address}}</b></span>
                                    </small>
                                </div>
                            </div>
                        </div>
                        <hr class="my-2">
                        <div class="row justify-content-end mr-2">
                            <button class="btn btn-outline-primary w-25" v-on:click="confirm(appoint)">Confirm</button>
                            <button class="btn btn-outline-danger w-25 ml-2" v-on:click="deny(appoint.id, appoint.patient.id)">Deny</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import moment from 'moment';

export default {
    name: "patientAppointments",
    data : function (){
        return {
            appointments : []
        }
    }, 
    filters : {
        formatDate: function(date){
                if (!date) return '';
                let formatedDate = new Date(date);
                var month = ('0' + (formatedDate.getMonth() + 1)).slice(-2);
                var day = ('0' + formatedDate.getDate()).slice(-2);
                var year = formatedDate.getFullYear();
                var hour = formatedDate.getHours();
                return day + '.' + month + '.' + year  + '.' + ' - ' + hour + ':00h';
            }
    },
    mounted: function () {
        this.axios.get("http://localhost:8080/medical/procedure/patient/relevant", {
            params: {
                patient : localStorage.getItem('user_id')}
        })
        .then(response => {
            this.appointments = response.data;
            console.log(response.data)
        });
    }, 
    methods : {
        confirm : function(appointment) {
            this.axios.put("http://localhost:8080/medical/procedure/appointment", appointment)
            .then(response => {
                this.appointments = response.data;
                console.log(response.data)
            });
        },
        deny : function(appointmentID, patientID) {
            this.axios.delete("http://localhost:8080/medical/procedure/appointment/" + appointmentID + "/" + patientID)
            .then(response => {
                this.appointments = response.data;
                console.log(response.data)
            });
        }
    }
}
</script>

<style scoped>

.btn-outline-custom {
    color: #363636;
    border-bottom: 2px solid #007bff;
    background-color: #f9f9f9;
}
.btn-outline-custom:focus {
	outline: 0;
	-webkit-box-shadow: 0 0 0 0;
	box-shadow: 0 0 0 0;
}
.btn-outline-custom:hover {
	color: #007bff;
}


</style>