import { all } from 'redux-saga/effects';

import getRandomUserSaga from './randomUserSaga';
import authSaga from '../../auth/sagas';

export default function* rootSaga() {
    yield all([getRandomUserSaga(), authSaga()]);
}
