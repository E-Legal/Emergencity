import React, { Component } from 'react';
import {
  StyleSheet,
  Text,
  View,
  ImageBackground,
  Image,
  TextInput,
  Dimensions,
  TouchableOpacity,
  TouchableWithoutFeedback,
  Keyboard,
  KeyboardAvoidingView,
  StatusBar,
  Linking,
} from 'react-native';
import { Button, ThemeProvider, Header, Avatar } from 'react-native-elements';
import { StackNavigator, SafeAreaView } from 'react-navigation';

import Profil from './Profil';
import bgImage from './img/background.jpg';
import logo from './img/logo.png';

const DismissKeyboard = ({ children }) => (
  <TouchableWithoutFeedback onPress={() => Keyboard.dismiss()}>
    {children}
  </TouchableWithoutFeedback>
);
const { width: WIDTH } = Dimensions.get('window');

export default class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      TextInputpassword: '',
      TextInputusername: '',
    };
  }

  _handlePress() {
    //Handler for the Submit onPress
    if (this.state.TextInputusername != '') {
      //Check for the Name TextInput
      if (this.state.TextInputpassword != '') {
        //Check for the Email TextInput
      } else {
        alert('Please Enter password');
        return;
      }
    } else {
      alert('Please Enter username');
      return;
    }
    console.log('Username: ' + this.state.TextInputusername);
    console.log('Password: ' + this.state.TextInputpassword);
    this.props.navigation.navigate('Profil');
  }

  render() {
    return (
      <DismissKeyboard>
        <ImageBackground source={bgImage} style={styles.backgroundContainer}>
          <Header
            centerComponent={{ text: 'LOGIN PAGE', style: { color: '#fff' } }}
            rightComponent={{ icon: 'language', color: '#fff', onPress: () =>{ Linking.openURL('https://eip.epitech.eu/2021/emergencity/')} }}
            containerStyle={{
              backgroundColor: 'transparent',
              justifyContent: 'space-around',
            }}
          />
          <View style={styles.logoContainer}>
            <StatusBar barStyle="light-content" />
            <Image source={logo} style={styles.logo}/>
          </View>
          <KeyboardAvoidingView
            behavior="position"
            style={styles.InputContainer}>
            <TextInput
              onChangeText={TextInputusername =>
                this.setState({ TextInputusername })
              }
              style={styles.input}
              placeholder={'Username'}
              placeholderTextColor={'rgba(255, 255, 255, 0.7)'}
              underLineColorAndroid="transparent"
            />
            <TextInput
              onChangeText={TextInputpassword =>
                this.setState({ TextInputpassword })
              }
              style={styles.input}
              placeholder={'Password'}
              secureTextEntry={true}
              placeholderTextColor={'rgba(255, 255, 255, 0.7)'}
              underLineColorAndroid="transparent"
            />
            <TouchableOpacity
              style={styles.btnLogin}
              onPress={() => this._handlePress()}>
              <Text style={styles.text}>Login</Text>
            </TouchableOpacity>
          </KeyboardAvoidingView>
        </ImageBackground>
      </DismissKeyboard>
    );
  }
}

const styles = StyleSheet.create({
  backgroundContainer: {
    flex: 1,
    width: null,
    height: null,
    alignItems: 'center',
  },
  logoContainer: {
    alignItems: 'center',
    marginBottom: 180,
    paddingTop: 5,
  },
  logo: {
    width: 200,
    height: 200,
  },
  input: {
    width: WIDTH - 55,
    height: 45,
    borderRadius: 25,
    fontSize: 16,
    textAlign: 'center',
    marginBottom: 5,
    backgroundColor: 'rgba(0, 0, 0, 0.35)',
    color: 'rgba(255, 255, 255, 0.7)',
  },
  InputContainer: {
    marginBottom: 10,
    alignItems: 'center',
    textAlign: 'center',
  },
  btnLogin: {
    width: WIDTH - 55,
    height: 45,
    borderRadius: 25,
    backgroundColor: '#222f3e',
    justifyContent: 'center',
    marginTop: 10,
  },
  text: {
    color: 'rgba(255, 255, 255, 0.7)',
    fontSize: 16,
    textAlign: 'center',
  },
});