<template>
	<div class="container">
		<div class="jumbotron" v-for="req in requests" :key="req.id" v-show="req">
			<div v-if="req.status === 0">
				<p v-if="req.doctorDTO">
					{{req.doctorDTO.firstName}} {{req.doctorDTO.lastName}}
				</p>
				<p v-if="req.nurseDTO">
					{{req.nurseDTO.firstName}} {{req.nurseDTO.lastName}}
				</p>
				<p>Requested period for vacation: {{req.startingDate | formatDate}} - {{req.endingDate | formatDate}}</p>
				<p><textarea id="inputComment" rows="2" class="form-control" placeholder="Enter comment" v-model="comment"></textarea></p>
				<p>
					<button class="btn btn-primary" v-on:click="accept(req)">Accept</button>
					<button class="btn btn-outline-danger" v-on:click="deny(req)" style="margin-left: 5px">Deny</button>
				</p>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	name: "VacationRequests",
  data: () => ({
    requests: [],
    admin: Object,
	comment: undefined,
  }),
	filters: {
		formatDate: function(date){
			if (!date) return '';
			let formatedDate = new Date(date);
			var month = ('0' + (formatedDate.getMonth() + 1)).slice(-2);
			var day = ('0' + formatedDate.getDate()).slice(-2);
			var year = formatedDate.getFullYear();
			return year + '-' + month + '-' + day;
		}
	},
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
  },
  methods: {
		accept: function (vacationRequest) {
			this.axios.put("http://localhost:8080/vacation/request/accept", vacationRequest)
				.then(response => {
					let index = this.requests.indexOf(vacationRequest);
					this.requests.splice(index, 1);
				})
		},
	  deny: function (vacationRequest) {
			if (this.comment === undefined || this.comment.trim() === ''){
				alert('You have to write comment before denying request!')
			} else {
				this.axios.put("http://localhost:8080/vacation/request/deny", vacationRequest, {
					params: {
						comment: this.comment
					}
				}).then(response => {
					let index = this.requests.indexOf(vacationRequest);
					this.requests.splice(index, 1);
				})
			}
	  }
  }
};
</script>

<style>
</style>