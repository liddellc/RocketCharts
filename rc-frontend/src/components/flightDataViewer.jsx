import React, { Component } from "react";
import FlightChart from "./components/flightChart";
import FlightMetadata from "./components/flightMetadata";

class FlightDataViewer extends Component {
  state = {};
  render() {
    return (
      <Container>
        <Row>
          <Col>
            <FlightChart />
          </Col>

          <Col>
            <FlightMetadata />
          </Col>
        </Row>
      </Container>
    );
  }
}

export default FlightDataViewer;
