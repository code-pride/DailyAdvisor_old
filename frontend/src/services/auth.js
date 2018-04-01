import * as axios from 'axios';

const INCORRECT_CREDENTIALS_ERROR = 'Incorrect email or password.';

const authService = {
    apiUrl: process.env.VUE_APP_API_LOCAL_URL,

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
        console.log('user data from auth.register method');
        console.dir(userData);


        console.log(this.apiUrl);
        return axios.get(`${this.apiUrl}/hello`);


        // return axios.post('http://localhost:8080/api/registration', userData);
    },

    // myLogin(credentials) {
    //     // 'Content-Type': 'application/x-www-form-urlencoded'

    //     const { email, password } = credentials;

    //     return axios.post('http://localhost:8080/api/login');
    // }
};

export default authService;
