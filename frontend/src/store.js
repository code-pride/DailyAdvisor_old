import Vue from 'vue';
import Vuex from 'vuex';
import auth from './services/auth';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        isAuthenticated: false,
    },
    getters: {
        isAuthenticated(state) {
            return state.isAuthenticated;
        },
    },
    mutations: {
        AUTHENTICATE(state) {
            state.authenticated = true;
        },
        LOGOUT(state) {
            state.authenticated = false;
        },
    },
    actions: {
        authenticate({ commit }, credentials) {
            auth.login(credentials).then(
                () => commit('AUTHENTICATE'),
                () => commit('LOGOUT'),
            );
        },
    },
});
