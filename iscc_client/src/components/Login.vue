<template>
	<div class="container">
		<div class="row">
			<form class="col-6" v-on:submit.prevent="submit" method="post">
				<div class="form-group">
                    <label for="email">Email address</label>
                    <input type="text" class="form-control" id="email" v-model="email">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" v-model="password">
                </div>
                <button type="submit" class="btn btn-primary mb-2">Login</button>
			</form>
		</div>
	</div>
</template>

<script>
	export default{
        name: "Register"
    }
</script>

<style>
	

</style>