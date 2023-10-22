package dto;

/*
    {
        "StartDate":"23.10.2023",
        "TimeSign":"0000",
        "CurrencyCode":"840",
        "CurrencyCodeL":"USD",
        "Units":1,
        "Amount":36.5401
    }
*/

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NbuCurrency {
    @JsonProperty(value = "StartDate")
    private String startDate;
    @JsonProperty(value = "TimeSign")
    private String timeSign;
    @JsonProperty(value = "CurrencyCode")
    private Integer currencyCode;
    @JsonProperty(value = "CurrencyCodeL")
    private String currencyCodeL;
    @JsonProperty(value = "Units")
    private Integer units;
    @JsonProperty(value = "Amount")
    private Double amount;
}
