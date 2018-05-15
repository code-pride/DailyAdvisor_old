import Vue from 'vue';
import Vuex from 'vuex';
import auth from './services/auth';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        authenticated: false,
        authenticationError: '',
    },
    getters: {
        isAuthenticated(state) {
            return state.authenticated;
        },
        didAuthenticationErrorOccured(state) {
            return state.authenticationError !== '';
        },
        authenticationErrorMessage(state) {
            return state.authenticationError;
        },
    },
    mutations: {
        AUTHENTICATE(state) {
            state.authenticated = true;
        },
        ADD_AUTHENTICATION_ERROR(state, payload) {
            state.authenticationError = payload.error;
        },
        CLEAR_AUTHENTICATION_ERRORS(state) {
            state.authenticationError = '';
        },
    },
    actions: {
        authenticate({ commit }, credentials) {
            auth.login(credentials).then(
                () => commit('AUTHENTICATE'),
                error => commit('ADD_AUTHENTICATION_ERROR', { error }),
            );
        },
        googleAuthenticate({ commit }) {
            // not sure what loginWithGoogle will return yet, so it's just guessing for now
            auth.loginWithGoogle().then(
                () => commit('authenticate'),
                error => commit('ADD_AUTHENTICATION_ERROR', { error }),
            );
        },
        facebookAuthenticate({ commit }) {
            auth.loginWithFacebook().then(
                () => commit('authenticate'),
                error => commit('ADD_AUTHENTICATION_ERROR', { error }),
            );
        },
        clearAuthenticationErrors({ commit }) {
            commit('CLEAR_AUTHENTICATION_ERRORS');
        },
    },
});
