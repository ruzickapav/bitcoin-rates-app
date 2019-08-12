package bitcoinrates.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BitcoinRatesSnapshotDTO {
    TimeDTO time;
    String disclaimer;

    public TimeDTO getTime() {
        return time;
    }

    public void setTime(TimeDTO time) {
        this.time = time;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public Map<String, BitcoinRateDTO> getBpi() {
        return bpi;
    }

    public void setBpi(Map<String, BitcoinRateDTO> bpi) {
        this.bpi = bpi;
    }

    String chartName;
    Map<String, BitcoinRateDTO> bpi;
}
