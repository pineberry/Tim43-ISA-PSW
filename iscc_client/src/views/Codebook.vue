<template>
    <div class="container row">
        <form class="col-6" v-on:submit.prevent="medicineSubmit" accept-charset="UTF-8">
            <div class="form-group">
                <label for="inputMedicineName">Medicine name</label>
                <input type="text" id="inputMedicineName" class="form-control">
            </div>
        </form>
        <form class="col-6" v-on:submit.prevent="diagnosisSubmit" accept-charset="UTF-8">

        </form>
    </div>
</template>

<script>
    export default {
        name : "Codebook",
        data : function () {
            return{
                medicineName : undefined,
                medicineCode : undefined,
                diagnosisName : undefined,
                diagnosisLName : undefined,
                diagnosisCode : undefined
            }
        },
        methods : {

        }
    }
</script>