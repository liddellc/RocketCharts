import React, { Component } from "react";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import Container from "react-bootstrap/Container";

class NavBar extends Component {
  state = {};
  render() {
    return (
      <div>
        <Navbar collapseOnSelect expand='sm' bg='dark' variant='dark'>
          <Container fluid>
            <Navbar.Brand href='#home'>RocketCharts 🚀📈</Navbar.Brand>
            <Navbar.Toggle />
            <Navbar.Collapse>
              <Nav className='ms-auto'>
                <Nav.Link href='#upload'>+Upload</Nav.Link>
                <Nav.Link href='#settings'>Settings</Nav.Link>
                <Nav.Link href='#logout'>Logout</Nav.Link>
              </Nav>
            </Navbar.Collapse>
          </Container>
        </Navbar>
      </div>
    );
  }
}

export default NavBar;
