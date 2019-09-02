import React from 'react';
import { Navbar, Nav, NavDropdown } from 'react-bootstrap';


function dropDownMenu() {
  return(

  <NavDropdown title= {localStorage.getItem('Login')} id="collasible-nav-dropdown">
  <NavDropdown.Item href="/profile">Profile</NavDropdown.Item>
  <NavDropdown.Item href="/logout">Logout</NavDropdown.Item>
</NavDropdown>
  );
}

    function isConnected() {
      if (!localStorage.getItem('Login')) {
        return <> <Nav.Link href="/login">Login</Nav.Link> <Nav.Link href="/authent">Authentification</Nav.Link> </>
    }
    else {
      return dropDownMenu()
    }
    }

function NavBar() {

  return (
<Navbar fixed="top" collapseOnSelect expand="lg" bg="primary" variant="dark">
  <Navbar.Brand href="/">Dashboard</Navbar.Brand>
  <Navbar.Toggle aria-controls="responsive-navbar-nav" />
  <Navbar.Collapse id="responsive-navbar-nav">
    <Nav className="mr-auto">
      { isConnected() }
      <Nav.Link href="/dashboard">Dashboard</Nav.Link>
      <Nav.Link href="/contact">Contact</Nav.Link>
      <Nav.Link href="/getfeu">Get Feu</Nav.Link>
    </Nav>
  </Navbar.Collapse>
</Navbar>

  );
}

export default NavBar;