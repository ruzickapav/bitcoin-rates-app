import React, {Component} from 'react';
import BitcoinRatesGraph from "./components/BitcoinRatesGraph";
import './App.css';
import axios from 'axios';
import moment from 'moment'

class App extends Component {

    state = {
        rateHistory: []
    };

    componentDidMount() {
        const format = "YYYY-MM-DDTHH:MM:ss";
        let dateTo = new moment().format(format);
        let dateFrom = new moment().subtract(1, 'day').format(format);
        debugger;
        axios.get('http://localhost:8080/rates/USD/', {
            params: {fromDate: dateFrom, toDate: dateTo}
        }).then(response => this.setState({rateHistory: response.data}))
    }

    render() {
        return (
            <div className="App">
                <BitcoinRatesGraph data={this.state.rateHistory} currency_code={'USD'}/>
            </div>
        );
    }
}

export default App;
