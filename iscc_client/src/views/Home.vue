<template>
  <div class="home">
    <!-- <div class="fluid" v-if="user">
      <PatientHome />
    </div> -->
    <div class="fluid" v-if="!user">
      <Login />
    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  name: "home",
  components: {
    // PatientHome: () => import("../components/PatientHome.vue"),
    Login: () => import("../components/Login.vue")
  },
  data : function() {
    return {
      user : localStorage.getItem('user')
    }
  },
  methods: {
    logout: function() {
      localStorage.removeItem("auth");
      alert("Izlogovani ste");
    }
  }
};
</script>
