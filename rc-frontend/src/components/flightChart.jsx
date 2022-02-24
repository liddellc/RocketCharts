import React, { Component } from "react";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";
import { Line } from "react-chartjs-2";
import { sampleData } from "../sampleData";

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
);

export const options = {
  responsive: true,
  interaction: {
    mode: "index",
    intersect: false,
  },
  stacked: false,
  plugins: {
    title: {
      display: true,
      text: "Chart.js Line Chart - Multi Axis",
    },
  },
  scales: {
    x: {
      type: "linear",
      //   min: 0,
      //   max: 12,
    },
    y: {
      type: "linear",
      display: true,
      position: "left",
    },
    y1: {
      type: "linear",
      display: true,
      position: "right",
      grid: {
        drawOnChartArea: false,
      },
    },
  },
};

var labels = [];
for (var i = 0; i < sampleData[0].flightData.length - 1; i++) {
  labels.push(sampleData[0].flightData[i].x);
}

export const cfg = {
  labels,
  datasets: [
    {
      label: "Altitude",
      data: sampleData[0].flightData,
      parsing: {
        yAxisKey: "alt",
      },
      borderColor: "rgb(255, 99, 132)",
      backgroundColor: "rgba(255, 99, 132, 0.5)",
      yAxisID: "y",
    },
    {
      label: "Velocity",
      data: sampleData[0].flightData,
      parsing: {
        yAxisKey: "vel",
      },
      borderColor: "rgb(53, 162, 235)",
      backgroundColor: "rgba(53, 162, 235, 0.5)",
      yAxisID: "y1",
    },
    {
      label: "Apogee",
      data: sampleData[0].flightData,
      parsing: {
        yAxisKey: "apo",
      },
      borderColor: "rgb(0, 128, 0)",
      backgroundColor: "rgba(0, 128, 0, 0.5)",
      yAxisID: "y",
      pointRadius: 8,
      pointHoverRadius: 16,
    },
    {
      label: "Nose-over",
      data: sampleData[0].flightData,
      parsing: {
        yAxisKey: "no",
      },
      borderColor: "rgb(0, 128, 0)",
      backgroundColor: "rgba(0, 128, 0, 0.5)",
      yAxisID: "y",
      pointRadius: 8,
      pointHoverRadius: 16,
    },
    {
      label: "Drogue",
      data: sampleData[0].flightData,
      parsing: {
        yAxisKey: "dro",
      },
      borderColor: "rgb(0, 128, 0)",
      backgroundColor: "rgba(0, 128, 0, 0.5)",
      yAxisID: "y",
      pointRadius: 8,
      pointHoverRadius: 16,
    },
    {
      label: "Main",
      data: sampleData[0].flightData,
      parsing: {
        yAxisKey: "main",
      },
      borderColor: "rgb(0, 128, 0)",
      backgroundColor: "rgba(0, 128, 0, 0.5)",
      yAxisID: "y",
      pointRadius: 8,
      pointHoverRadius: 16,
    },
  ],
};

class FlightChart extends Component {
  state = {};
  render() {
    return (
      <div>
        <Line options={options} data={cfg} />
      </div>
    );
  }
}

export default FlightChart;
