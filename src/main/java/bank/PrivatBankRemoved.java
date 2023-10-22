//package bank;
//
//import app.Client;
//
//import java.util.Map;
//
//public class PrivatBank extends Bank {
//    public PrivatBank(Client client) {
//        super(client);
//    }
//
//    @Override
//    public Double getCurrencyBuyRateByName(String currencyName) {
//        Map<String, Double> currenciesBuy = client.getPrivatBankCurrenciesBuy();
//        return currenciesBuy.get(currencyName);
//    }
//
//    @Override
//    public Double getCurrencySellRateByName(String currencyName) {
//        Map<String, Double> currenciesSell = client.getPrivatBankCurrenciesSell();
//        return currenciesSell.get(currencyName);
//    }
//}
