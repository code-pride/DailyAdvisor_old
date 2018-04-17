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
                (response) => {
                    console.log('Seems like everything is fine...');
                    console.log(response);
                    commit('AUTHENTICATE');
                },
                (error) => {
                    console.log('There is an error...');
                    console.log(error);
                    commit('ADD_AUTHENTICATION_ERROR', {
                        error: error.message,
                    });
                },
            );
        },
        clearAuthenticationErrors({ commit }) {
            commit('CLEAR_AUTHENTICATION_ERRORS');
        },
    },
});
