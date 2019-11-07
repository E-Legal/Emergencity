/* eslint-disable */
<template>
  <div name="MyAwesomeMap">
    <div class="container">
      <div class="row">
        <div class="col-sm" style="width: 400px; height: 400px; margin: 0; position: relative; outline: currentcolor none medium;">
          <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2903.1713760356865!2d5.367165051227156!3d43.310671779032084!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x12c9c0f26c729b3b%3A0x243583660fc8bdd7!2s21%20Rue%20Mir%C3%A8s%2C%2013002%20Marseille!5e0!3m2!1sfr!2sfr!4v1567028365466!5m2!1sfr!2sfr" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen=""></iframe>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
export default {
  name: "MyAwesomeMap",
  components: {},
  data() {
    return {
      zoom: 11,
      center: [47.41322, -1.219482],
      url: "http://{s}.tile.osm.org/{z}/{x}/{y}.png",
      attribution:
        '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
      marker: [47.41322, -1.219482]
    };
  }
};
</script>