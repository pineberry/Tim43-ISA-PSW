<template>
	<div class="container p-0">
        <ul class="nav nav-tabs swatch-cyan justify-content-between">
            <li class="nav-item">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="http://localhost:8081/patientHome" role="button" aria-haspopup="true" aria-expanded="false">{{user}}</a>
                <div class="dropdown-menu">
					<router-link class="dropdown-item" to="patientProfile">Profile</router-link>
					<a class="dropdown-item bg-danger text-white" v-on:click="logout" href="http://localhost:8081">
                        Logout
                    </a>
                </div>
            </li>
            <li class="nav-item">
                <router-link class="nav-link pl-4 pr-4" to="patientAppointments">Appointments</router-link>
            </li>
            <li class="nav-item">
                <router-link class="nav-link pl-4 pr-4" to="">Medical record</router-link>
            </li>
            <li class="nav-item">
                <router-link class="nav-link pl-3 pr-3" to="">Procedure history</router-link>
            </li>
            <li class="nav-item">
                <router-link class="nav-link pl-5 pr-5" to="clinics">Clinics</router-link>
            </li>
            <li class="nav-item">
                <router-link class="nav-link pl-5 pr-5" to="doctors">Doctors</router-link>
            </li>
            <li class="nav-item">
                <router-link class="nav-link" to="searchAvailableDoctorsAtClinic"><img class="size" src="../images/search.svg"><span class="pl-2">Available</span></router-link>
            </li>
        </ul>
	</div>
</template>

<script>
    import SearchAvailableDoctorsAtClinic from '../views/SearchAvailableDoctorsAtClinic.vue';
    export default {
        name: "patientHome",
        data : function() {
            return {
                user : localStorage.getItem('user'),
                notificationCount: 0, 
                appointments : []
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