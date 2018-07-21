import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_LOCAL_URL;

export const http = axios.create({
    baseURL: apiUrl,
});
