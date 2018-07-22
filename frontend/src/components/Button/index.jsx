import React, { Fragment } from 'react';
import { NavLink } from 'react-router-dom';

import * as S from './styled';

const Button = ({ url, content, onClick, value, type }) => (
    <Fragment>
        {type === 'submit' ? (
            <S.Button type={type}>{value}</S.Button>
        ) : url ? (
            <NavLink to={url}>
                <S.Button>{content}</S.Button>
            </NavLink>
        ) : (
            <S.Button onClick={onClick}>{content}</S.Button>
        )}
    </Fragment>
);

export default Button;
