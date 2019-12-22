<template>
    <div class="container">
        <div class="row">
            <form  class="col-6" v-on:submit.prevent="changePassword">
                <div class="form-group">
                    <label for="inputPassword">New Password</label>
                    <input type="password" class="form-control" placeholder="Input new password..." v-model="password" id="inputPassword">
                </div>
                <div class="form-group">
                    <label for="inputPasswordF">Repeat Password</label>
                    <input type="password" class="form-control" placeholder="Repeat password form above.." v-model="passwordF" id="inputPasswordF">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</template>

<script>
    export default {
        name : "AdminPasswordChange",
        data : function () {
            return {
                email : localStorage.getItem('email'),
                password : undefined,
                passwordF : undefined
            }
        },
        methods : {
            changePassword : function () {
                var User = {
                    "email" : this.email,
                    "password" : this.password,
                    "passwordF" : this.passwordF
                }

                this.axios.post("http://localhost:8080/center/admin/change/password", User)
                    .then(response => { alert( response.data.email); 
                    this.$router.push('/clinicCenterAdministratorHome')
                    })
                    .catch(error => { alert(error) })
            }
        }

    }
</script>