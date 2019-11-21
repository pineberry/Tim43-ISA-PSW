<template>
	<div class="container">
		<div class="row">
			<form class="col-6" v-on:submit.prevent="submit" method="post">
				<div class="form-group">
                    <label for="emailAddress">Email address</label>
                    <input type="text" class="form-control" id="emailAddress" v-model="emailAddress">
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
        name: "Register",
        data : function () {
            return{
                emailAddress : undefined,
                password : undefined,
                auth : null,
                jwt : null
            }
        },
        methods : {
        	submit : function () {
        		var auth = {
        			"emailAddress" : this.emailAddress,
        			"password" : this.password
        		}
        		this.axios.post('http://localhost:8080/authenticate', auth)
        		.then(response => 
        		{
        			this.auth = response
        			this.jwt = this.auth.data.jwt
	        		if (this.jwt != null) 
	        		{
        				this.$router.push('/addClinic')
        				this.axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.jwt
        				localStorage.setItem('auth', 'Bearer ' + this.jwt)
	        		}
	        		else 
	        		{
	        			this.$router.push('/error')
	        		}
        		})
        	}
        }
    }
</script>

<style>
	

</style>