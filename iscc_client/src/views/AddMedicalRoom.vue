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
            }
        },
        methods: {
            addMedicalRoom: function(){
                var medicalRoom = {
                    "roomName": this.roomName,
                    "roomNumber": this.roomNumber,
                }

                if (this.roomName != undefined)
                    this.roomName.trim();
				else 
                    this.roomName = '';

                if (this.roomName === '' || this.roomNumber === undefined){
                    alert('All fields should be filled!');
                    return;
                }

                this.axios.post("http://localhost:8080/medical/room/add/"
                                                            + localStorage.getItem("user_id") , medicalRoom)
                    .then(response => {
                        this.roomName = undefined;
                        this.roomNumber = undefined;
                        alert('Medical room has been added!');
                    })
            }
        }
    }
</script>

<style>
</style>