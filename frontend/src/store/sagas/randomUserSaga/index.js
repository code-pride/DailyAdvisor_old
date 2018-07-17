import { all, takeLatest, put, call } from 'redux-saga/effects';

import {
    FETCH_RANDOM_USER_REQUESTED,
    FETCH_RANDOM_USER_STARTED,
    FETCH_RANDOM_USER_SUCCEEDED,
    FETCH_RANDOM_USER_FAILED,
    FETCH_RANDOM_USER_FINISHED,
} from '../../actions/randomUserActions';

import { requestRandomUser } from '../../../services/requester';

function* fetchRandomUser(action) {
    const params = action.payload;

    try {
        yield put({
            type: FETCH_RANDOM_USER_STARTED,
        });

        const { data } = yield call(requestRandomUser, params);
        const user = data.results[0];

        yield put({
            type: FETCH_RANDOM_USER_SUCCEEDED,
            payload: {
                ...user,
            },
        });
        yield put({
            type: FETCH_RANDOM_USER_FINISHED,
        });
    } catch (error) {
        yield put({
            type: FETCH_RANDOM_USER_FAILED,
            payload: {
                error,
            },
        });
    }
}

export default function* getRandomUserSaga() {
    yield all([takeLatest(FETCH_RANDOM_USER_REQUESTED, fetchRandomUser)]);
}
