import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_LOCAL_URL;

export const requestRandomUser = () => axios.get('https://randomuser.me/api/');
export const giveMeUrl = () => console.log(apiUrl);

const authService = {
    login(credentials) {
        console.log('tutaj bedzie logowanie');
        // temporar solution, as API doesn't work yet
        // return new Promise((resolve, reject) => {
        //     if (credentials.email === 'm@m.mm' && credentials.password === '111111') {
        //         resolve();
        //     } else {
        //         reject(INCORRECT_CREDENTIALS_ERROR);
        //     }
        // });
    },

    register(userData) {
        console.log('rejestrowanie');
        // return axios.post(`${apiUrl}/registration`, userData);
    },

    registerConfirmation(token) {
        console.log('konfirmacja rejestracji');
        // return axios.post(`${apiUrl}/registrationConfirm`, { token });
    },

    hello() {
        console.log('hello');
        // return axios.get(`${apiUrl}/hello`);
    },
};

export default authService;
