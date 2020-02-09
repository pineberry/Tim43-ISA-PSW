<template>
	<div id="container">
		<div class="row">
			<form class="col" v-on:submit.prevent="search" method="post">
				<div class="row">
					<div class="col-6">
						<div class="form-group m-0">
							<label class="m-0" for="search-clinic-doctor-date"><small>Date</small></label>
							<input type="date" :min="today" class="form-control" id="search-clinic-doctor-date" v-model="date">
						</div>
						<div v-if="searchAdvancedF == true" class="form-group m-0">
							<label class="m-0" for="location"><small>Location</small></label>
							<input type="text" class="form-control" id="search-clinic-doctor-location" v-model="location">
						</div>
					</div>
					<div class="col-6">
						<div class="form-group m-0">
							<label class="m-0" for="typeOfProcedure"><small>Specialization</small></label>
							<select id="search-clinic-doctor-typeOfProcedure" class="form-control" v-model="typeOfProcedure">
								<option v-for="procedure in procedureTypes" :key="procedure.id" :value="procedure">{{procedure.typeName}}</option>
							</select>
						</div>
						<div v-if="searchAdvancedF == true" class="form-group m-0">
							<label class="m-0" for="rating"><small>Rating</small></label>
							<input type="number" min="0" max="5" class="form-control" id="rating" v-model="rating">
						</div>
					</div>
				</div>
				<div class="row mt-2 m-0 justify-content-between">
					<div v-if="searchAdvancedF == true">
						<a class="ml-2 m-1" v-on:click="showSearch" href="#"><img class="small_img rotate" src="../images/down-button.svg" alt="Basic"></a>
					</div>
					<div v-if="searchAdvancedF == false">
						<a class="ml-2 m-1" v-on:click="showSearch" href="#"><img class="small_img" src="../images/down-button.svg" alt="Advanced"></a>
					</div>
					<button type="submit" class="btn btn-primary mb-2" id="search-clinic-doctor-btn-search">Search</button>
				</div>
			</form>
		</div>
		<div class="row m-0" v-if="showResult == true" id="clinics">
			<div class="card mb-1 col-12" v-for="clinic in clinics" :key="clinic.id">
				<div class="card-body">
					<h3 class="card-title m-0"><a data-toggle="collapse" :href="'#show'+clinic.id" aria-expanded="true" :aria-controls="'show'+clinic.id">{{clinic.name}}</a></h3>
					<p class="card-subtitle m-0 text-muted"><small>Address: {{clinic.address}}</small></p>
					<p>Average rating: <span><b>{{clinic.averageRating}}</b></span></p>
					<hr class="my-1">
					<div class="collapse" :id="'show'+clinic.id" data-parent="#clinics">
						<p>Available doctors:</p>
						<div class="row m-1" id="search-clinic-doctor-available-doctors">
							<div class="card border-primary p-2 w-025 mr-1 " v-for="doctor in filteredDoctors(clinic.id)" :key="doctor.id">
								<div class="row justify-content-center">
									<div class="col-6">
										<img src="../images/doctor.png" alt="..." class="rounded float-left">
									</div>
								</div>
								<div class="row justify-content-center">
									<div v-if="doctor.clinic.id == clinic.id">
										<div class="row justify-content-center">
											<p class="card-title m-0"><b>{{doctor.firstName}} {{doctor.lastName}}</b></p>
										</div>
										<hr class="my-1">
										<p class="card-subtitle m-0 text-muted">
											<small>
												Average rating: <span><b>{{doctor.averageRating}}</b></span>
											</small>
										</p>
										<p class="card-subtitle m-0 text-muted">
											<small>
												Working time: <span><b>{{doctor.workingtimeStart}} - {{doctor.workingtimeEnd}}</b></span>
											</small>
										</p>
										<router-link class="btn mt-2 btn-outline-primary btn-sm" :to="'scheduling_'+doctor.id+'_'+date">Request an appointment</router-link>
									</div>
								</div>
							</div>
						</div>
						<hr class="my-1">
					</div>
					<footer class="blockquote-footer"><small>{{clinic.description}}</small></footer>
				</div>
			</div>
		</div>
	</div>
</template>
<script>


	export default {
		name: "searchClinics",
		data: function(){
			return {
				clinics: [],
				doctors: [],
				procedureTypes: [],
				date: undefined,
				today: undefined,
				typeOfProcedure: undefined,
				location: "",
				rating: 0,
				response: undefined,
				searchAdvancedF: false,
				showResult: false,
				showDoctorsF: undefined
			}
		},
		mounted: function() {
			this.today = new Date();
        	this.today = this.today.getFullYear() + '-' + ('0' + (this.today.getMonth() + 1)).slice(-2) + '-' + ('0' + this.today.getDate()).slice(-2);
			this.axios.get("http://localhost:8080/procedure/type/all")
			.then(response => {this.procedureTypes = response.data})
			.catch(error => {alert(error.response.data)})
		},
		methods: {
			search: function() {
				this.axios.get("http://localhost:8080/search/clinic", {
					params: {
						date : this.date,
						typeOfProcedure : this.typeOfProcedure.typeName,
						location : this.location,
						rating : this.rating
					}
				})
				.then(response => {
					this.clinics = response.data.clinics
					this.doctors = response.data.doctors
					this.showResult = true
					console.log(response)
				})
				.catch(error => {alert(error.response.data)})
			},
			showSearch : function () {
				this.location = ""
				this.rating = 0
				this.searchAdvancedF = !this.searchAdvancedF
			},
			showDoctors : function (clinicId) {
				if (clinicId != this.showDoctorsF) {
					this.showDoctorsF = clinicId
				} else {
					this.showDoctorsF = undefined
				}
			},
			filteredDoctors: function(clinicId) {
				if(clinicId) {
					return this.doctors.filter((doctor) => {
						return doctor.clinic.id == clinicId;
					})
				} else {
					return null;
				}
			}
		}
	}
</script>
<style>
.small_img {
	width: 24px;
	height: auto;
}
.rotate {
	transform: rotate(180deg);
}
</style>