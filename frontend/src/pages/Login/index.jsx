import React from 'react';
import styled from 'styled-components';

import loginbg from '../../assets/loginbg.png';
import unknown from '../../assets/unknown.png';

import Image from '../../components/Image';
import Button from '../../components/Button';
import LoginForm from './components/LoginForm';

export const SContainer = styled.div`
    position: fixed;
    left: 0;
    top: 0;
    display: flex;
    flex-direction: row;
    justify-content: center;
    width: 100%;
    height: 100%;
    background-image: url(${loginbg});
    background-position: top right;
    background-size: cover;
`;

export const SLoginContainer = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding: 0px 25px;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
`;

export const SLoginBox = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 350px;
    justify-content: space-around;
`;

export const SMainHeading = styled.h1`
    font-size: 24px;
    margin: 0;
    letter-spacing: 5px;
    font-weight: 800;
    color: #fff;
    text-transform: uppercase;
`;

class Login extends React.Component {
    state = {
        loginFormDisplayed: false,
    };

    render() {
        return (
            <SContainer>
                <SLoginContainer>
                    <SLoginBox>
                        <SMainHeading>daily advisor</SMainHeading>
                        <Image src={unknown} alt="Unknown person profile picture" width="150" />

                        <LoginForm />

                        <label htmlFor="username">Username</label>
                        <input id="username" type="text" />

                        <label htmlFor="username">Password</label>
                        <input id="username" type="text" />
                        <Button url="/main" content="Zaloguj" />
                    </SLoginBox>
                </SLoginContainer>
            </SContainer>
        );
    }
}

export default Login;
