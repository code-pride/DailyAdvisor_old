import React from 'react';
import styled from 'styled-components';

import Avatar from '../../components/Avatar';
import Button from '../../components/Button';
import profilePictureUnknown from '../../assets/unknown.png';
import loginBackground1 from '../../assets/loginBackground1.jpg';

export const StyledContainer = styled.div`
    position: fixed;
    left: 0;
    top: 0;
    display: flex;
    flex-direction: row;
    justify-content: center;
    width: 100%;
    height: 100%;
    background-image: url(${loginBackground1});
    background-size: cover;
    background-position: center center;
`
export const LoginContainer = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding: 0px 50px;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
`

export const LoginBox = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 350px;
    justify-content: space-around;
`

export const StyledMainHeading = styled.h1`
    margin: 0;
    letter-spacing: 5px;
    font-weight: 900;
    color: #fff;
`

const Login = () => (
    <StyledContainer>
        <LoginContainer>
            <LoginBox>
                <StyledMainHeading>RECIPIES</StyledMainHeading>
                <Avatar src={profilePictureUnknown} alt="Unknown person profile picture" width="150" />
                <Button url="/main" content="Zaloguj" />
            </LoginBox>
        </LoginContainer>
    </StyledContainer>
)

export default Login;
