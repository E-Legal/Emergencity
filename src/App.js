import React, { Component } from 'react';
import {Route} from 'react-router-dom';
import Login from './Login/Login';
import Logout from './Logout/Logout';
import Authent from './Authentication/Auhent';
import Dashboard from './Dashboard/Dashboard';
import Accueil from './Accueil/Accueil';
import Profile from './Profile/Profile'
import Contact from './Contact/Contact'
import GetFeu from './GetFeu/Getfeu'

class App extends Component {
  render() {
    return (
      <div>
        <Route exact path='/logout' component={Logout}/>
        <Route exact path='/' component={Accueil}/>
        <Route exact path='/dashboard' component={Dashboard}/>
        <Route exact path='/login' component={Login}/>
        <Route exact path='/authent' component={Authent}/>
        <Route exact path='/profile' component={Profile}/>
        <Route exact path='/contact' component={Contact}/>
        <Route exact path='/getfeu' component={GetFeu}/>
      </div>
    );
  }
}

export default App;