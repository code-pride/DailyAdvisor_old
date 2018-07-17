import { all, fork } from 'redux-saga/effects';

import getRandomUserSaga from './randomUserSaga';

export default function* rootSaga() {
    yield all([fork(getRandomUserSaga)]);
}
