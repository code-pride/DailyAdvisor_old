import React from 'react';

import * as S from './styled';

const SocialLogin = props => (
    <S.Link href={props.url}>
        {props.mediaType === 'google' ? 'Login with Google' : 'Login with Facebook'}
    </S.Link>
);

export default SocialLogin;
