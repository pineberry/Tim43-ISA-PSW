<template>
    <div class="container">
        <p>To see unchecked prescriptions click <router-link to="/uncheckedPrescriptions">here</router-link></p>
        <p>To see prescriptions checked by you click <router-link to="/checkedPrescriptions">here</router-link></p>
    </div>
</template>

<script>
 export default {
     name : "NurseHome"
 }
</script>