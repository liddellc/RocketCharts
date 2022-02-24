import React, { Component } from "react";

import { sampleData } from "../sampleData";

class FlightSelector extends Component {
  state = {};
  render() {
    return (
      <div className='flight-selector p-2'>
        <select className='form-select'>
          <option selected>Open this select menu</option>
          {sampleData.map((flight) => (
            <option value={flight.id}>
              {flight.date +
                " @ " +
                flight.location +
                ": " +
                flight.rocket +
                " on " +
                flight.motor}
            </option>
          ))}
        </select>
      </div>
    );
  }
}

export default FlightSelector;
