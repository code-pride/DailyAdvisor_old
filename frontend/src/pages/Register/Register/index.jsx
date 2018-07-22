import React from 'react';
import { connect } from 'react-redux';

import { RegisterForm } from './RegisterForm';
import { registerUser } from '../../../auth/actions';

class Register extends React.Component {
    render() {
        return <RegisterForm onSubmit={this.props.registerUser} />;
    }
}

export default connect(
    null,
    { registerUser },
)(Register);
