import React, {Component} from 'react';
import axios from 'axios';
import { Container, Row, Col, InputGroup, Form, Button, Card } from 'react-bootstrap';
import NavBar from '../NavBar/NavBar';
import ReactPlaceholder from 'react-placeholder';
import "react-placeholder/lib/reactPlaceholder.css";

class Contact extends Component {
  constructor(...args) {
    super(...args);

    this.state = { validated: false };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({value: event.target.value});
  }

  handleSubmit(event) {
    alert('Formulaire validé');
    event.preventDefault();
  }
    async componentDidMount() {
	/* axios.post(`localhost:9000/contact`)
	   .then(res => {
	   const x = res.data;
	   this.setState({ x });
	   })*/
	const { match: { params } } = this.props;
    }

  render() {
    return (<>
    <Container>
      <NavBar/>
    <Form>

      <Form.Row>
        <Col>
          <Form.Control placeholder="First name" />
        </Col>
        <Col>
          <Form.Control placeholder="Last name" />
        </Col>
        <Col>
        <Form.Control type="email" placeholder="Enter email" />
        </Col>
      </Form.Row>

      <Form.Group controlId="Form.ControlSelect">
        <Form.Label>Selection Sujet</Form.Label>
        <Form.Control as="select">
          <option>Feu inactif</option>
          <option>Feu clignotant</option>
          <option>Absence de feu</option>
          <option>Mauvaise trajectoire</option>
          <option>Autre</option>
        </Form.Control>
      </Form.Group>

      <Form.Group controlId="exampleForm.ControlTextarea1">
        <Form.Label>Description Sujet</Form.Label>
        <Form.Control as="textarea" rows="3" />
      </Form.Group>

        <Button variant="primary" type="submit">
          Submit
        </Button>
    </Form>
    </Container>
    </>
	   );
  }
}
export default Contact;
