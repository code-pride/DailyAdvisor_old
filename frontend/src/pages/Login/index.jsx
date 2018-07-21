import React from 'react';
import { connect } from 'react-redux';

import unknown from '../../assets/unknown.png';
import { loginUser } from '../../auth/actions';

import Image from '../../components/Image';
import { LoginForm } from './components/LoginForm';

import * as S from './styled';

class Login extends React.Component {
    state = {
        isLoginFormDisplayed: false,
    };

    toggleLogin = () => {
        this.setState({
            isLoginFormDisplayed: !this.state.isLoginFormDisplayed,
        });
    };

    render() {
        return (
            <S.Container>
                <S.LoginContainer>
                    <S.LoginBox>
                        <S.MainHeading>daily advisor</S.MainHeading>
                        <Image
                            src={unknown}
                            alt="Unknown person profile picture"
                            width="150"
                            onClick={this.toggleLogin}
                        />

                        {this.state.isLoginFormDisplayed ? (
                            <LoginForm onSubmit={this.props.loginUser} />
                        ) : null}
                    </S.LoginBox>
                </S.LoginContainer>
            </S.Container>
        );
    }
}

export default connect(
    null,
    { loginUser },
)(Login);
