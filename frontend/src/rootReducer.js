import { combineReducers } from 'redux';

import { calendar } from './calendar/reducers/calendar';

export const rootReducer = combineReducers({
    calendar,
});
