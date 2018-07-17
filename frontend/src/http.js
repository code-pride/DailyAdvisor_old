import axios from 'axios';

const apiUrl = 'http://localhost:3000';

export const http = axios.create({
    baseURL: apiUrl,
});
