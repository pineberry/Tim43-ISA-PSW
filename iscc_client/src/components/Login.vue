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
        				else if (this.usersDetails.typeOfUser == "nurse")
                        {
                            this.$router.push('/nurseHome');
                        }
        				else
                        {
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