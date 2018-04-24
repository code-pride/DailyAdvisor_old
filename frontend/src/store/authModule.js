import auth from '../services/auth';

const state = {
    registerErrorMessage: '',
    registerSuccessMessage: '',
    registerSnackBarColor: 'error',
    isAuthenticated: false,
    authenticationError: '',
};

const getters = {
    registerSnackBarColor() {
        return state.registerSnackBarColor;
    },
    didRegisterErrorOccured() {
        return state.registerErrorMessage !== '';
    },
    didRegisterSuccess() {
        return state.registerSuccessMessage !== '';
    },
    registerErrorMessage() {
        return state.registerErrorMessage;
    },
    registerSuccessMessage() {
        return state.registerSuccessMessage;
    },
    isAuthenticated() {
        return state.isAuthenticated;
    },
    authenticationErrorOccured() {
        return state.authenticationError !== '';
    },
};

const mutations = {
    ADD_REGISTER_ERROR(state, error) {
        state.registerError = error.response.statusText;
    },
    ADD_REGISTER_SUCCES_MESSAGES(state, data) {
        state.registerSnackBarColor = 'success';
        state.registerSuccessMessage = data.statusText;
    },
    ADD_REGISTER_CONFIRMATION_SUCCES() {
        console.log('user registration confirmed successfully');
    },
    ADD_REGISTER_CONFIRMATION_ERROR() {
        console.log('user registration confirmation error');
    },
    CLEAR_REGISTER_MESSAGES(state) {
        state.registerErrorMessage = '';
        state.registerSuccessMessage = '';
    },
    AUTHENTICATE(state) {
        state.isAuthenticated = true;
    },
    ADD_AUTHENTICATION_ERROR(state, error) {
        state.authenticationError = error.statusText;
    },
};

const actions = {
    authenticate({ commit }, credentials) {
        auth.login(credentials).then(
            () => commit('AUTHENTICATE'),
            error => commit('ADD_AUTHENTICATION_ERROR', { error }),
        );
    },

    register({ commit }, userData) {
        auth.register(userData).then(
            data => commit('ADD_REGISTER_SUCCES_MESSAGES', data),
            error => commit('ADD_REGISTER_ERROR', error),
        );
    },

    registerConfirmation({ commit }, token) {
        auth.registerConfirmation(token).then(
            () => commit('ADD_REGISTER_CONFIRMATION_SUCCES'),
            () => commit('ADD_REGISTER_CONFIRMATION_ERROR'),
        );
    },

    clearRegisterMessages({ commit }) {
        commit('CLEAR_REGISTER_MESSAGES');
    },
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations,
};
