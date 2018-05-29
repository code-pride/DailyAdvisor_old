import Vue from 'vue';
import Vuetify from 'vuetify';
import Vuelidate from 'vuelidate';
import App from './App.vue';
import router from './router';
import store from './store/store';
import i18n from './translations/locale-configuration';
import './registerServiceWorker';

Vue.use(Vuelidate);
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
    i18n,
    router,
    store,
    render: h => h(App),
}).$mount('#app');

export default i18n;
