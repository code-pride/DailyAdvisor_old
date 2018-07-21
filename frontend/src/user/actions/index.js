export const GET_USER_DATA = 'GET_USER_DATA';
export const getUserData = () => ({
    type: GET_USER_DATA,
});

export const GET_USER_DATA_FULFILLED = 'GET_USER_DATA_FULFILLED';
export const getUserDataFulfilled = payload => ({
    type: GET_USER_DATA_FULFILLED,
    payload,
});

export const GET_USER_DATA_REJECTED = 'GET_USER_DATA_REJECTED';
export const getUserDataRejected = payload => ({
    type: GET_USER_DATA_REJECTED,
    payload,
});
