<template>
    <v-app id="app">
        <div id="nav">
            <router-link to="/">Home</router-link> |
            <router-link to="/about">About</router-link> |
            <router-link to="/register">Sign Up</router-link> |
            <router-link to="/login">Login</router-link>
        </div>
        <router-view/>
    </v-app>
</template>

<style lang="scss">
#app {
    font-family: "Roboto", sans-serif;
}
</style>
