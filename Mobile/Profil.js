import React from 'react';
import {
  FlatList,
  ActivityIndicator,
  Text,
  View,
  Image,
  ImageBackground,
  StyleSheet,
  TouchableOpacity,
  TouchableWithoutFeedback,
  TextInput,
  Keyboard,
  KeyboardAvoidingView,
  StatusBar,
  Dimensions,
} from 'react-native';
import { StackNavigator } from 'react-navigation';
import PropTypes from 'prop-types';
import { LinearGradient } from 'expo-linear-gradient';
import Icon from 'react-native-vector-icons/FontAwesome';
import { Button, ThemeProvider, Header, Avatar } from 'react-native-elements';

const { width: WIDTH } = Dimensions.get('window');
const DismissKeyboard = ({ children }) => (
  <TouchableWithoutFeedback onPress={() => Keyboard.dismiss()}>
    {children}
  </TouchableWithoutFeedback>
);

var jsonQuery = require('json-query');
var data = {
  grouped_people: {
    Pompier: [
      {
        name: 'Yanis',
        country: 'Marseille',
        vehicule: 'Voiture',
        grade: 'Stagiaire',
      },
      {
        name: 'Eliott',
        country: 'Marseille',
        vehicule: 'Camion',
        grade: 'Caporale',
      },
      {
        name: 'Sophie',
        country: 'Marseille',
        vehicule: 'Vélo',
        grade: 'Officer',
      },
      {
        name: 'Colin',
        country: 'Marseille',
        vehicule: 'Moto',
        grade: 'Capitain',
      },
      {
        name: 'Quentin',
        country: 'Marseille',
        vehicule: 'Trotinette',
        grade: 'Capitain',
      },
    ],
  },
};

var result = jsonQuery('grouped_people[Pompier][*name=Eliott]', { data: data })
  .value;
console.log(result);

export default class FetchExample extends React.Component {
  constructor(props) {
    super(props);
    this.state = { TextInput: '' };
  }

  _handlePress() {
    //Handler for the Submit onPress
    if (this.state.TextInput != '') {
      //Check for the Name TextInput
    } else {
      alert('Please Enter text');
      return;
    }
    console.log('Username: ' + this.state.TextInput);
  }

  render() {
    return (
      <DismissKeyboard>
        <View>
          <ImageBackground
            source={require('./img/background.jpg')}
            style={{ width: '100%', height: '100%', alignItems: 'center' }}>
            <Header
              leftComponent={{ icon: 'keyboard-arrow-left', color: '#fff', onPress: () =>{this.props.navigation.navigate('Login');} }}
              centerComponent={{ text: 'PROFILE', style: { color: '#fff' } }}
              rightComponent={{ icon: 'map', color: '#fff'}}
              containerStyle={{ backgroundColor: 'transparent',justifyContent: 'space-around',}}
            />
            <Avatar
              size="xlarge"
              rounded
              title="EL"
              onPress={() => alert('Works!')}
              containerStyle={{ marginTop: 30 }}
            />
            <StatusBar hidden="true" />
            <FlatList
              contentContainerStyle={{
                flexGrow: 1,
                paddingTop: 60,
                alignItems: 2,
              }}
              data={result}
              renderItem={({ item }) => (
                <Text style={styles.Profileinf}>
                  Nom: {item.name}
                  {'\n'}
                  Véhicule: {item.vehicule}
                  {'\n'}
                  Grade: {item.grade}
                </Text>
              )}
              keyExtractor={({ id }, index) => id}
            />
            <KeyboardAvoidingView
              behavior="position"
              style={styles.InputContainer}>
              <TextInput
                onChangeText={TextInput => this.setState({ TextInput })}
                style={styles.input}
                placeholder={'Some text'}
                placeholderTextColor={'rgba(255, 255, 255, 0.7)'}
                underLineColorAndroid="transparent"
              />
              <TouchableOpacity
                style={styles.btnLogin}
                onPress={() => this._handlePress()}>
                <Text style={styles.text}>Search</Text>
              </TouchableOpacity>
            </KeyboardAvoidingView>
          </ImageBackground>
        </View>
      </DismissKeyboard>
    );
  }
}

const styles = StyleSheet.create({
  Profileinf: {
    fontSize: 20,
    color: 'white',
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
