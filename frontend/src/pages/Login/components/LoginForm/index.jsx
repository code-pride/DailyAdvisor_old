import React, { Fragment } from 'react';

import Button from '../../../../components/Button';

class LoginForm extends React.Component {
    state = {
        username: '',
        password: '',
    };

    render() {
        return (
            <Fragment>
                <label htmlFor="username">Username</label>
                <input id="username" value={this.state.username} type="text" />

                <label htmlFor="username">Password</label>
                <input id="password" value={this.state.password} type="password" />

                <Button url="/main" content="Zaloguj" />
            </Fragment>
        );
    }
}

export default LoginForm;
