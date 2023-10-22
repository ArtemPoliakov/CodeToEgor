package dto;

/*
    [
        {
        "ccy":"EUR",
        "base_ccy":"UAH",
        "buy":"19.20000",
        "sale":"20.00000"
        },
        {
        "ccy":"USD",
        "base_ccy":"UAH",
        "buy":"15.50000",
        "sale":"15.85000"
        }
    ]
*/

import ArtemExtensions.Currency;
import ArtemExtensions.Currency.CurrencyNames;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrivatBankCurrency {
    private String ccy;
    private String base_ccy;
    private Double buy;
    private Double sale;
}
