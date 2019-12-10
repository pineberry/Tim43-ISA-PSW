<template>
	<div class="container">
		<div class="m-5">
			<form class="col-5" v-on:submit.prevent="submit" method="post">
				<div class="form-group">
                    <label for="emailAddress">Email address</label>
                    <input type="text" class="form-control" id="emailAddress" v-model="emailAddress">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" v-model="password">
                </div>
                <div class="row justify-content-between m-1">
					<small class="">To register click <span><router-link to="/register">here</router-link></span>.</small>
					<button type="submit" class="btn btn-primary">Login</button>
				</div>
			</form>
		</div>
		<p class="nav-item nav-link"></p>  	
	</div>
</template>

<script>

	export default{
        name: "Login",
        data : function () {
            return{
                emailAddress : undefined,
                password : undefined,
                response : undefined,
                usersDetails : null,
                jwt : null
            }
        },
        methods : {
        	submit : function () {
        		var auth = {
        			"emailAddress" : this.emailAddress,
        			"password" : this.password
        		}
        		console.log(auth)
        		this.axios.post('http://localhost:8080/authenticate', auth)
        		.then(response => 
        		{
        			this.response = response
        			this.usersDetails = this.response.data

        			if ( this.usersDetails )
                    {
        				localStorage.setItem('auth', 'Bearer ' + this.usersDetails.jwt);
        				localStorage.setItem('user', this.usersDetails.firstName + ' ' + this.usersDetails.lastName);
						localStorage.setItem('email', this.usersDetails.email);
						localStorage.setItem('user_id', this.usersDetails.id);
						localStorage.setItem('typeOfUser', this.usersDetails.typeOfUser);
        				if (this.usersDetails.typeOfUser == "patient")
        				{
        					this.$router.push('/patientHome');
        				}
						else if (this.usersDetails.typeOfUser == "doctor")
        				{
        					this.$router.push('/doctorHome');
        				}
        				else if (this.usersDetails.typeOfUser == "clinicAdministrator")
        				{
        					this.$router.push('/clinicAdministratorHome');
        				}
        				else {
                            if (this.usersDetails.firstLogin === true) {
                                this.$router.push('/changePassword');
                            }
                            else {
                                this.$router.push('/clinicCenterAdministratorHome')
                            }
        				}
                    } else {
	        			this.$router.push('/error')
	        		}
        		})
        	}
        }
    }
</script>

<style>
	

</style>