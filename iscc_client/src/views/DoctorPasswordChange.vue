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
        name: "DoctorPasswordChange",
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

                if (localStorage.getItem('typeOfUser') === 'doctor') {
                    this.axios.put("http://localhost:8080/doctor/change/password", User)
                        .then(response => {
                            alert(response.data.email);
                            this.$router.push('/doctorHome')
                        })
                } else if (localStorage.getItem('typeOfUser') === 'nurse') {
                    this.axios.put("http://localhost:8080/nurse/change/password", User)
                        .then(response => {
                            alert(response.data.email);
                            this.$router.push('/nurseHome')
                        })
                } else if (localStorage.getItem('typeOfUser') === 'clinicAdministrator') {
                    this.axios.put("http://localhost:8080/clinic/admin/change/password", User)
                        .then(response => {
                            alert(response.data.email);
                            this.$router.push('/clinicAdministratorHome')
                        })
                }
            }
        }
    }
</script>

<style scoped>

</style>