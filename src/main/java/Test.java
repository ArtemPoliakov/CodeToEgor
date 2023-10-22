import ArtemExtensions.Currency;
import app.BankManager;
import bank.*;

import java.io.IOException;
import java.net.URISyntaxException;

import static ArtemExtensions.Currency.CurrencyNames.EUR;
import static ArtemExtensions.Currency.CurrencyNames.USD;

public class Test {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        BankManager manager = new BankManager();
        Thread.sleep(2000);

        System.out.println("PrivatBank USD Buy: " + manager.getPrivatBankCurrencyBuyRate(USD));
        System.out.println("PrivatBank USD Sell: " + manager.getPrivatBankCurrencySellRate(USD));
        System.out.println("PrivatBank EUR Buy: " + manager.getPrivatBankCurrencyBuyRate(EUR));
        System.out.println("PrivatBank EUR Sell: " + manager.getPrivatBankCurrencySellRate(EUR));

        System.out.println("MonoBank USD Buy: " + manager.getMonoBankCurrencyBuyRate(USD));
        System.out.println("MonoBank USD Sell: " + manager.getMonoBankCurrencySellRate(USD));
        System.out.println("MonoBank EUR Buy: " + manager.getMonoBankCurrencyBuyRate(EUR));
        System.out.println("MonoBank EUR Sell: " + manager.getMonoBankCurrencySellRate(EUR));

        System.out.println("NBU USD Buy: " + manager.getNbuCurrencyRate(USD));
        System.out.println("NBU EUR Sell: " + manager.getNbuCurrencyRate(EUR));
    }
}
