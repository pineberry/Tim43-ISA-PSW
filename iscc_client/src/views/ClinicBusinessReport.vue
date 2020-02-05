<template>
    <div class="container">
        <div v-if="clinic" class="row">
            <div class="col-6">
                <p>Average rating: {{clinic.averageRating}}</p>
                    <div class="form-group">
                        <label for="inputStartDate">From date:</label>
                        <input id="inputStartDate" type="date" class="form-control" placeholder="Enter start date" v-model="startDate">
                    </div>
                    <div class="form-group">
                        <label for="inputEndDate">To date:</label>
                        <input id="inputEndDate" type="date" class="form-control" placeholder="Enter end date" v-model="endDate">
                    </div>
                    <p><button class="btn btn-primary" v-on:click="getIncome()">Calculate income</button></p>
                    <p v-if="income">Income in chosen period: {{income}}</p>
                <p><mdb-container v-if="p">
                    <mdb-bar-chart
                            :data="barChartData"
                            :options="barChartOptions"
                            :width="400"
                            :height="300"
                    ></mdb-bar-chart>
                </mdb-container></p>
            </div>
            <div class="col-6">
                <table class="table">
                    <thead class="thead-dark">
                        <th scope="col">Name</th>
                        <th scope="col">Last name</th>
                        <th scope="col">Average rating</th>
                    </thead>
                    <tbody>
                        <tr v-for="doc in clinic.doctors" :key="doc.id">
                            <td>{{doc.firstName}}</td>
                            <td>{{doc.lastName}}</td>
                            <td>{{doc.averageRating}}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
    import { mdbBarChart, mdbContainer } from "mdbvue";
    export default {
        name: "ClinicBusinessReport",
        components: {
            mdbBarChart,
            mdbContainer
        },
        data() {
            return {
                clinic: null,
                income: undefined,
                startDate: undefined,
                endDate: undefined,
                p: false,
                barChartData: {
                    labels: [
                        "Today",
                        "This week",
                        "This month",
                    ],
                    datasets: [
                        {
                            label: "Number of exams and surgeries",
                            data: [],
                            backgroundColor: [
                                "rgba(255, 99, 132, 0.2)",
                                "rgba(54, 162, 235, 0.2)",
                                "rgba(255, 206, 86, 0.2)",
                            ],
                            borderColor: [
                                "rgba(255,99,132,1)",
                                "rgba(54, 162, 235, 1)",
                                "rgba(255, 206, 86, 1)",
                            ],
                            borderWidth: 1
                        }
                    ]
                },
                barChartOptions: {
                    responsive: false,
                    maintainAspectRatio: false,
                    scales: {
                        xAxes: [
                            {
                                barPercentage: 1,
                                gridLines: {
                                    display: true,
                                    color: "rgba(0, 0, 0, 0.1)"
                                }
                            }
                        ],
                        yAxes: [
                            {
                                gridLines: {
                                    display: true,
                                    color: "rgba(0, 0, 0, 0.1)"
                                }
                            }
                        ]
                    }
                }
            };
        },
        mounted: function () {
            this.axios.get("http://localhost:8080/clinic/report/" + localStorage.getItem("user_id"))
                .then(response => {
                    this.clinic = response.data;
                    this.barChartData.datasets[0].data.push(this.clinic.numberOfProcedures['day']);
                    this.barChartData.datasets[0].data.push(this.clinic.numberOfProcedures['week']);
                    this.barChartData.datasets[0].data.push(this.clinic.numberOfProcedures['month']);
                    this.p = true;
                })
        },
        methods: {
            getIncome: function() {
                if (this.startDate != undefined && this.startDate != ''
                        && this.endDate != undefined && this.endDate != '') {
                    this.axios.get("http://localhost:8080/clinic/income/" + this.startDate + '/'
                                               + this.endDate + '/' + localStorage.getItem("user_id"))
                        .then(response => {
                            this.income = response.data;
                        })
                }
            }
        }
    };
</script>

<style scoped>

</style>