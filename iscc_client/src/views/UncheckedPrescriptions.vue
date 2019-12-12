<template>
    <div class="container" v-if="prescriptions">
        <div class="jumbotron" v-for="pres in prescriptions" :key="pres.id">
            <p>Naziv lijeka: {{pres.medication}}</p>
            <button class="btn btn-success" v-on:click="check(pres.id)">Check</button>
        </div>
    </div>
</template>

<script>
    export default {
        name : "UncheckedPrescriptions",
        data : function () {
            return {
                prescriptions : null
            }
        },
        mounted : function () {
           this.getPrescriptions();
        },
        methods : {
            check : function (id) {
                var nurse = {
                    "email" : localStorage.getItem("email")
                }
                this.axios.put("http://localhost:8080/nurse/check/prescription/" + id, nurse)
                    .then(response => {alert("uspjesno cekcekcekiram"); this.getPrescriptions()})
                    .catch(error => {alert(error.response.data)})
            },
            getPrescriptions : function () {
                var nurse = {
                    "email" : localStorage.getItem("email")
                }
                this.axios.post("http://localhost:8080/nurse/unchecked/prescriptions", nurse)
                    .then(response => {
                        this.prescriptions = response.data
                    })
                    .catch(error => {
                        alert(error.response.data);
                    })
            }
        }
    }
</script>