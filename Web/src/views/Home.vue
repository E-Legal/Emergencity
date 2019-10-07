<template>

</template>

<script>
// @ is an alias to /src
import LayoutDefault from '../layouts/LayoutDefault.vue';
import HelloWorld from "@/components/HelloWorld.vue";

export default {
  name: "home",
  components: {
  HelloWorld
},
  created() {
    this.$emit('update:layout', LayoutDefault);
  },

};
</script>
