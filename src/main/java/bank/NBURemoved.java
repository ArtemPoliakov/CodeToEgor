//package bank;
//
//import app.Client;
//
//import java.util.Map;
//
//public class NBU extends Bank {
//    public NBU(Client client) {
//        super(client);
//    }
//
//    @Override
//    public Double getCurrencyBuyRateByName(String currencyName) {
//        Map<String, Double> currenciesBuy = client.getNbuCurrencies();
//        return currenciesBuy.get(currencyName);
//    }
//
//    @Override
//    public Double getCurrencySellRateByName(String currencyName) {
//        Map<String, Double> currenciesSell = client.getNbuCurrencies();
//        return currenciesSell.get(currencyName);
//    }
//}
