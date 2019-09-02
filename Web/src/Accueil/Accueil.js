import React, {Component} from 'react';
import axios from 'axios';
import { Container, Form, Button, Row, Card } from 'react-bootstrap';
import { Redirect } from 'react-router-dom';
import { createStore } from 'redux'
import NavBar from '../NavBar/NavBar';

class Accueil extends Component {
  constructor(props) {
    super(props);
    this.handleClick = this.handleClick.bind(this);
    this.state = {
      question: null,
      isLoading: false,
      login: null,
      password: null,
    };
  }
  handleClick(e) {
    console.log(this.input.value);
    console.log(this.pass.value);
    const { history } = this.props;
    if(this.input.value == "yolo@yolo.com" && this.pass.value == "oui") {
      let path = `/`;
      this.props.history.push(path);
      //store.set('loggedIn', true);
      history.push('/');
    }
  }
  async componentDidMount() {
    const { match: { params } } = this.props;
  }

  render() {
    const {question} = this.state;
    const { isLoading } = this.state;
    console.log(this.login)
    if (this.redirect == true) {
      return <Redirect to='/'  />
    }
    var lol = localStorage.getItem('Login');
    return (
      <Container>
                        <NavBar/>
  <Row className="justify-content-md-center">
        <Card style={{ width: '25rem' }}>
  <Card.Body>
  { localStorage.getItem('Login') }
Page d'accueil
</Card.Body>
</Card>
</Row>
</Container>
    )
  }
}

export default Accueil;