<template>
  <div class="container">
    <form
      @submit.prevent="bookOperation"
      class="col-6"
    >
      <div class="form-group">
        <label for="selectDoctors">Doctors</label>
        <select
          class="custom-select"
          v-model="selectedDoctors"
          id="selectDoctors"
          multiple
        >
          <option
            v-for="doctor in doctors"
            :key="doctor.id"
            :value="doctor.id"
          >{{doctor.firstName}} {{doctor.lastName}}</option>
        </select>
      </div>
      <button
        class="btn btn-primary"
        type="submit"
      >Book</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "ChoseDoctors",
  data: () => ({
    doctors: Object,
    admin: Object,
    procedureId: undefined,
    roomId: undefined,
    selectedDoctors: []
  }),
  mounted() {
    if (localStorage.getItem("typeOfUser") === "clinicAdministrator") {
      if (this.$route.query.proc != undefined && this.$route.query.room != undefined) {
        this.procedureId = this.$route.query.proc;
        this.roomId = this.$route.query.room;
      }
      this.axios
        .get(
          "http://localhost:8080/clinic/admin/" +
            localStorage.getItem("user_id")
        )
        .then(response => {
          this.admin = response.data;
          this.doctors = this.admin.clinic.doctors;
          console.log(this.doctors);
        });
    }
  },
  methods : {
      bookOperation() {
          let operation = {
            "procedureId" : this.procedureId,
	        "roomId" : this.roomId, 
	        "doctorsId" : this.selectedDoctors
          }
          this.axios.put("http://localhost:8080/medical/procedure/book/operation", operation)
            .then(reponese => {
                this.$router.push("/");
            })
            .catch(error => {console.log(error);})

      }
  }
};
</script>

<style>
</style>