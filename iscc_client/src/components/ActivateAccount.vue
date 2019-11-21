<template>
    <div class="container">
        <div class="jumbotron">
            <h5>Vas nalog je uspjesno aktiviran</h5>
            <router-link to="/">Login</router-link>
        </div>
    </div>
</template>

<script>
    export default {
        name : "ActivateAccount",
        mounted : function () {
            this.axios.put("http://localhost:8080/center/admin/activate/" + this.$route.params.id)
                .then(response => {alert(response.data)})
                .catch(error => {alert(error.response.data)})
        }
    }
</script>