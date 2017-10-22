import React, {Component} from 'react';
import { StyleSheet, View, TextInput, TouchableOpacity, Text } from 'react-native';


let email = "joe@example.com";  
let password = "donkeybrains";  
let myApiUrl = "http://www.example.com/api"  
let usersPath = "users"


export default class LoginForm extends Component {

   state = {
      username: '',
      password: ''
   }
   handleUsername = (text) => {
      this.setState({ username: text })
   }
   handlePassword = (text) => {
      this.setState({ password: text })
   }
   login = (email, pass) => {
      fetch('http://192.168.10.160:3000/v1/User/Login', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
        body: JSON.stringify({
          username: email,
          password: pass,
        })
      })
      .then((response) => response.json()) 
      .then((responseJson) => { alert("Login Success: " + responseJson.Login) }) 
      .catch((error) => { console.error(error); });
   }

  render() {
    return (
      <View style={styles.container}>
        <TextInput placeholder="username" onChangeText = {this.handleUsername} returnKeyType="next" onSubmitEditing={()=>this.passwordInput.focus()} keyboardType="email-address" autoCapitalize="none" autoCorrect={false} style={styles.input}> </TextInput>
        <TextInput placeholder="password" secureTextEntry onChangeText = {this.handlePassword} style={styles.input} ref={(input) => this.passwordInput = input}> </TextInput>
        
        <TouchableOpacity style={styles.buttonContainer} onPress = {() => this.login(this.state.username, this.state.password)}  > 
          <Text style={styles.buttonText}> LOGIN </Text>
        </TouchableOpacity>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    padding:10

  },
  input: {
    height: 40,
    backgroundColor: 'rgba(255,255,255,0.7)',
    marginBottom: 20,
    paddingHorizontal: 10,
    width: 200
  },
  buttonContainer: {
    backgroundColor: '#2980b9',
    paddingVertical: 15
  },
  buttonText:{
    textAlign: 'center',
    color: '#FFFFFF',
    fontWeight: '700'
  }
});