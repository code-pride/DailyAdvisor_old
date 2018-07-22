import React, { Fragment } from 'react';
import { connect } from 'react-redux';
import { NavLink } from 'react-router-dom';

import { getUserData } from '../../user/actions';

class Main extends React.Component {
    componentDidMount() {
        this.props.getUserData();
    }

    render() {
        return (
            <Fragment>
                <div>Main</div>
                <NavLink to={'../'}>dupa</NavLink>
            </Fragment>
        );
    }
}

export default connect(
    null,
    { getUserData },
)(Main);
