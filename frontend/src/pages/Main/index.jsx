import React, { Fragment } from 'react';
import { connect } from 'react-redux';

import { FETCH_RANDOM_USER_REQUESTED } from '../../store/actions/randomUserActions';

import Button from '../../components/Button';

class Main extends React.Component {
    trySaga = () => {
        this.props.dispatch({ type: FETCH_RANDOM_USER_REQUESTED });
    };

    render() {
        return (
            <Fragment>
                <div>Main</div>
                <Button onClick={this.trySaga} content="try saga" />
            </Fragment>
        );
    }
}

const mapStateToProps = state => ({
    userData: state.randomUserReducer.userData,
});

export default connect(
    mapStateToProps,
    null,
)(Main);
