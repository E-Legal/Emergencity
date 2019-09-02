import Login from './Login';
import Profil from './Profil';
import React, { Component } from 'react';
import {
  createStackNavigator,
  createAppContainer,
  StackNavigator,
} from 'react-navigation';

const App = createStackNavigator(
  {
    Login: { screen: Login },
    Profil: { screen: Profil },
  },
  {
    initialRouteName: 'Login',
    mode: 'modal',
    headerMode: 'none',
    cardStyle: {backgroundColor: 'transparent'},
    transitionConfig: () => ({
     containerStyle: {
      }
    }),
  }
);
export default createAppContainer(App);
