package ArtemExtensions;

import lombok.Data;

import java.util.List;
@Data
public class Bank {
    private List<Currency> currencyList;
    private BankNames name;
    public Bank(BankNames name, List<Currency> currencyList){
        this.name = name;
        this.currencyList = currencyList;
    }

    public enum BankNames {
        MONOBANK("Monobank rates:"),
        PRIVATBANK("Privatbank rates:"),
        NBU("NBU rates:"),
        UNDEFINED_BANK("Unexpected bank");

        private String message;
        BankNames(String message){
            this.message = message;
        }
        public String getMessage(){
            return message;
        }

    }
}
