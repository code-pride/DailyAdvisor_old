import { all, takeLatest, put, fork, call } from 'redux-saga/effects';

import * as actions from '../actions';
import { authApi } from '../../services/Auth';

function* registerUser(action) {
    try {
        const res = yield authApi.registerUser(action.payload);
        console.log(res);
        if (res.status === 216) {
            console.log('here');
            return Promise.reject('Email already registered');
        }
        yield put(actions.registerUserFulfilled());
    } catch (err) {
        yield put(actions.registerUserRejected(err));
    }
}

function* registerUserSaga() {
    yield takeLatest(actions.REGISTER_USER, registerUser);
}

export default function* authSaga() {
    yield all([fork(registerUserSaga)]);
}
