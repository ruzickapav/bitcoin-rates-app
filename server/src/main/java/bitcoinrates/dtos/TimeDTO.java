package bitcoinrates.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeDTO {
    Date updatedISO;

    public Date getUpdatedISO() {
        return updatedISO;
    }

    public void setUpdatedISO(Date updatedISO) {
        this.updatedISO = updatedISO;
    }
}
