import axios from 'axios';

export const requestRandomUser = () => axios.get('https://randomuser.me/api/');
