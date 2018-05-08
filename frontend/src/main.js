import Vue from 'vue';
import Vuetify from 'vuetify';
import Vuelidate from 'vuelidate';
import VueI18n from 'vue-i18n';
import App from './App.vue';
import router from './router';
import store from './store/store';
import './registerServiceWorker';

import { messages, dateTimeFormats, numberFormats } from './translations/localization';

Vue.use(VueI18n);
Vue.config.productionTip = false;

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

const i18n = new VueI18n({
    locale: 'en',
    messages,
    dateTimeFormats,
    numberFormats,
});

new Vue({
    i18n,
    router,
    store,
    render: h => h(App),
}).$mount('#app');
