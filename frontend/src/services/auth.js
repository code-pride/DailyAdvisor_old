import * as axios from 'axios';

const apiUrl = process.env.VUE_APP_API_LOCAL_URL;

const INCORRECT_CREDENTIALS_ERROR = 'Incorrect email or password.';
const GOOGLE_LOGIN_FAILED = 'Google login failed, as it is not implemented yet';
const FACEBOOK_LOGIN_FAILED = 'Facebook login failed, as it is not implemented yet';

const authService = {
    login(credentials) {
        // temporar solution, as API doesn't work yet
        console.log(credentials);
        console.log(document.cookie);
        return new Promise((resolve, reject) => {
            console.log(authService.getCookie('XSRF-TOKEN'));
            const request = axios.create({
                // withCredentials: true,
                headers: {
                    'XSRF-TOKEN': authService.getCookie('XSRF-TOKEN'),
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
                    console.log('nie pyklo')
                    console.log(error);
                    reject();
                },
            );

            // if (credentials.email === 'm@m.mm' && credentials.password === '111111') {
            //     resolve();
            // } else {
            //     reject(INCORRECT_CREDENTIALS_ERROR);
            // }
        });
    },
    loginWithGoogle() {
        return new Promise((resolve, reject) => {
            // lets just see what will happen here
            const request = axios.create({
                withCredentials: true,
                // headers: {
                //     'Access-Control-Allow-Origin': '*',
                // },
                // params: {
                //     id: 37880978,
                //     updateTime: -1,
                // },
            });

            request.get(`${apiUrl}/csrf`).then(
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
            axios.get(`${apiUrl}/login/facebook`).then(
                () => resolve(),
                () => reject(FACEBOOK_LOGIN_FAILED),
            );
        });
    },
    getCookie(name) {
        const value = `; ${document.cookie}`;
        const parts = value.split(`; ${name}=`);
        if (parts.length === 2) {
            return parts.pop().split(';').shift();
        }

        return 'spierdalaj';
    },
};

export default authService;
