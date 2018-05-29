import authService from '../services/auth';
import i18n from '../translations/locale-configuration';


const state = {
    registerErrorMessage: '',
    registerConfirmationMessage: '',
    isRegistered: false,
    authenticated: false,
    authenticationError: '',
};

const getters = {
    registerErrorOccured() {
        return state.registerErrorMessage !== '';
    },
    registerErrorMessage() {
        return state.registerErrorMessage;
    },
    registerConfirmationMessage() {
        return state.registerConfirmationMessage;
    },
    isRegistered() {
        return state.isRegistered;
    },
    isAuthenticated(state) {
        return state.authenticated;
    },
    didAuthenticationErrorOccured(state) {
        return state.authenticationError !== '';
    },
    authenticationErrorMessage(state) {
        return state.authenticationError;
    },
};

const mutations = {
    ADD_REGISTER_ERROR(state) {
        state.isRegistered = false;
        state.registerErrorMessage = i18n.t('userExist');
    },
    ADD_REGISTER_CONFIRMATION_SUCCES(state) {
        state.registerConfirmationMessage = i18n.t('yourAccountIsConfirmed');
    },
    ADD_REGISTER_CONFIRMATION_ERROR() {
        state.registerConfirmationMessage = i18n.t('yourAccountHasBenActivatedEarlier');
    },
    CLEAR_REGISTER_MESSAGES(state) {
        state.registerErrorMessage = '';
    },
    CLEAR_REGISTRATION_STATE(state) {
        state.isRegistered = false;
    },
    REGISTER(state) {
        state.isRegistered = true;
        state.registerErrorMessage = '';
    },
    AUTHENTICATE(state) {
        state.authenticated = true;
    },
    ADD_AUTHENTICATION_ERROR(state, payload) {
        state.authenticationError = payload.error;
    },
    CLEAR_AUTHENTICATION_ERRORS(state) {
        state.authenticationError = '';
    },
};

const actions = {
    authenticate({ commit }, credentials) {
        authService.login(credentials).then(
            () => commit('AUTHENTICATE'),
            error => commit('ADD_AUTHENTICATION_ERROR', { error }),
        );
    },
    googleAuthenticate({ commit }) {
        // not sure what loginWithGoogle will return yet, so it's just guessing for now
        authService.loginWithGoogle().then(
            () => commit('AUTHENTICATE'),
            error => commit('ADD_AUTHENTICATION_ERROR', { error }),
        );
    },
    facebookAuthenticate({ commit }) {
        authService.loginWithFacebook().then(
            () => commit('authenticate'),
            error => commit('ADD_AUTHENTICATION_ERROR', { error }),
        );
    },
    clearAuthenticationErrors({ commit }) {
        commit('CLEAR_AUTHENTICATION_ERRORS');
    },
    register({ commit }, userData) {
        return authService.register(userData).then(
            (data) => {
                if (data.status === 226) {
                    commit('ADD_REGISTER_ERROR');
                } else {
                    commit('REGISTER');
                }
            },
            error => commit('ADD_REGISTER_ERROR', error),
        );
    },

    registerConfirmation({ commit }, token) {
        authService.registerConfirmation(token).then(
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
