import axios from 'axios';

import { http } from '../../http';

export const requestRandomUser = () => axios.get('https://randomuser.me/api/');

const registerUser = user => http.post('/registration', user);

export const authApi = {
    registerUser,
};
