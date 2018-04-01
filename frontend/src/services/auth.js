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

        const mockData = {
            email: 'mail@gmail.com',
            password: '123456',
            name: 'Marek',
            lastName: 'Makowski',
            city: 'Katowice',
        };


        console.log(this.apiUrl);

        return axios.post(`${this.apiUrl}/registration`, mockData);
    },

};

export default authService;
