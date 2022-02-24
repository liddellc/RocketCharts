import React, { Component } from "react";
import { sampleData } from "../sampleData";

class FlightMetadata extends Component {
  state = {};
  render() {
    return (
      <div className='pt-5'>
        <table className='table table-striped'>
          <tbody>
            <tr>
              <td>
                <strong>Date:</strong>
              </td>
              <td>{sampleData[0].date}</td>
            </tr>
            <tr>
              <td>
                <strong>Rocket:</strong>
              </td>
              <td>{sampleData[0].rocket}</td>
            </tr>
            <tr>
              <td>
                <strong>Motor:</strong>
              </td>
              <td>{sampleData[0].motor}</td>
            </tr>
            <tr>
              <td>
                <strong>Location:</strong>
              </td>
              <td>{sampleData[0].location}</td>
            </tr>
          </tbody>
        </table>
      </div>
    );
  }
}

export default FlightMetadata;
