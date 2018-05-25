import Vue from 'vue';
import VueI18n from 'vue-i18n';

import { messages, dateTimeFormats, numberFormats } from './localization';

Vue.use(VueI18n);
Vue.config.productionTip = false;

const i18n = new VueI18n({
    locale: 'pl',
    messages,
    dateTimeFormats,
    numberFormats,
});

export default i18n;
