<template>
<<<<<<< HEAD
<div class="container row justify-content-center m-0">
	<div v-if="editDataF == false" class="col-10 card m-5 p-5">
		<div class="row justify-content-center">
			<p><small>Name: </small>{{user.firstName}} {{user.lastName}}</p>
		</div>
		<div class="row justify-content-center">
			<p><small>E-mail address: </small>{{user.email}}</p>
		</div>
		<div class="row justify-content-center">
			<p><small>Address: </small>{{user.address}} - {{user.city}}, {{user.state}}</p>
		</div>
		<div class="row justify-content-center">
			<p><small>Phone number: </small>{{user.phoneNumber}}</p>
		</div>
		<div class="row justify-content-center">
			<p><small>Insurance number: </small>{{user.healthCareNumber}}</p>
		</div>
		<button class="btn align-self-center w-50 btn-primary mt-2" v-on:click="editData">Edit data</button>
	</div>
	<div v-if="editDataF == true" class="col-10 card m-5 p-5">
		<form v-on:submit.prevent="updateUsersData">
			<div class="row">
				<div class="form-group col-6 m-2">
					<div class="row">
						<label class="m-0" for="firstName"><small>First name</small></label>
						<input type="text" name="firstName" id="firstName" class="form-control" v-model="user.firstName">
					</div>
					<div class="row">
						<label class="m-0" for="firstName"><small>Last name</small></label>
						<input type="text" name="lastName" id="lastName" class="form-control" v-model="user.lastName">
					</div>
					<div class="row">
						<label class="m-0" for="firstName"><small>Phonenumber</small></label>
						<input type="text" name="phoneNumber" id="phoneNumber" class="form-control" v-model="user.phoneNumber">
					</div>
				</div>
				<div class="form-group col m-2">
					<div class="row">
						<label class="m-0" for="firstName"><small>Address</small></label>
						<input type="text" name="address" id="address" class="form-control" v-model="user.address">
					</div>
					<div class="row">
						<label class="m-0" for="firstName"><small>City</small></label>
						<input type="text" name="city" id="city" class="form-control" v-model="user.city">
					</div>
					<div class="row">
						<label class="m-0" for="firstName"><small>State</small></label>
						<input type="text" name="state" id="state" class="form-control" v-model="user.state">
					</div>
				</div>
			</div>
			<div class="row justify-content-center">
				<button class="btn w-50 btn-primary mt-2" type="submit">Submit update</button>
			</div>
		</form>
	</div>
</div>
=======
  <div class="fluid row" v-if="user">
    <div v-if="editDataF == false" class="col-6 card m-5 p-5">
      <div class="row justify-content-start">
        <div class="col-6">
          <p class="text-secondary font-weight-bold">Name:</p>
        </div>
        <div class="col-6">
          <p class="font-weight-light">{{user.firstName}} {{user.lastName}}</p>
        </div>
      </div>
      <div class="row justify-content-start">
        <div class="col-6">
          <p class="text-secondary font-weight-bold">E-mail address:</p>
        </div>
        <div class="col-6">
          <p class="font-weight-light">{{user.email}}</p>
        </div>
      </div>
      <div class="row justify-content-start">
        <div class="col-6">
          <p class="text-secondary font-weight-bold">Password:</p>
        </div>
        <div class="col-6">
          <p class="font-weight-light">{{user.password}}</p>
        </div>
      </div>
      <div class="row justify-content-start">
        <div class="col-6">
          <p class="text-secondary font-weight-bold">Address:</p>
        </div>
        <div class="col-6">
          <p class="font-weight-light">{{user.address}} - {{user.city}}, {{user.state}}</p>
        </div>
      </div>
      <div class="row justify-content-start">
        <div class="col-6">
          <p class="text-secondary font-weight-bold">Phone number: </p>
        </div>
        <div class="col-6">
          <p class="font-weight-light">{{user.phoneNumber}}</p>
        </div>
      </div>
      <div class="row justify-content-start">
        <div class="col-6">
          <p class="text-secondary font-weight-bold">Insurance number:</p>
        </div>
        <div class="col-6">
          <p class="font-weight-light">{{user.healthCareNumber}}</p>
        </div>
      </div>
      <button class="btn btn-primary mt-2" v-on:click="editData">Edit data</button>
    </div>
    <div v-if="editDataF == true" class="col-6 card m-5 p-5">
      <form v-on:submit.prevent="updateUsersData">
        <div class="form-group row m-1">
          <label for="firstName">First name:</label>
          <input type="text" name="firstName" id="firstName" class="form-control" v-model="user.firstName">
        </div>
        <div class="form-group row m-1">
          <label for="firstName">Last name:</label>
          <input type="text" name="lastName" id="lastName" class="form-control" v-model="user.lastName">
        </div>
        <div class="form-group row m-1">
          <label for="firstName">Address:</label>
          <input type="text" name="address" id="address" class="form-control" v-model="user.address">
        </div>
        <div class="form-group row m-1">
          <label for="firstName">City:</label>
          <input type="text" name="city" id="city" class="form-control" v-model="user.city">
        </div>
        <div class="form-group row m-1">
          <label for="firstName">State:</label>
          <input type="text" name="state" id="state" class="form-control" v-model="user.state">
        </div>
        <div class="form-group row m-1">
          <label for="firstName">Phonenumber:</label>
          <input type="text" name="phoneNumber" id="phoneNumber" class="form-control" v-model="user.phoneNumber">
        </div>
        <button class="btn btn-primary mt-2" type="submit">Submit update</button>
      </form>
    </div>
  </div>
>>>>>>> ecf575e7cac1096797121689187dbacd97585603
</template>

<script>

export default {
name : "patientProfile",
data : function () {
	return {
	response : undefined,
	user : undefined,
	editDataF : false
	}
},
created: function() {
		this.axios.get("http://localhost:8080/patient/"+localStorage.getItem('user_id'))
		.then(response => {
				this.response = response
				this.user = this.response.data
			})
		.catch(error => {
			alert(error.response.data)
			})
},
methods : {
	editData : function() {
	this.editDataF = !this.editDataF
	},
	// submit : function () {
	//             var user = {
	//             	"firstName" : this.firstName,
	//               "lastName" : this.lastName,
	//               "email" : this.email,
	//               "password" : this.password,
	//                 "passwordF" : this.passwordF,
	//               "address" : this.address,
	//               "city" : this.city,
	//               "state" : this.state,
	//               "phoneNumber" : this.phoneNumber,
	//               "healthCareNumber" : this.healthCareNumber
	//             }
	//            console.log(user)
	//           this.axios.post('http://localhost:8080/register', user)
	//           .then(response =>
	//           {
	//                 this.response = response
	//                 console.log(this.response)  
	//           })
	//         }
	//     },
	updateUsersData : function() {
	console.log('test')
	var usersData = {
		"id" : localStorage.getItem('user_id'),
		"firstName" : this.user.firstName,
		"lastName" : this.user.lastName,
		"email" : this.user.email,
		"password" : this.user.password,
		"address" : this.user.address,
		"city" : this.user.city,
		"state" : this.user.state,
		"phoneNumber" : this.user.phoneNumber,
		"healthCareNumber" : this.user.healthCareNumber
	}
	this.axios.put('http://localhost:8080/patient/update', usersData)
		.then(response =>
		{
		this.response = response
		console.log(this.response)
		localStorage.setItem('user', this.user.firstName + ' ' + this.user.lastName);
		this.$router.go()
		})
	
	}
}
}
</script>

<style>

</style>