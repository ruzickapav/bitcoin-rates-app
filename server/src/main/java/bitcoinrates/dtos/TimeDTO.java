package bitcoinrates.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class TimeDTO {
    Date updated;
    Date updatedIso;
    Date updatedUK;
}
