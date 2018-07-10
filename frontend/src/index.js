import React from 'react';
import { render } from 'react-dom';
import { Provider } from 'react-redux';
import { Route, Switch } from 'react-router';
import { ConnectedRouter } from 'react-router-redux';

import store, { history } from './store';

import Main from './pages/Main';
import Login from './pages/Login';

import { StyledMainApp } from './styles';

const target = document.querySelector('#root');

render(
    <Provider store={store}>
        <ConnectedRouter history={history}>
            <StyledMainApp>
                <Switch>
                    <Route exact path="/" component={Login} />
                    <Route path="/login" component={Login} />
                    <Route path="/main" component={Main} />
                </Switch>
            </StyledMainApp>
        </ConnectedRouter>
    </Provider>,
    target
);
