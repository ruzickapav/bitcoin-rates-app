import Plot from "react-plotly.js";
import React from "react";

const bitcoinRatesChart = props => {
    let timeValues = props.data.map(tick => tick.timestamp);
    let ratesValues= props.data.map(tick => tick.rate_float);

    return (
        <Plot data={
            [
                {
                    x: timeValues,
                    y: ratesValues,
                    type: 'scatter',
                    mode: 'lines+points',
                    marker: {color: 'red'},
                }
            ]}
              layout={
                  {
                      width: 800, height: 600,
                      title: 'Bitcoin/' + props.currency_code +' rate'
                  }
              }
        />
    );
};

export default bitcoinRatesChart;
