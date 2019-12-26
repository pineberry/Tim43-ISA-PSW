<template>
	<div class="container">
		<div class="row m-0 mt-2">
			<div class="card m-1 w-05" v-for="clinic in clinics" :key="clinic.id">
				<a class="clinic-card" href="">
					<div class="card-body">
						<h3 class="card-title m-0">{{clinic.name}}</h3>
						<hr class="my-1">
						<p class="card-subtitle m-0 text-muted"><small>Address: {{clinic.address}}</small></p>
						<p>Average rating: <span><b>{{clinic.averageRating}}</b></span></p>
						<hr class="my-1">
						<footer class="blockquote-footer"><small>{{clinic.description}}</small></footer>
					</div>
				</a>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	name: "clinics",
	data : function () {
		return {
			clinics : []
		}
	}, 
	mounted : function() {
		this.axios.get("http://localhost:8080/clinic/clinics")
		.then(response => {
			this.clinics = response.data
		})
	}, 
	methods : {

	}

}
</script>

<style>
.clinic-card p{
	color: #343a40 !important;
}
.clinic-card h3{
	color: #007BFF !important;
}
.clinic-card:hover{
	background-color: #cce1f7;
	
}
.clinic-card:hover h3, .clinic-card:hover p{
	color: #f8f9fa !important;
}
</style>