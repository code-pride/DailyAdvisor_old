import Vue from 'vue';
import Vuetify from 'vuetify';
import App from './App.vue';
import router from './router';
import store from './store/store';
import './registerServiceWorker';

Vue.config.productionTip = false;
Vue.use(Vuetify, {
    theme: {
        primary: '#03A9F4',
        secondary: '#4E342E',
        accent: '#64DD17',
        error: '#f44336',
        warning: '#ffeb3b',
        info: '#2196f3',
        success: '#4caf50',
    },
});

new Vue({
    router,
    store,
    render: h => h(App),
}).$mount('#app');
