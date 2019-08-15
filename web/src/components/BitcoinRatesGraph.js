import Plot from "react-plotly.js";
import React from "react";
import moment from 'moment'

const bitcoinRatesChart = props => {
    let timeValues = props.data.map(tick => { return moment(moment.utc(tick.timestamp).toDate()).local().format(""); });
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
