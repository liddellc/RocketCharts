import React from "react";
import FlightSelector from "./components/flightSelector";
import NavBar from "./components/navBar";
import FlightChart from "./components/flightChart";
import FlightMetadata from "./components/flightMetadata";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

export function App() {
  return (
    <React.Fragment>
      <NavBar />
      <FlightSelector />
      <Container>
        <Row>
          <Col sm={10}>
            <FlightChart />
          </Col>

          <Col>
            <FlightMetadata />
          </Col>
        </Row>
      </Container>
    </React.Fragment>
  );
}
