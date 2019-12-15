<template>
    <div class="container">
        <h4>New Medical Room</h4>
        <form v-on:submit.prevent="addMedicalRoom" accept-charset="UTF-8" method="post">
            <div class="row">
                <div class="col-6">
                    <div class="form-group">
                        <label for="inputName">Room name:</label>
                        <input id="inputName" type="text" class="form-control" placeholder="Enter room name" v-model="roomName">
                    </div>
                    <div class="form-group">
                        <label for="inputNumber">Room number:</label>
                        <input id="inputNumber" type="number" class="form-control" placeholder="Enter room number" v-model="roomNumber">
                    </div>
                    <div class="form-group">
                        <label for="selectClinic">Clinic</label>
                        <select id="selectClinic" class="form-control" v-model="clinic">
                            <option v-for="c in clinics" :key="c.id" :value="c">{{c.name}}</option>
                        </select>
                    </div>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Add</button>
        </form>
    </div>
</template>

<script>
    import axios from "axios"
    
    export default {
        name: "AddMedicalRoom",
        data: function(){
            return {
                roomName: undefined,
                roomNumber: undefined,
                clinic: null,
                clinics: []
            }
        },
        mounted: function(){
            this.axios.get("http://localhost:8080/clinic/clinics")
                .then(response => {this.clinics = response.data})
                .catch(error => {alert(error.response.data)})
        },
        computed: {
            valName: function(){
                if(this.roomName === '') 
                    return 'This field is required!';
                else
                    return null;
            },
            valNumber: function(){
                if(this.roomName === '')
                    return 'This field is required!';
                else
                    return null;
            }
        },
        methods: {
            addMedicalRoom: function(){
                var medicalRoom = {
                    "roomName": this.roomName,
                    "roomNumber": this.roomNumber,
                    "clinic": this.clinic
                }

                var valid = true;

                if (this.roomNumber != undefined) 
                    this.roomName.trim();
				else 
                    this.roomName = '';

                if (this.roomName === undefined || this.roomName === '' || this.roomNumber === undefined
                        || this.clinic === null){
                    valid = false
                }

                if (valid){
                    this.axios.post("http://localhost:8080/medical/room/add", medicalRoom)
                        .then(response => {this.$router.push('/searchRooms/0')})
                }
            }
        }
    }
</script>

<style>
</style>