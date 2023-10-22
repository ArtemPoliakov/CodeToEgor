package dto;

/*
    {
        "currencyCodeA": 840,
        "currencyCodeB": 980,
        "date": 1552392228,
        "rateSell": 27,
        "rateBuy": 27.2,
        "rateCross": 27.1
    }
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonoBankCurrency {
    private Integer currencyCodeA;
    private Integer currencyCodeB;
    private Long date;
    private Double rateBuy;
    private Double rateCross;
    private Double rateSell;
}
