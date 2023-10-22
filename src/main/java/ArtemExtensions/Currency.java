package ArtemExtensions;

import lombok.Data;

@Data
public class Currency {
    private CurrencyNames name;
    private double buyPrice;
    private double sellPrice;

    public Currency(CurrencyNames name, double buyPrice, double sellPrice) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    public enum CurrencyNames{
       USD("USD:"),
       EUR("EUR:"),
       UNDEFINED("Unexpected currency");
       private String message;
       CurrencyNames(String message){
           this.message = message;
       }
       public String getMessage(){
           return message;
       }
   }
}
