<template>
  <div>
    <fieldset>
      <legend>Event details</legend>
      <b>Title:</b>
      {{title}}
      <br />
      <b>Start:</b>
      {{start}}
      <br />
      <b>End:</b>
      {{end}}
      <br />
    </fieldset>
  </div>
</template>

<script>
export default {
  data: () => ({
    title: "",
    start: {},
    end: {}
  }),
  props: {
    event: Object
  },
  mounted() {
	this.title = this.event.title;
	this.start = formatDate(this.event.start);
	this.end = formatDate(this.event.end);
  }
};

function formatDate(date) {
  var options = { year: "numeric", month: "2-digit", day: "2-digit" };
  let string = date.toLocaleDateString("sv-SE", options);

  return string;
}
</script>

<style>
</style>