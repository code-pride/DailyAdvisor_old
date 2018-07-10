import React from 'react';
import { connect } from 'react-redux';
import styled from 'styled-components';
import { Route, Switch } from 'react-router';

import Avatar from '../../components/Avatar';
import Button from '../../components/Button';
import Header from '../../components/Header';
import NavigationMenu from '../../components/NavigationMenu';
import { FETCH_RANDOM_USER_REQUESTED } from '../../store/actions/randomUserActions';
import profilePicture from '../../assets/avatar.png';

import Dashboard from '../../pages/Dashboard';
import Categories from '../../pages/Categories';

import { gray, lightGray} from '../../constants/colors';

import { navigationItems } from '../../components/NavigationMenu/navgiationItems';
import CategoryItemPage from '../CategoryItemPage';

const SBackgroundMainContainer = styled.div`
    position: fixed;
    left: 0;
    top: 0;    
    width: 100%;
    height: 100%;
    background-color: #fff;
    overflow: auto;
`

const SMainContainer = styled.div`
    display: grid;
    grid-template-columns: 250px auto;
    grid-template-rows: 50px auto;
    grid-template-areas: 
        "header header"
        "sidenav content";
`

const SMainSidenav = styled.div`
    grid-area: sidenav;
    padding: 50px 0 0 0;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
    box-shadow: 2px 9px 27px -10px rgba(0,0,0,0.2);
    height: 100%;
    background-color: ${lightGray};
    z-index: 10;
`

const SMainContent = styled.div`
    grid-area: content;
`

const SMainProfileStatus = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 350px;
    justify-content: space-around;
    margin-bottom: 50px;
`

const STextStatus = styled.div`
    p {
        text-align: center;
        font-size: 14px;
        margin: 0;
        color: ${gray};
    }

    h2 {
        margin: 5px 0 0 0;
        color: ${gray};
        text-transform: capitalize;
    }
`

class Main extends React.Component {
    trySaga = () => {
        this.props.dispatch({ type: FETCH_RANDOM_USER_REQUESTED });
    };

    render() {
        return (
            <SBackgroundMainContainer>
                <SMainContainer>
                    <Header />
                    <SMainSidenav>
                        <SMainProfileStatus>
                            {
                                this.props.userData.name ?
                                <Avatar src={this.props.userData.picture.large} alt="Unknown person profile picture" width="150" />
                                :
                                <Avatar src={profilePicture} alt="Logged in person" width="150" />
                            }
                            <STextStatus>
                                <p>zalogowany jako</p>
                                { 
                                    this.props.userData.name ? 
                                    <h2>{ this.props.userData.name.first } { this.props.userData.name.last }</h2> 
                                    :
                                    <h2>marcin krawczyk</h2>
                                }
                            </STextStatus>
                            <Button url="/login" content="Wyloguj"></Button>
                            <Button content="Get new user!" onClick={this.trySaga} />
                        </SMainProfileStatus>
                        <NavigationMenu navigationItems={navigationItems} />
                    </SMainSidenav>
                    <SMainContent>
                        <Switch>
                            <Route path={`${this.props.match.path}/dashboard`} component={Dashboard} />
                            <Route exact path={`${this.props.match.path}/categories`} component={Categories} />

                            <Route path={`${this.props.match.path}/categories/:categoryId`} component={CategoryItemPage} />
                        </Switch>
                    </SMainContent>
                </SMainContainer>
            </SBackgroundMainContainer>
        )
    }
}

const mapStateToProps = (state) => ({
    userData: state.randomUserReducer.userData,
});


export default connect(mapStateToProps, null)(Main);
