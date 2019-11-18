<template>
	<div class="container">
		<div class="row">
			<form class="col-6" v-on:submit.prevent="loginSubmit"method="post">
				<div class="form-group">
                    <label for="inputName">Name</label>
                    <input type="text" class="form-control" id="inputName" placeholder="First name" v-model="name">
                </div>
			</form>
		</div>
	</div>
</template>

<script type="text/javascript">
	
</script>

<style type="text/css">
	

</style>