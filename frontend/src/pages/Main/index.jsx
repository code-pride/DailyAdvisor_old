import React, { Fragment } from 'react';
import { connect } from 'react-redux';

import { getUserData } from '../../user/actions';

class Main extends React.Component {
    componentDidMount() {
        this.props.getUserData();
    }

    render() {
        return (
            <Fragment>
                <div>Main</div>
            </Fragment>
        );
    }
}

export default connect(
    null,
    { getUserData },
)(Main);
