import {
    combineReducers
} from 'redux';
import {
    routerReducer
} from 'react-router-redux';
import randomUserReducer from './randomUserReducer';

export default combineReducers({
    randomUserReducer,
    router: routerReducer,
});
