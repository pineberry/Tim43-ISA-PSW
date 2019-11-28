<template>
	<div class="container">
		<p>Welcome, {{user}}!</p> 
        <div id="searchAvailable"> </div>
        <div v-if="searchF == true">
            <SearchAvailableDoctorsAtClinic/>
            <a v-on:click="showSearch" href="#">Remove form.</a>
        </div>
        <div v-if="searchF == false">
            <p>If you want to schedule an examintation click <a v-on:click="showSearch" href="#">here</a>.</p>
        </div>
        
        
        <p>To logout from the system: </p><button v-on:click="logout" class="btn btn-primary mb-2">Logout</button>
        <router-view />
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
                alert("Izlogovani ste");
                this.$router.push('/')
            },
            showSearch : function () {
                this.searchF = !this.searchF
            }
        }
    };
</script>

<style>

</style>