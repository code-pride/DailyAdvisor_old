import { http } from '../utils/http';

const getUserProfile = () => http.get('/getUserProfile');

export const userApi = {
    getUserProfile,
};
