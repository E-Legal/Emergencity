/* eslint-disable */
<template>
  <div name="MyAwesomeMap">
      <div class="container-flex" >
        <GmapMap
          :center="{lat: latitude, lng: longitude}"
          :zoom="17"
          :options="{
            zoomControl: true,
            mapTypeControl: false,
            scaleControl: false,
            streetViewControl: false,
            rotateControl: false,
            fullscreenControl: true,
            disableDefaultUi: false
          }"
          class="gmap"
>
  <GmapInfoWindow :options="infoOptions" :position="infoWindowPos" :opened="infoWinOpen" @closeclick="infoWinOpen=false"></GmapInfoWindow>
  <GmapMarker
        :key="index"
    v-for="(m, index) in markers"
    :position="m.position"
    :clickable="true"
    :draggable="false"
    @click="toggleInfoWindow(m,index)"
  />
  <GmapCircle

    v-for="(m, index) in markers"
              :key="'circle' + index"
    :center="m.position"
    :clickable="true"
    :draggable="false"
    @click="toggleInfoWindow(m,index)"
    :options="{
        strokeColor: '#000',
        strokeOpacity: 0,
        strokeWeight: 0,
        fillColor: m.color,
        fillOpacity: 0.50,
        radius: 7
        }"
    
    ></GmapCircle>
</GmapMap>

      </div>
  </div>
</template>

<style scoped>
.gmap {
width: 1920px; height: calc(100vh - 66px)
}
</style>

<script>

import {gmapApi} from 'vue2-google-maps'

export default {
  name: "MyAwesomeMap",
  components: {
    google: gmapApi
  },
  data() {
    return {
      zoom: 17,
      infoWindowPos: null,
      infoWinOpen: false,
      currentMidx: null,
      infoOptions: {
          content: '',
          pixelOffset: {
            width: 0,
            height: -35
        }
      },
      markers: [{
          position: {lat: 43.2697659, lng: 5.3671704}, infoText: '<p> 61 Rue Marx Dormoy, 13004 Marseille, France </p><b><strong>Feu Indisponible</strong></b>', state: 0, icon: 'mark', color: 'black'
        },{
          position: {lat: 43.2709000, lng: 5.3671709}, infoText: '<p> 61 Rue Marx Dormoy, 13004 Marseille, France </p><b><strong>Feu Rouge</strong></b>', state: 0, icon: 'mark', color: 'red'}, 
        {position: {lat: 43.2697659, lng: 5.3717040}, infoText: '<p> 61 Rue Marx Dormoy, 13004 Marseille, France </p><b><strong>Feu vert</strong></b>', state: 1, icon: 'mark2', color: 'green'}
      ],
      latitude: 43.2697659,
      longitude: 5.3671704
    };
  },
  methods: {
  toggleInfoWindow: function(marker, idx) {
    this.infoWindowPos = marker.position;
    this.infoOptions.content = marker.infoText;
    if (this.currentMidx == idx) {
      this.infoWinOpen = !this.infoWinOpen;
    }
    else {
      this.infoWinOpen = true;
      this.currentMidx = idx;
    }
  }
}
};

</script>
