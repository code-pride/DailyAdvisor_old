import { combineEpics } from 'redux-observable';

import { authEpicFactory } from './auth/epics';

export const rootEpic = combineEpics(authEpicFactory());
