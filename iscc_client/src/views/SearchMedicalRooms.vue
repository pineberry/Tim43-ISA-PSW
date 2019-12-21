<template>
    <div class="container">
        <h4>Medical Rooms</h4>
        <form @submit.prevent="searchRooms" accept-charset="UTF-8" method="get">
            <div class="row">
                <div class="col-6">
                    <div class="form-group">
                        <label for="selectSearchBy">Seach by</label>
                        <select id="selectSearchBy" class="form-control" v-model="searchBy">
                            <option value="name">Room name</option>
                            <option value="number">Room number</option>
                        </select>
                    </div>
                    <div v-if="searchBy === 'name'" class="form-group">
						<label for="inputName">Room name:</label>
						<input id="inputName" type="text" class="form-control" placeholder="Enter room name" v-model="roomName">
					</div>
                    <div v-else class="form-group">
						<label for="inputNumber">Room number:</label>
						<input id="inputNumber" type="number" class="form-control" v-model="roomNumber">
					</div>
                    <div class="form-group">
						<label for="inputDate">Date:</label>
						<input id="inputDate" type="date" class="form-control" placeholder="Enter date" v-model="searchDate">
					</div>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Search</button>
        </form>

        <div v-if="medicalRooms" class="container marginTop">

                <table class="table">
                    <thead class="thead-dark">
                        <th scope="col">#</th>
                        <th scope="col">Room name</th>
                        <th scope="col">Room number</th>
                        <th scope="col">Free date</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                        <th v-show="procedureId != 0" scope="col">Book</th>
                    </thead>
                    <tbody>
                        <tr v-for="room in medicalRooms" :key="room.id">
                            <td>{{medicalRooms.indexOf(room) + 1}}</td>
                            <td>{{room.roomName}}</td>
                            <td>{{room.roomNumber}}</td>
                            <td>{{room.firstFreeDate | formatDate}}</td>
                            <td><router-link class="btn btn-primary" :to="{path: '/editRoom',query: {roomId: room.id}}">Edit</router-link></td>
                            <td><button class="btn btn-warning" v-on:click="deleteRoom(room)">Delete</button></td>
                            <td v-show="procedureId != 0"><button class="btn btn-warning" v-on:click="bookRoom(room.id)">Book</button></td>
                        </tr>
                    </tbody>
                </table>
        </div>
    </div>
</template>

<script>
    import axios from "axios"

    export default {
        name: "searchMedicalRooms",
        data: function(){
            return {
                searchBy: 'name',
                roomName: undefined,
                roomNumber: undefined,
                searchDate: undefined,
                medicalRooms: null,
                procedureId: 0
            }
        },
        mounted: function(){
            this.axios.get("http://localhost:8080/medical/room/all")
                .then(response => {
                    this.medicalRooms = response.data;
                    this.procedureId = this.$route.params.id;
                })
        },
        filters: {
            formatDate: function(date){
                if (!date) return '';
                let formatedDate = new Date(date);
                var month = ('0' + (formatedDate.getMonth() + 1)).slice(-2);
                var day = ('0' + formatedDate.getDate()).slice(-2);
                var year = formatedDate.getFullYear();
                return year + '-' + month + '-' + day;
            }
        },
        methods: {
            searchRooms: function(){
                if (this.searchDate === undefined || this.searchDate === ''){
                    var date = new Date();
                    this.searchDate = this.dateFormating(date);
                }

                if (this.searchBy === 'name'){
                    var searchName = this.roomName;
                    if (this.roomName === undefined || this.roomName.trim() === ''){
                        searchName = 'all';
                    }

                    this.axios.get("http://localhost:8080/medical/room/name/" + searchName + "/" + this.searchDate)
                        .then(response => {
                            this.medicalRooms = response.data;
                        })
                    
                } else {
                    var searchNumber = this.roomNumber;

                    if (this.roomNumber === undefined || this.roomNumber === ''){
                        searchNumber = -1;
                    }
                    
                    this.axios.get("http://localhost:8080/medical/room/number/" + searchNumber + "/" + this.searchDate)
                        .then(response => {
                            this.medicalRooms = response.data;
                        })
                    
                }
            },
            deleteRoom: function(room){
                this.axios.delete("http://localhost:8080/medical/room/" + room.id)
                    .then(response => {
                        var index = this.medicalRooms.indexOf(room)
                        this.medicalRooms.splice(index, 1)
                    })
            },
            bookRoom: function (id) {
                this.axios.put("http://localhost:8080/medical/procedure/" + this.procedureId + "/" + id)
                    .then(response => {this.$router.push('/adminProfile');})
            },
            dateFormating: function(date){
                if (!date) return '';
                var month = ('0' + (date.getMonth() + 1)).slice(-2);
                var day = ('0' + date.getDate()).slice(-2);
                var year = date.getFullYear();
                return year + '-' + month + '-' + day;
            }
        },
        watch: {
            procedureId: function(){
                if (this.procedureId != 0) {
                    this.axios.get("http://localhost:8080/medical/procedure/" + this.procedureId)
                        .then(response => {
                            var date = new Date(response.data.dateOfProcedure);
                            this.searchDate = this.dateFormating(date);
                        })
                        .catch(error => {
                            alert('error');
                        })
                }
            }
        }

    }
</script>

<style scoped>
    .marginTop {
        margin-top: 10px;
    }
</style>