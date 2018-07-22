import React from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router-dom';

import unknown from '../../assets/unknown.png';
import { loginUser } from '../../auth/actions';
import { isLoggedInSelector } from '../../auth/selectors';

import Image from '../../components/Image';
import { LoginForm } from './components/LoginForm';
import SocialLogin from '../../components/SocialLogin';

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
        if (this.props.isLoggedIn === true) {
            return <Redirect to="/main" />;
        }

        return (
            <S.Container>
                <S.LoginContainer>
                    <S.LoginBox>
                        <S.MainHeading>daily advisor</S.MainHeading>
                        <SocialLogin url="http://localhost:8091/login/google" mediaType="google" />
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

function mapStateToProps(state) {
    return {
        isLoggedIn: isLoggedInSelector(state),
    };
}

export default connect(
    mapStateToProps,
    { loginUser },
)(Login);
