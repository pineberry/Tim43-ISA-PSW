<template>
	<div class="container">
		<form @submit.prevent="sendRequest" accept-charset="UTF-8">
			<div class="row">
				<div class="form-group col-6">
				<label for="inputStartDate">Start date:</label>
				<input class="form-control" type="date" id="inputStartDate" v-model="startDate">
				</div>
				<div class="form-group col-6">
				<label for="inputEndDate">End date:</label>
				<input type="date" id="inputEndDate" class="form-control" v-model="endDate">
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</div>
		</form>
	</div>
</template>

<script>
export default {
	name: "VacationRequest",
	props : {
		doctor: Object, 
		nurse: Object
	},
	data : () => ({
		startDate: undefined,
		endDate: undefined, 
		status: 0,
	}),
	mounted() {
		/*if(localStorage.getItem("typeOfUser") == "doctor") {
			this.axios.get("http://localhost:8080/doctor/" + localStorage.getItem("user_id"))
				.then(response => { 
					this.doctor = response.data;
					this.nurse = null;
				})
				.catch(error => { console.log(error); })
		}
		if(localStorage.getItem("typeOfUser") == "nurse") {
			this.axios.get("http://localhost:8080/nurse/" + localStorage.getItem("user_id"))
				.then(response => {
					this.nurse = response.data;
					this.doctor = null;
				})
				.catch(error => { console.log(error); })
		}*/
	},
	methods : {
		sendRequest() {
			let request = {
				"doctorDTO" : this.doctor,
				"nurseDTO" : this.nurse,
				"startingDate" : this.startDate,
				"endingDate" : this.endDate,
				"status" : this.status
			}

			this.axios.post("http://localhost:8080/vacation/request/add", request)
				.then(response => { console.log(response.data); })
				.catch(error => { console.log(error); })
		}
	}
}
</script>

<style>

</style>