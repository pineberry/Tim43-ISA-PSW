<template>
	<div class="container">
			<h4>New Nurse</h4>
			 <form @submit.prevent="addNurse" accept-charset="UTF-8" method="post">
          <div class="row">
						<div class="col-6">
							<div class="form-group">
								<label for="inputName">Name:</label>
								<input id="inputName" type="text" class="form-control" placeholder="Enter name" v-model="firstName">
							</div>
							<div class="form-group">
								<label for="inputSurname">Surname:</label>
								<input id="inputSurname" type="text" class="form-control" placeholder="Enter surname" v-model="lastName">
							</div>
							<div class="form-group">
								<label for="inputAddress">Address:</label>
								<input id="inputAddress" type="text" class="form-control" placeholder="Enter address" v-model="address">
							</div>
							<div class="form-group">
								<label for="inputCity">City:</label>
								<input id="inputCity" type="text" class="form-control" placeholder="Enter city" v-model="city">
							</div>
							<div class="form-group">
								<label for="inputState">State:</label>
								<input id="inputState" type="text" class="form-control" placeholder="Enter state" v-model="state">
							</div>
						</div>
						<div class="col-6">
							<div class="form-group">
								<label for="inputEmail">E-mail:</label>
								<input id="inputEmail" type="email" class="form-control" placeholder="Enter e-mail" v-model="email">
							</div>
							<div class="form-group">
								<label for="inputPhone">Phone number:</label>
								<input id="inputPhone" type="text" class="form-control" placeholder="Enter phone number" v-model="phoneNumber">
							</div>
						</div>
					</div>
					<button type="submit" class="btn btn-primary">Add</button>
        </form>
	</div>
</template>

<script>
export default {
	name : "AddNurse",
  data: () => ({
    firstName: undefined,
    lastName: undefined,
    email: undefined,
    phoneNumber: undefined,
    address: undefined,
    city: undefined,
    state: undefined,
    clinic: null,
		admin: Object
	}),
	mounted() {
		if(localStorage.getItem("typeOfUser") == "clinicAdministrator") {
			this.axios.get("http://localhost:8080/clinic/admin/" + localStorage.getItem("user_id"))
				.then(response => { 
					this.admin = response.data; 
					this.clinic = this.admin.clinic;
				})
				.catch(error => {console.log(error);})
		}
	},
	methods: {
		addNurse() {
			let nurse = {
				"firstName" : this.firstName,
				"lastName" : this.lastName,
				"email" : this.email,
				"phoneNumber" : this.phoneNumber,
				"address" : this.address,
				"city" : this.city,
				"state" : this.state,
				"clinic" : this.clinic,
			}
			
			this.axios.post("http://localhost:8080/nurse/add",  nurse)
				.then(response => {this.$router.push("/");})
				.catch(error => {console.log(eroor);})
		}
	}
};
</script>

<style>
</style>