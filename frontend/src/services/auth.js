import * as axios from 'axios';

const apiUrl = process.env.VUE_APP_API_LOCAL_URL;

const INCORRECT_CREDENTIALS_ERROR = 'Incorrect email or password.';
const GOOGLE_LOGIN_FAILED = 'Google login failed, as it is not implemented yet';
const FACEBOOK_LOGIN_FAILED = 'Facebook login failed, as it is not implemented yet';

const authService = {
    login(credentials) {
        // temporar solution, as API doesn't work yet
        return new Promise((resolve, reject) => {
            if (credentials.email === 'm@m.mm' && credentials.password === '111111') {
                resolve();
            } else {
                reject(INCORRECT_CREDENTIALS_ERROR);
            }
        });
    },
    loginWithGoogle() {
        return new Promise((resolve, reject) => {
            // lets just see what will happen here
            axios.get(`${apiUrl}/login/google`).then(
                () => resolve(),
                () => reject(GOOGLE_LOGIN_FAILED),
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
    }
};

export default authService;
