import React from 'react';

import { giveMeUrl } from '../../../services/Auth/';

class Register extends React.Component {
    componentDidMount() {
        giveMeUrl();
    }

    render() {
        return <div>Register component</div>;
    }
}

export default Register;
