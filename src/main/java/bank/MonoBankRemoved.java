//package bank;
//
//import app.Client;
//
//import java.util.Map;
//
//public class MonoBank extends Bank {
//    public MonoBank(Client client) {
//        super(client);
//    }
//
//    @Override
//    public Double getCurrencyBuyRateByName(String currencyName) {
//        Map<String, Double> currenciesBuy = client.getMonoBankCurrenciesBuy();
//        return currenciesBuy.get(currencyName);
//    }
//
//    @Override
//    public Double getCurrencySellRateByName(String currencyName) {
//        Map<String, Double> currenciesSell = client.getMonoBankCurrenciesSell();
//        return currenciesSell.get(currencyName);
//    }
//}
