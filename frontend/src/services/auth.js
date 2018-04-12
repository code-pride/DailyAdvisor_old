import * as axios from 'axios';

const apiUrl = process.env.VUE_APP_API_LOCAL_URL;

const INCORRECT_CREDENTIALS_ERROR = 'Incorrect email or password.';

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

    register(userData) {
        return axios.post(`${apiUrl}/registration`, userData);
    },

    hello() {
        return axios.get(`${apiUrl}/hello`);
    },

};

export default authService;
