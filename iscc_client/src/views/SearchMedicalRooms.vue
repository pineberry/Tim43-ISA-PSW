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
						<label for="inputRoomName">Room name:</label>
						<input id="inputRoomName" type="text" class="form-control" placeholder="Enter room name" v-model="roomName">
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
                <div class="col-6">
                    <span>Filter rooms</span>
                    <p>
                        <input id="filterName" type="text" class="form-control" placeholder="Filter by name" v-model="filterName">
                    </p>
                    <p>
                        <input id="filterNumber" type="number" class="form-control" placeholder="Filter by number" v-model="filterNumber">
                    </p>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Search</button>
        </form>

        <div v-if="medicalRooms" class="container marginTop">

                <table class="table" id="room-search-table">
                    <thead class="thead-dark">
                        <th scope="col">#</th>
                        <th scope="col">Room name</th>
                        <th scope="col">Room number</th>
                        <th scope="col">Free date</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                        <th v-show="procedureId != 0" scope="col">Book</th>
                        <th scope="col">Calendar</th>
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
                            <td><router-link class="btn btn-outline-primary" :to="{path: '/roomCalendar', query: {id: room.id}}">Details</router-link></td>
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
                unfilteredRooms: null,
                procedureId: 0,
                typeOfExamination: this.$route.query.type,
                filterName: undefined,
                filterNumber: undefined,
                show: false,
            }
        },
        mounted: function(){
            this.axios.get("http://localhost:8080/medical/room/clinic/" + localStorage.getItem("user_id"))
                .then(response => {
                    this.unfilteredRooms = response.data;
                    this.medicalRooms = this.unfilteredRooms.slice();
                    if (this.$route.query.proc != undefined) {
                        this.procedureId = this.$route.query.proc;
                    }
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

                    this.axios.get("http://localhost:8080/medical/room/name/" + searchName + "/" + this.searchDate
                                                                    + '/' + localStorage.getItem("user_id"))
                        .then(response => {
                            this.medicalRooms = response.data;
                            this.unfilteredRooms = this.medicalRooms.slice();
                        })
                    
                } else {
                    var searchNumber = this.roomNumber;

                    if (this.roomNumber === undefined || this.roomNumber === ''){
                        searchNumber = -1;
                    }
                    
                    this.axios.get("http://localhost:8080/medical/room/number/" + searchNumber + "/"
                                                    + this.searchDate + '/' + localStorage.getItem("user_id"))
                        .then(response => {
                            this.medicalRooms = response.data;
                            this.unfilteredRooms = this.medicalRooms.slice();
                        })
                    
                }
            },
            deleteRoom: function(room){
                if(localStorage.getItem("typeOfUser") != "clinicAdministrator") {
                    alert('Only clinic admin has permission for this action!');
                    return;
                }
                
                this.axios.delete("http://localhost:8080/medical/room/" + room.id)
                    .then(response => {
                        var index = this.medicalRooms.indexOf(room)
                        this.medicalRooms.splice(index, 1)
                    })
                    .catch(error=>{
                        alert('Cannot delete room due to scheduled appoinments!');
                    })
            },
            bookRoom: function (id) {
                if(this.typeOfExamination != undefined) {
                    if(this.typeOfExamination == 'exam'){
                    this.axios.put("http://localhost:8080/medical/procedure/" + this.procedureId + "/" + id)
                        .then(response => {this.$router.push('/adminProfile');})
                        .catch(error => {
                            if(confirm('Would you like automatically to book room?')) {
                                this.axios.put("http://localhost:8080/medical/procedure/auto/book/" + this.procedureId)
                                    .then(response => {
                                        this.$router.push("/clinicAdministratorHome")
                                    })
                            }
                        })
                    }
                    else if(this.typeOfExamination == 'surgery') {
                        this.$router.push('/choseDoctors?proc=' + this.procedureId + '&room=' + id);
                    }
                }
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
                            console.log(response)
                            var date = new Date(response.data.dateOfProcedure);
                            this.searchDate = this.dateFormating(date);
                        })
                }
            },

            filterName: function() {
                /*
                if (this.filterName != undefined && this.filterName.trim() != '') {
                    this.medicalRooms.length = 0;
                    this.filterName = this.filterName.toLowerCase();
                    this.medicalRooms = this.unfilteredRooms.filter((room) =>
                                                                room.roomName.toLowerCase().includes(this.filterName));
                } else {
                    this.medicalRooms = this.unfilteredRooms.slice();
                } */
                let name = this.filterName.trim().toLowerCase();

                if (this.filterNumber === undefined || this.filterNumber.trim() === '') {
                    this.medicalRooms = this.unfilteredRooms.filter((room) =>
                                                                            room.roomName.toLowerCase().includes(name));
                } else {
                    let roomNumber = this.filterNumber;
                    this.medicalRooms = this.unfilteredRooms.filter((room) =>
                                                                        room.roomNumber.toString().includes(roomNumber));
                    this.medicalRooms = this.medicalRooms.filter((room) =>
                                                                        room.roomName.toLowerCase().includes(name));
                }
            },

            filterNumber: function() {
                /*
                if (this.filterNumber != undefined && this.filterNumber != '') {
                    this.medicalRooms.length = 0;
                    this.filterNumber = this.filterNumber.toString();
                    this.medicalRooms = this.unfilteredRooms.filter((room) =>
                                                                room.roomNumber.toString().includes(this.filterNumber));
                } else {
                    this.medicalRooms = this.unfilteredRooms.slice();
                } */

                let roomNumber = this.filterNumber.trim();

                if (this.filterName === undefined || this.filterName.trim() === '') {
                    this.medicalRooms = this.unfilteredRooms.filter((room) =>
                                                                 room.roomNumber.toString().includes(roomNumber));
                } else {
                    let name = this.filterName.trim().toLowerCase();
                    this.medicalRooms = this.unfilteredRooms.filter((room) =>
                                                                    room.roomName.toLowerCase().includes(name));
                    this.medicalRooms = this.medicalRooms.filter((room) =>
                                                                    room.roomNumber.toString().includes(roomNumber));
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