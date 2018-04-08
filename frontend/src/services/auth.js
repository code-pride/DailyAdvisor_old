import * as axios from 'axios';


axios.defaults.baseUrl = process.env.VUE_APP_API_LOCAL_URL;

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

        return axios.post('/registration', mockData);
    },

    hello() {
        return axios.get('/hello');
    }

};

export default authService;
