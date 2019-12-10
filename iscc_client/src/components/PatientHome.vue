<template>
	<div class="container">
        <ul class="nav nav-tabs swatch-cyan">
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
                <router-link class="nav-link" to="">Clinics</router-link>
            </li>
            <li class="nav-item">
                <router-link class="nav-link" to="searchDoctors">Doctors</router-link>
            </li>
            <li class="nav-item">
                <router-link class="nav-link" to="">Procedure history</router-link>
            </li>
            <li class="nav-item">
                <router-link class="nav-link" to="">Medical record</router-link>
            </li>
        </ul>
        <div v-if="searchF == true">
            <SearchAvailableDoctorsAtClinic/>
            <a v-on:click="showSearch" href="#">Remove form.</a>
        </div>
        <div v-if="searchF == false">
            <p>If you want to schedule an examintation click <a v-on:click="showSearch" href="#">here</a>.</p>
        </div>
	</div>
</template>

<script>

    import SearchAvailableDoctorsAtClinic from '../views/SearchAvailableDoctorsAtClinic.vue';

    export default {
        name: "patientHome",
        components : {
            SearchAvailableDoctorsAtClinic
        },
        data : function() {
            return {
                user : localStorage.getItem('user'),
                searchF : false
            }
        },
        methods : {
            logout : function () {
                localStorage.removeItem("auth");
                localStorage.removeItem("email");
                localStorage.removeItem("user");
                localStorage.removeItem("user_id");
                localStorage.removeItem("typeOfUser");
            },
            showSearch : function () {
                this.searchF = !this.searchF
            }
        }
    };
</script>

<style>

</style>