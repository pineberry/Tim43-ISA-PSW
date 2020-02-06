<template>
    <div class="container">
        <div class="col card m-1 mt-2">
            <div class="row card-title justify-content-center m-0">
                <p class="h3 m-0 mt-2 text-primary">{{clinic.name}}</p>
            </div>
            <div class="row card-title justify-content-center m-0">
                <p class="text-muted m-0">-{{clinic.description}}-</p>
            </div>
            <hr class="my-2">
            <div class="row m-0" v-if="!availableF">
                <div class="w-05 card m-1" v-for="doctor in doctors" :key="doctor.id">
                    <div class="card-body">
                        <h3 class="card-title">dr. {{doctor.firstName}} {{doctor.lastName}}</h3>
                        <hr class="my-1">
                        <div class="row mt-2">
                            <div class="col-3">
                                <div class="row">
                                    <div class="col">
                                        <img src="../images/doctor.png" alt="..." class="rounded float-left">
                                    </div>
                                </div>
                            </div>
                            <div class="col-8">
                                <p class="m-0 border-bottom"><small>Specialization:</small> <br> <b> {{doctor.specialized.typeName}} - {{doctor.specialized.typeDescription}}</b></p>
                                <p class="m-0 border-bottom"><small>Clinic:</small> <br>
                                    <b>
                                        <a class="special-link" :href="'http://localhost:8081/clinic_'+doctor.clinic.id"> {{doctor.clinic.name}}</a>
                                    </b>
                                    <small class="text-muted"><br>{{doctor.address}}, {{doctor.city}}</small>
                                </p>
                                <p class="m-0"><small>Average rating: <b>{{doctor.averageRating}}</b></small></p>
                            </div>
                        </div>
                        <div class="text-right">
                            <router-link class="btn mt-2 btn-outline-primary btn-sm" :to="'scheduling_'+doctor.id">Request an appointment</router-link>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row m-0" v-if="availableF">
                <div class="card m-1 w-100" v-for="appoint in availableAppointments" :key="appoint.id">
                    <div class="card-body col">
                        <div class="row m-0">
                            <div class="col p-0">
                                <p class="m-0">Date: <b>{{appoint.dateOfProcedure | formatDate}}</b></p>
                                <p class="m-0">Room: <b>{{appoint.medicalRoom.roomName}}-{{appoint.medicalRoom.roomNumber}}</b></p>
                                <p class="m-0">Price: <b>${{appoint.procedureType.price}}</b> </p>
                                <p class="m-0">Discount: <b>{{appoint.dicount}}%</b> </p>
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
                            <div class="col-1 p-0 align-self-end">
                                <button class="btn btn-primary" v-on:click="confirm(appoint)">Book</button>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <hr class="my-2">
            <div class="row m-0">
                <p class="m-0">Clinic rating: <b>{{clinic.averageRating}}</b></p>
            </div>
            <div class="row m-0">
                <p class="m-0 mb-2">Address: <b>{{clinic.address}}</b></p>
            </div>
            <hr class="my-2">
            <div class="row m-0">
                <button v-on:click="available()" class="btn btn-primary w-50 mb-2">
                    <span v-if="!availableF">Available examination appointments</span>
                    <span v-if="availableF">ShowDoctors</span>
                </button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "clinic",
    data : function() {
        return {
            clinic : Object,
            doctors : [], 
            clinicID : undefined,
            availableAppointments: [],
            availableF: false
        }
    },
    methods : {
        available: function() {
            this.availableF = !this.availableF;
        },
        confirm : function(appointment) {
            this.axios.put("http://localhost:8080/medical/procedure/predefined_appointment/" + localStorage.getItem('user_id'), appointment)
            .then(response => {
                this.$router.push('/patientAppointments');
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
                var hour = formatedDate.getHours();
                return day + '.' + month + '.' + year  + '.' + ' - ' + hour + ':00h';
            }
    },
    mounted : function() {
        this.clinicID = this.$route.params.id;
        this.axios.get("http://localhost:8080/clinic/single/" + this.clinicID)
        .then(response => {
            //console.log(response);
            this.clinic = response.data;
            this.doctors = this.clinic.doctors;
        });

        this.axios.get("http://localhost:8080/medical/procedure/predefined/" + this.clinicID)
        .then(response => {
            this.availableAppointments = response.data;
            console.log(this.availableAppointments);
        });
    }
}
</script>

<style>

</style>