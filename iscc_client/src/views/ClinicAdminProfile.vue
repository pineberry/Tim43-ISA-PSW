<template>
    <div class="container">
        <h4>Profile info</h4>
        <div v-if="admin" class="row">
            <div class="col-9">
                <p>First name: {{admin.firstName}}</p>
                <p>Last name: {{admin.lastName}}</p>
                <p>Address: {{admin.address}}</p>
                <p>City: {{admin.city}}</p>
                <p>State: {{admin.state}}</p>
                <p>Phone number: {{admin.phoneNumber}}</p>
                <p>E-mail: {{admin.email}}</p>
                <p>Clinic: {{admin.clinicName}}</p>
            </div>
            <div class="col-3">
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="drdpButton"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Settings
                    </button>
                    <div class="dropdown-menu" aria-labelledby="drdpButton">
                        <router-link class="dropdown-item" to="/editAdmin">Edit profile</router-link>
                        <router-link class="dropdown-item" to="/changePasswordDoctor">Change password</router-link>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "clinicAdminProfile",
        data: function() {
            return {
                admin: null
            }
        },
        mounted() {
            this.axios.get("http://localhost:8080/clinic/admin/" + localStorage.getItem('user_id'))
                .then(response => {
                    this.admin = response.data;
                })
        }
    }
</script>

<style scoped>

</style>