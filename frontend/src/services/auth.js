import * as axios from 'axios';

import { getCookie } from '../utils/helpers';

const apiUrl = process.env.VUE_APP_API_LOCAL_URL;

const INCORRECT_CREDENTIALS_ERROR = 'Incorrect email or password.';
const GOOGLE_LOGIN_FAILED = 'Google login failed, as it is not implemented yet';
const FACEBOOK_LOGIN_FAILED = 'Facebook login failed, as it is not implemented yet';

const authService = {
    login(credentials) {
        return new Promise((resolve, reject) => {
            const xcsrf = getCookie('XSRF-TOKEN');

            const loginRequest = () => {
                const request = axios.create({
                    withCredentials: true,
                    headers: {
                        'XSRF-TOKEN': getCookie('XSRF-TOKEN'),
                    },
                });

                request.post(`${apiUrl}/login`, {
                    username: credentials.email,
                    password: credentials.password,
                }).then(
                    () => {
                        console.log('SUCCES!');
                        resolve();
                    },
                    (error) => {
                        console.log(error);
                        reject(INCORRECT_CREDENTIALS_ERROR);
                    },
                );
            };

            if (xcsrf != null) {
                loginRequest();
            } else {
                const request = axios.create({
                    withCredentials: true,
                });

                request.get(`${apiUrl}/csrf`).then(
                    () => {
                        console.log(document.cookie);
                        loginRequest();
                    },
                    () => {
                        console.log(document.cookie);
                        reject();
                    },
                );
            }
        });
    },

    loginWithGoogle() {
        return new Promise((resolve, reject) => {
            // lets just see what will happen here
            const request = axios.create({
                withCredentials: true,
            });

            // request.get(`${apiUrl}/csrf`).then(
            request.get('http://www.google.pl').then(
                () => {
                    console.log(document.cookie);
                    resolve();
                },
                () => {
                    console.log(document.cookie);
                    reject(GOOGLE_LOGIN_FAILED);
                },
            );
        });
    },

    loginWithFacebook() {
        return new Promise((resolve, reject) => {
            // lets just see what will happen here
            const request = axios.create({
                withCredentials: true,
            });

            request.get('http://localhost:8091/oauth/authorize?redirect_uri=http://localhost:8080/&client_id=frontendClientId&response_type=token&audience=fdsfdsf&scope=read&state=fsdfsdfsdfsdf').then(
                () => {
                    console.log(document.cookie);
                    resolve();
                },
                () => {
                    console.log(document.cookie);
                    reject(FACEBOOK_LOGIN_FAILED);
                },
            );
        });
    },

    register(userData) {
        return axios.post(`${apiUrl}/registration`, userData);
    },

    pobierz() {
        const request = axios.create({
            withCredentials: true,
            headers: {
                'Authorization': "Bearer",
            },
        });
    },

    registerConfirmation(token) {
        return axios.post(`${apiUrl}/registrationConfirm`, { token });
    },

    hello() {
        return axios.get(`${apiUrl}/hello`);
    },
};

export default authService;
