import React, {Component} from 'react';
import axios from 'axios';
import { Container, Form, Button, Row, Card } from 'react-bootstrap';
import { Redirect } from 'react-router-dom';
import { createStore } from 'redux';
import NavBar from '../NavBar/NavBar';


class GetFeu extends Component {
 
    async componentDidMount() {
        /* axios.get(`localhost:8080/login`)
         .then(res => {
           const persons = res.data;
           this.setState({ persons });
         })*/
         const { match: { params } } = this.props;
       }
       
    render() {
      return ( <>
        <NavBar/>
       </> )
      };
    }

    export default GetFeu