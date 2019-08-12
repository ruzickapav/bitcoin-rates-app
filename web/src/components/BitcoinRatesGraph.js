import Plot from "react-plotly.js";
import React from "react";

const bitcoinRatesChart = props => {
    return (
        <Plot data={
            [
                {
                    x: [1, 2, 3],
                    y: [2, 6, 3],
                    type: 'scatter',
                    mode: 'lines+points',
                    marker: {color: 'red'},
                }
            ]}
              layout={
                  {
                      width: 320, height:
                          240, title:
                          'A Fancy Plot'
                  }
              }
        />
    );
};

export default bitcoinRatesChart;
