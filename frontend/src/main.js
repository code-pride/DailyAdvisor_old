import Vue from 'vue';
import VueI18n from 'vue-i18n';
import App from './App.vue';
import router from './router';
import store from './store';
import './registerServiceWorker';

import { messages, dateTimeFormats, numberFormats } from './localization';

Vue.use(VueI18n);
Vue.config.productionTip = false;

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
