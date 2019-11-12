<template>
  <div class="dashboard">
    <Map />
  </div>
</template>

<script>
import LayoutDefault from "../layouts/LayoutDefault.vue";
import Map from "../components/Map.vue";

export default {
  name: "dashboard",
  components: {
    Map
  },
  created() {
    this.$emit("update:layout", LayoutDefault);
  }
};
</script>
