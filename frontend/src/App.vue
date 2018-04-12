<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </div>
    <p>normal strings</p>
    <p>{{ $t('hello') }}</p>


    <br>
    <p>data formats</p>
    <p>{{ $d(new Date(), 'short') }}</p>
    <p>{{ $d(new Date(), 'long') }}</p>
    <p>{{ $d(new Date(), 'long', 'pl') }}</p>

    <br>
    <p>numbers</p>
    <p>{{ $n(100, 'currency') }}</p>
    <p>{{ $n(100, 'currency', 'pl') }}</p>
    <p>{{ $n(0.53, 'percent') }}</p>
    <router-view/>
  </div>
</template>

<style lang="scss">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
  a {
    font-weight: bold;
    color: #2c3e50;
    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
