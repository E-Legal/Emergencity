import React, {Component} from 'react';
import axios from 'axios';
import { Container, Form, Button, Row, Card } from 'react-bootstrap';
import { Redirect } from 'react-router-dom';
import { createStore } from 'redux';
import NavBar from '../NavBar/NavBar';

class Logout extends Component {
  constructor(props) {
    super(props);
    this.state = {
      question: null,
      isLoading: false,
      login: null,
      password: null,
      redirect: false,
    };
  }

  renderRedirect = () => {
    if (localStorage.getItem('Login')) {
      localStorage.removeItem('Login');
      /// INSERER REQUETE POST POUR LOGOUT, IF SUCCESS REDIRECT
      return <Redirect to='/' />
    }
  }

  async componentDidMount() {
    const { match: { params } } = this.props;
  }

  render() {
    const { isLoading } = this.state;
    console.log(this.login)
    return (
      <Container>
                <NavBar/>
        {this.renderRedirect()}
</Container>
    )
  }
}

export default Logout;