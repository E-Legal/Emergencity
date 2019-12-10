<template>
  <div class="stats">
<div class="container">
      <div class="Chart__list">
        <div class="Chart">
          <h2>Chart</h2>
          <line-example title="Data one"></line-example>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.Chart {
  background: #212733;
  border-radius: 15px;
  box-shadow: 0px 2px 15px rgba(25, 25, 25, 0.27);
  margin:  25px 0;
}

.Chart h2 {
  margin-top: 0;
  padding: 15px 0;
  color:  rgba(255, 0,0, 0.5);
  border-bottom: 1px solid #323d54;
}
#stats {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.container {
  max-width: 800px;
  margin:  0 auto;
}

</style>

<script>
import LayoutDefault from "../layouts/LayoutDefault.vue";
import LineExample from '../script_chart/linechart.js'

export default {
  name: "stats",
  props: {
    msg: String
  },
  components: {
    LineExample
  },
  created() {
    this.$emit("update:layout", LayoutDefault);
  }
};
</script>