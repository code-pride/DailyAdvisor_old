import Vue from 'vue';
import Vuex from 'vuex';
import auth from './services/auth';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        isAuthenticated: false,
        authenticationError: '',
    },
    getters: {
        isAuthenticated(state) {
            return state.isAuthenticated;
        },
        authenticationErrorOccured(state) {
            return state.authenticationError !== '';
        },
    },
    mutations: {
        AUTHENTICATE(state) {
            state.authenticated = true;
        },
        ADD_AUTHENTICATION_ERROR(state, error) {
            state.authenticationError = error;
        },
    },
    actions: {
        authenticate({ commit }, credentials) {
            auth.login(credentials).then(
                () => commit('AUTHENTICATE'),
                error => commit('ADD_AUTHENTICATION_ERROR', { error }),
            );
        },

        register(something, userData) {
            auth.register(userData).then(
                response => console.log(response),
                error => console.log(error),
            );
        },
    },
});
