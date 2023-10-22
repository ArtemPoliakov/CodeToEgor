package app;

import ArtemExtensions.Currency;
import ArtemExtensions.Currency.CurrencyNames;
import bank.*;

import java.util.Map;
import static java.util.Map.entry;

public class BankManager {
    private final Client client = Client.getClientObject();

    public Double getMonoBankCurrencyBuyRate(CurrencyNames currencyName) {
        Map<CurrencyNames, Double> monoBankCurrenciesBuy = client.getMonoBankCurrenciesBuy();
        System.out.println("MonoBankCurrenciesBuy: " + monoBankCurrenciesBuy);
        return monoBankCurrenciesBuy.get(currencyName);
    }

    public Double getMonoBankCurrencySellRate(CurrencyNames currencyName) {
        Map<CurrencyNames, Double> monoBankCurrenciesSell = client.getMonoBankCurrenciesSell();
        System.out.println("MonoBankCurrenciesSell: " + monoBankCurrenciesSell);
        return monoBankCurrenciesSell.get(currencyName);
    }

    public Double getPrivatBankCurrencyBuyRate(CurrencyNames currencyName) {
        Map<CurrencyNames, Double> privatBankCurrenciesBuy = client.getPrivatBankCurrenciesBuy();
        System.out.println("PrivatBankCurrenciesBuy: " + privatBankCurrenciesBuy);
        return privatBankCurrenciesBuy.get(currencyName);
    }

    public Double getPrivatBankCurrencySellRate(CurrencyNames currencyName) {
        Map<CurrencyNames, Double> privatBankCurrenciesSell = client.getPrivatBankCurrenciesSell();
        System.out.println("PrivatBankCurrenciesSell: " + privatBankCurrenciesSell);
        return privatBankCurrenciesSell.get(currencyName);
    }

    public Double getNbuCurrencyRate(CurrencyNames currencyName) {
        Map<CurrencyNames, Double> nbuCurrencies = client.getNbuCurrencies();
        System.out.println("NbuCurrencies: " + nbuCurrencies);
        return nbuCurrencies.get(currencyName);
    }
}
