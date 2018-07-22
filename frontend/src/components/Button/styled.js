import styled from 'styled-components';

export const Button = styled.button`
    font-size: 12px;
    font-weight: 700;
    color: #fff;
    background-color: #5a5a5a;
    text-align: center;
    display: inline-block;
    position: relative;
    text-decoration: none;
    text-transform: uppercase;
    padding: 10px 35px;
    border-radius: 50px;
    border: 0;
    overflow: hidden;
    outline: none;
    cursor: pointer;

    transition: all 0.2s linear 0s;

    &:before {
        content: '+';
        font-family: FontAwesome;
        font-size: 15px;
        position: absolute;
        display: flex;
        align-items: center;
        justify-content: center;
        right: 0;
        top: 0;
        opacity: 0;
        height: 100%;
        width: 40px;
        transition: all 0.2s linear 0s;
    }

    &:hover {
        text-indent: -20px;

        &:before {
            opacity: 1;
            text-indent: 0px;
        }
    }
`;
