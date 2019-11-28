<template>
  <div class="home">
	<button v-on:click="logout" class="btn btn-primary mb-2">Logout</button>
  	<Login/>
  	<p class="nav-item nav-link">To register click <span><router-link to="/register">here</router-link></span>.</p>  	
  	<router-view />
  </div>
</template>

<script>
import Register from "@/components/Register.vue";
import Login from "@/components/Login.vue";

export default {
  name: "home",
  components: {
  	Login
  },
  methods : {
        	logout : function () {
        		localStorage.removeItem("auth");
        		alert("Izlogovani ste");
        	}
        }
};
</script>
