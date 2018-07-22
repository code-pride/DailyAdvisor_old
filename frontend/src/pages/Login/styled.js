import styled from 'styled-components';

import loginbg from '../../assets/loginbg.png';

export const Container = styled.div`
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

export const LoginContainer = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding: 0px 25px;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
`;

export const LoginBox = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 350px;
    justify-content: space-around;
`;

export const MainHeading = styled.h1`
    font-size: 24px;
    margin: 0;
    letter-spacing: 5px;
    font-weight: 800;
    color: #fff;
    text-transform: uppercase;
`;
