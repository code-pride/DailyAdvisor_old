import auth from '../services/auth';

const state = {
    isAuthenticated: false,
    authenticationError: '',
};

const getters = {
    isAuthenticated(state) {
        return state.isAuthenticated;
    },
    authenticationErrorOccured(state) {
        return state.authenticationError !== '';
    },
};

const mutations = {
    AUTHENTICATE(state) {
        state.isAuthenticated = true;
    },
    ADD_AUTHENTICATION_ERROR(state, error) {
        state.authenticationError = error;
    },
};

const actions =  {
    authenticate({ commit }, credentials) {
        auth.login(credentials).then(
            () => commit('AUTHENTICATE'),
            error => commit('ADD_AUTHENTICATION_ERROR', { error }),
        );
    },

    register(something, userData) {
        console.log(userData);
        // auth.register(userData).then(
        //     response => console.log(response),
        //     error => console.log(error),
        // );
    },
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations,
}