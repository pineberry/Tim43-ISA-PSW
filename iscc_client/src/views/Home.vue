<template>
  <div class="home">
  	<Register/>
  	<Login/>
  </div>
</template>

<script>
import Register from "@/components/Register.vue";
import Login from "@/components/Login.vue";

export default {
  name: "home",
  components: {
  	Register,
  	Login
  }
};
</script>
