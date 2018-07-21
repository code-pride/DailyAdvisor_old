import React, { Fragment } from 'react';
import { Route } from 'react-router';

import Calendar from '../../calendar';

export default class Main extends React.Component {
    render() {
        return (
            <Fragment>
                <div>Main</div>
                <Route path="/main/calendar" component={Calendar} />
            </Fragment>
        );
    }
}
