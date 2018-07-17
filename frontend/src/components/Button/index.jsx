import React, { Fragment } from 'react';
import PropTypes from 'prop-types';
import { NavLink } from 'react-router-dom';

import { StyledButton } from './styles';

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

Button.propTypes = {
    url: PropTypes.string,
    content: PropTypes.string.isRequired,
};

export default Button;
