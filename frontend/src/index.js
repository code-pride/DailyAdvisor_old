import React from 'react';
import { render } from 'react-dom';
import { Provider } from 'react-redux';
import { Route, Switch } from 'react-router';
import { ConnectedRouter } from 'react-router-redux';

import store, { history } from './store';

import Login from './pages/Login';
import Register from './pages/Register/Register';
import AfterRegister from './pages/Register/AfterRegister';
import RegisterConfirm from './pages/Register/RegisterConfirm';
import About from './pages/About';
import Main from './pages/Main';
import NotFound from './pages/NotFound';

import { StyledMainApp } from './styles';

const target = document.querySelector('#root');

render(
    <Provider store={store}>
        <ConnectedRouter history={history}>
            <StyledMainApp>
                <Switch>
                    <Route exact path="/" component={Login} />
                    <Route path="/login" component={Login} />
                    <Route path="/about" component={About} />
                    <Route path="/register" component={Register} />
                    <Route path="/afterRegister" component={AfterRegister} />
                    <Route path="/registerConfirm/:token" component={RegisterConfirm} />
                    <Route path="/main" component={Main} />
                    <Route component={NotFound} />
                </Switch>
            </StyledMainApp>
        </ConnectedRouter>
    </Provider>,
    target,
);
