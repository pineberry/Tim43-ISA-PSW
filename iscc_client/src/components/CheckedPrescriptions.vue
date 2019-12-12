<template>
    <div class="container">
        <div class="jumbotron" v-for="pres in prescriptions" :key="pres.id">
            <p>Naziv lijeka: {{pres.medication}}</p>
        </div>
    </div>
</template>

<script>
    export default {
        name : "CheckedPrescriptions.vue",
        data : function () {
            return{
                nurse : localStorage.getItem('user_id'),
                prescriptions : null
            }
        },
        mounted() {
            this.getPrescriptions()
        },
        methods : {
            getPrescriptions : function () {
                this.axios.get("http://localhost:8080/nurse/checked/prescriptions/" + this.nurse)
                    .then(response => {this.prescriptions = response.data;})
                    .catch(error => {alert(error)})
            }
        }
    }
</script>