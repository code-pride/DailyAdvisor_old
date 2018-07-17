import React, { Fragment } from 'react';

import { StyledButton } from './styles';
import { NavLink } from 'react-router-dom';

const Button = ({ url, content, onClick }) => (
    <Fragment>
        {url ? (
            <NavLink to={url}>
                <StyledButton>{content}</StyledButton>
            </NavLink>
        ) : (
            <StyledButton onClick={onClick}>{content}</StyledButton>
        )}
    </Fragment>
);

export default Button;
