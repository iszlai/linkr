<template>
  <div id="app">
    <md-toolbar>
      <h1 class="md-title">Linkr</h1>
    </md-toolbar>

    <div class="main-content">
      <login></login>
    </div>
  </div>
</template>

<script>
  import Login from './components/Login'
  import VueMaterial from 'vue-material'
  import Vue from 'vue'
  export default {
    name: 'app',
    components: {
      Login
    }
  }

  Vue.use(VueMaterial)
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>