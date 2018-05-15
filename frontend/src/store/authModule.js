import auth from '../services/auth';

const state = {
    registerErrorMessage: '',
    registerConfirmationMessage: '',
    isRegistered: false,
    isAuthenticated: false,
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
    isAuthenticated() {
        return state.isAuthenticated;
    },
    authenticationErrorOccured() {
        return state.authenticationError !== '';
    },
};

const mutations = {
    ADD_REGISTER_ERROR(state) {
        state.isRegistered = false;
        state.registerErrorMessage = 'Użytkownik z takim adresem email już istnieje.';
    },
    ADD_REGISTER_CONFIRMATION_SUCCES(state) {
        state.registerConfirmationMessage = 'Twoje konto zostało potwierdzone. Możesz się teraz bezpiecznie zalogować.';
    },
    ADD_REGISTER_CONFIRMATION_ERROR() {
        state.registerConfirmationMessage = 'Konto zostało już wcześniej aktywowane';
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
        return auth.register(userData).then(
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
