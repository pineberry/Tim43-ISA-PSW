<template>
	<div class="container p-0">
        <ul class="nav nav-tabs swatch-cyan justify-content-between">
            <li class="nav-item">
                <a class="nav-link dropdown-toggle" id="patient-profile-dropdown"  data-toggle="dropdown" href="" role="button" aria-haspopup="true" aria-expanded="false">{{user}}</a>
                <div class="dropdown-menu">
					<a class="dropdown-item btn-light" v-bind:class="{active: tab === 1}" v-on:click="tab = 1" >Profile</a>
					<a class="dropdown-item bg-danger text-white" v-on:click="logout" id="patient-profile-logout" href="http://localhost:8081">
                        Logout
                    </a>
                </div>
            </li>
            <li class="nav-item" ><a class="nav-link " v-bind:class="{active: tab === 2}" v-on:click="tab = 2">Appointments</a></li>
            <li class="nav-item" ><a class="nav-link " v-bind:class="{active: tab === 3}" v-on:click="tab = 3">Medical record</a></li>
            <li class="nav-item" ><a class="nav-link " v-bind:class="{active: tab === 4}" v-on:click="tab = 4">Procedure history</a></li>
            <li class="nav-item" ><a class="nav-link " v-bind:class="{active: tab === 5}" v-on:click="tab = 5">Clinics</a></li>
            <li class="nav-item" ><a class="nav-link " v-bind:class="{active: tab === 6}" v-on:click="tab = 6">Doctors</a></li>
            <li class="nav-item" ><a class="nav-link " v-bind:class="{active: tab === 7}" v-on:click="tab = 7" id="patient-profile-available"><img class="size" src="../images/search.svg"><span class="pl-2">Available</span></a></li>
<!-- 
            <li class="nav-item">
                <router-link class="nav-link pl-4 pr-4" to="patientAppointments">Appointments</router-link>
            </li>
            <li class="nav-item">
                <router-link class="nav-link pl-4 pr-4" to="medicalRecord">Medical record</router-link>
            </li>
            <li class="nav-item">
                <router-link class="nav-link pl-3 pr-3" to="procedureHistory">Procedure history</router-link>
            </li>
            <li class="nav-item">
                <router-link class="nav-link pl-5 pr-5" to="clinics">Clinics</router-link>
            </li>
            <li class="nav-item">
                <router-link class="nav-link pl-5 pr-5" to="doctors">Doctors</router-link>
            </li>
            <li class="nav-item">
                <router-link class="nav-link" to="searchAvailableDoctorsAtClinic"><img class="size" src="../images/search.svg"><span class="pl-2">Available</span></router-link>
            </li> -->
        </ul>
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 1}" >
                <PatientProfile/>
            </div>
            <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 2}" >
                <PatientAppointments/>
            </div>
            <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 3}" >
                <MedicalRecord/>
            </div>
            <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 4}" >
                <ProcedureHistory/>
            </div>
            <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 5}" >
                <Clinics/>
            </div>
            <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 6}" >
                <Doctors/>
            </div>
            <div role="tabpanel" class="tab-pane" v-bind:class="{active: tab === 7}" >
                <SearchAvailableDoctorsAtClinic/>
            </div>
        </div>
	</div>
</template>

<script>
    import PatientProfile from '../views/PatientProfile.vue';
    import PatientAppointments from '../components/PatientAppointments.vue';
    import MedicalRecord from '../views/MedicalRecord.vue';
    import ProcedureHistory from '../views/ProcedureHistory.vue';
    import Clinics from '../views/Clinics.vue';
    import Doctors from '../views/Doctors.vue';
    import SearchAvailableDoctorsAtClinic from "../views/SearchAvailableDoctorsAtClinic.vue";
    export default {
        components : {
            PatientProfile,
            PatientAppointments,
            MedicalRecord,
            ProcedureHistory,
            Clinics,
            Doctors,
            SearchAvailableDoctorsAtClinic
        },
        name: "patientHome",
        data : function() {
            return {
                user : localStorage.getItem('user'),
                tab: 1
            }
        },
        methods : {
            logout : function () {
                localStorage.removeItem("auth");
                localStorage.removeItem("email");
                localStorage.removeItem("user");
                localStorage.removeItem("user_id");
                localStorage.removeItem("typeOfUser");
            }
        }
    };
</script>

<style>

</style>