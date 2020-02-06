<template>
	<div class="container">
		<div class="row jumbotron" v-for="req in requests" :key="req.id">
			<div v-if="req.doctorDTO">
				{{req.doctorDTO.firstName}} {{req.doctorDTO.lastName}}
			</div>
			<div v-if="req.nurseDTO">
				{{req.nurseDTO.firstName}} {{req.nurseDTO.lastName}}
			</div>
		</div>
	</div>
</template>

<script>
export default {
	name: "VacationRequests",
  data: () => ({
    requests: Object,
    admin: Object
  }),
  mounted() {
   	if(localStorage.getItem("typeOfUser") == "clinicAdministrator") {
			this.axios.get("http://localhost:8080/clinic/admin/" + localStorage.getItem("user_id"))
				.then(response => { 
					this.admin = response.data; 
					this.axios.get("http://localhost:8080/vacation/request/clinic/" + this.admin.clinic.id)
						.then(response => { this.requests = response.data; console.log(this.requests);})
						.catch(error => { console.log(error); })
				})
				.catch(error => {console.log(error);})
		}
  }
};
</script>

<style>
</style>