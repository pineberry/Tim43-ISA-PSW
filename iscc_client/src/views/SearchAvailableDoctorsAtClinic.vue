<template>
	<div id="container">
		<div class="row">
			<form class="col-6" v-on:submit.prevent="search" method="post">
				<div class="form-group">
					<label for="date">Date for the appointment</label>
					<input type="date" class="form-control" id="date" v-model="date">
				</div>
				<div class="form-group">
					<label for="typeOfProcedure">Procedure type</label>
					<select id="typeOfProcedure" class="form-control" v-model="typeOfProcedure">
						<option v-for="procedure in procedureTypes" :key="procedure.id" :value="procedure">{{procedure.typeName}}</option>
					</select>
				</div>
				<div v-if="searchAdvancedF == true">
					<div class="form-group">
						<label for="location">Location</label>
						<input type="text" class="form-control" id="location" v-model="location">
					</div>
					<div class="form-group">
						<label for="rating">Rating</label>
						<input type="number" min="0" max="5" class="form-control" id="rating" v-model="rating">
					</div>
					
					<a v-on:click="showSearch" href="#">Remove advanced.</a>
				</div>
				<div v-if="searchAdvancedF == false">
					<a v-on:click="showSearch" href="#">Advanced</a>
				</div>
				<button type="submit" class="btn btn-primary mb-2">Search</button>
			</form>
		</div>
		<div class="row" v-if="showResult == true">
			<div class="jumbotron col-12" v-for="clinic in clinics" :key="clinic.id">
				<h2><span><a v-on:click="showDoctors" href="#">{{clinic.name}}</a></span></h2>
				<p>Address: {{clinic.address}}</p>
				<small>{{clinic.description}}</small> 
				<p>Ocena: <span><b>{{clinic.averageRating}}</b></span></p>
				<div v-if="showDoctorsF == true">
					<h4>Available doctors:</h4>
					<div v-for="doctor in doctors" :key="doctor.id">
						<div v-if="doctor.clinic.id == clinic.id">
							<b>{{doctor.firstName}} {{doctor.lastName}}</b>
							<p>Ocena: <span><b>{{doctor.averageRating}}</b></span></p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>
<script>


	export default {
		name: "searchAvailable",
		data: function(){
			return {
				date: undefined,
				typeOfProcedure: undefined,
				procedureTypes: [],
				searchAdvancedF: false,
				location: "",
				rating: 0,
				response: undefined,
				clinics: [],
				doctors: [],
				showResult: false,
				showDoctorsF: false
			}
		},
		mounted: function() {
			this.axios.get("http://localhost:8080/procedure/type/all")
			.then(response => {this.procedureTypes = response.data})
			.catch(error => {alert(error.response.data)})
		},
		methods: {
			search: function() {
				var searchParameters = {
					date : this.date,
					typeOfProcedure : this.typeOfProcedure,
					location : this.location,
					rating : this.rating

				}
				console.log(searchParameters)
				this.axios.get("http://localhost:8080/search/clinic", {
					params: {
						date : this.date,
						typeOfProcedure : this.typeOfProcedure,
						location : this.location,
						rating : this.rating
					}
				})
				.then(response => {
					this.response = response
					this.clinics = this.response.data.clinics
					this.doctors = this.response.data.doctors
					this.showResult = true
					console.log(this.response)
				})
				.catch(error => {alert(error.response.data)})
			},
			showSearch : function () {
				this.location = null
				this.rating = null
				this.searchAdvancedF = !this.searchAdvancedF
			},
			showDoctors : function () {
				this.showDoctorsF = !this.showDoctorsF
			}
		}
	}
</script>