import { http } from '../utils/http';

const registerUser = user => http.post('/registration', user);
const loginUser = user => http.post('/login', user);
const getUserProfile = () => http.get('/getUserProfile');
const getCsrf = () => http.get('/csrf');

export const authApi = {
    getCsrf,
    registerUser,
    loginUser,
    getUserProfile,
};
