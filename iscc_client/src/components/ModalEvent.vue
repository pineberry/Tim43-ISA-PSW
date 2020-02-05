<template>
  <div v-if="procedure.patient">
    <fieldset>
      <legend>Event details</legend>
      <b>Title: </b>{{title}}<br />
      <b>Start: </b>{{start}}<br />
      <b>End: </b>{{end}}<br />
      <b>Patient: </b>{{procedure.patient.firstName}} {{procedure.patient.lastName}} <br/>
    </fieldset>
  </div>
</template>

<script>
export default {
  data: () => ({
    title: "",
    start: {},
    end: {},
    procedure: Object
  }),
  props: {
    event: Object
  },
  mounted() {
    this.axios
      .get("http://localhost:8080/medical/procedure/" + this.event.id)
      .then(response => {
        this.procedure = response.data;
      });
    this.title = this.event.title;
    this.start = formatDate(this.event.start);
    this.end = formatDate(this.event.end);
  }
};

function formatDate(date) {
  if (date != null) {
    var options = { year: "numeric", month: "2-digit", day: "2-digit" };
    let string = date.toLocaleDateString("sv-SE", options);
    return string;
  }
  return "";
}
</script>

<style>
</style>