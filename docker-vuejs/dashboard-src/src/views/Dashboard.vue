<template>

  <div class="dashboard">
    <div class="container">
    <Map/>
    </div>
  </div>

</template>

<script>

import LayoutDefault from '../layouts/LayoutDefault.vue';
import Map from '../components/Map.vue';

export default {
  name: "dashboard",
  components: {
    Map,
},
created() {
    this.$emit('update:layout', LayoutDefault);
  }
};
</script>
