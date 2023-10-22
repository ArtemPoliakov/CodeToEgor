package app;

import ArtemExtensions.*;
import ArtemExtensions.Currency.CurrencyNames;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.MonoBankCurrency;
import dto.NbuCurrency;
import dto.PrivatBankCurrency;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static ArtemExtensions.Currency.CurrencyNames.*;

public class Client {
    private static Client thisClient;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<MonoBankCurrency> monoBankCurrencies = new ArrayList<>();
    private List<PrivatBankCurrency> privatBankCurrencies = new ArrayList<>();
    private List<NbuCurrency> nbuCurrencies = new ArrayList<>();
    private List<Bank> banks = new ArrayList<>();

    private Client() {
        System.out.println("Hello from Client");

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        executorService.scheduleAtFixedRate(() -> {
            System.out.println("Update Start");

            monoBankCurrencies = getMonoBankCurrenciesList();
            System.out.println(monoBankCurrencies);
            privatBankCurrencies = getPrivatBankCurrenciesList();
            System.out.println(privatBankCurrencies);
            nbuCurrencies = getNbuCurrenciesList();
            System.out.println(nbuCurrencies);

            //added (kostylь)       list updating process is to be improved and further optimised
            banks = createBankList();
            //added (kostylь)
            System.out.println("Update End");

        }, 0, 5, TimeUnit.MINUTES);
    }
    public static Client getClientObject(){
        if(thisClient==null){
            thisClient = new Client();
        }
        return thisClient;
    }
    public List<Bank> getBanks(){
        return banks;
    }

    public List<Bank> createBankList(){
        return List.of(getMonobank(), getPrivatbank(), getNbu());
    }


    public Bank getMonobank(){
        Map<CurrencyNames, Double> buyMap = getMonoBankCurrenciesBuy();
        Map<CurrencyNames, Double> sellMap = getMonoBankCurrenciesSell();
        Currency Usd = new Currency(USD, buyMap.get(USD), sellMap.get(USD));
        Currency Eur = new Currency(EUR, buyMap.get(EUR), sellMap.get(EUR));
        Bank Monobank = new Bank(Bank.BankNames.MONOBANK, List.of(Usd, Eur));
        return Monobank;
    }
    public Bank getPrivatbank(){
        Map<CurrencyNames, Double> buyMap = getPrivatBankCurrenciesBuy();
        Map<CurrencyNames, Double> sellMap = getPrivatBankCurrenciesSell();
        Currency Usd = new Currency(USD, buyMap.get(USD), sellMap.get(USD));
        Currency Eur = new Currency(EUR, buyMap.get(EUR), sellMap.get(EUR));
        Bank Privatbank = new Bank(Bank.BankNames.PRIVATBANK, List.of(Usd, Eur));
        return Privatbank;
    }
    public Bank getNbu(){
        Map<CurrencyNames, Double> buyAndSellMap = getNbuCurrencies();
        Currency Usd = new Currency(USD, buyAndSellMap.get(USD), buyAndSellMap.get(USD));
        Currency Eur = new Currency(EUR, buyAndSellMap.get(EUR), buyAndSellMap.get(EUR));
        Bank Nbu = new Bank(Bank.BankNames.NBU, List.of(Usd, Eur));
        return Nbu;
    }
    public Map<CurrencyNames, Double> getMonoBankCurrenciesBuy() {
        return monoBankCurrencies.stream()
                .filter(currency -> currency.getCurrencyCodeA() == 840 || currency.getCurrencyCodeA() == 978)
                .filter(currency -> currency.getCurrencyCodeB() == 980)
                .collect(Collectors.toMap(currency -> getCurrencyNameByCode(currency.getCurrencyCodeA()), MonoBankCurrency::getRateBuy));
    }

    public Map<CurrencyNames, Double> getMonoBankCurrenciesSell() {
        return monoBankCurrencies.stream()
                .filter(currency -> currency.getCurrencyCodeA() == 840 || currency.getCurrencyCodeA() == 978)
                .filter(currency -> currency.getCurrencyCodeB() == 980)
                .collect(Collectors.toMap(currency -> getCurrencyNameByCode(currency.getCurrencyCodeA()), MonoBankCurrency::getRateSell));
    }

    public Map<CurrencyNames, Double> getPrivatBankCurrenciesBuy() {
        return privatBankCurrencies.stream()
                .collect(Collectors.toMap(currency->
                        getCurrencyNameByCcyForPrivatAndNBU(currency.getCcy()), PrivatBankCurrency::getBuy));
    }

    public Map<CurrencyNames, Double> getPrivatBankCurrenciesSell() {
        return privatBankCurrencies.stream()
                .collect(Collectors.toMap(currency->
                        getCurrencyNameByCcyForPrivatAndNBU(currency.getCcy()), PrivatBankCurrency::getSale));
    }

    public Map<CurrencyNames, Double> getNbuCurrencies() {
        return nbuCurrencies.stream()
                .filter(currency -> currency.getCurrencyCode() == 840 || currency.getCurrencyCode() == 978)
                .collect(Collectors.toMap(currency->
                        getCurrencyNameByCcyForPrivatAndNBU(currency.getCurrencyCodeL()), NbuCurrency::getAmount));
    }


    @SneakyThrows
    private List<MonoBankCurrency> getMonoBankCurrenciesList() {
        var response = getStringHttpResponse(ApplicationConstants.MONO_URL);
        return objectMapper.readValue(response.body(), new TypeReference<List<MonoBankCurrency>>() {});
    }

    @SneakyThrows
    private List<PrivatBankCurrency> getPrivatBankCurrenciesList() {
        var response = getStringHttpResponse(ApplicationConstants.PRIVAT_URL);
        return objectMapper.readValue(response.body(), new TypeReference<List<PrivatBankCurrency>>() {});
    }

    @SneakyThrows
    private List<NbuCurrency> getNbuCurrenciesList() {
        var response = getStringHttpResponse(ApplicationConstants.NBU_URL);
        return objectMapper.readValue(response.body(), new TypeReference<List<NbuCurrency>>() {});
    }


    @SneakyThrows
    private HttpResponse<String> getStringHttpResponse(String url) {
        HttpRequest getRequest = createGetRequest(url);
        return httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
    }

    @SneakyThrows
    private HttpRequest createGetRequest(String url) {
        return HttpRequest.newBuilder(new URI(url))
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .timeout(Duration.of(10, ChronoUnit.SECONDS))
                .GET()
                .build();
    }

    private CurrencyNames getCurrencyNameByCode(int currencyCode) {
        return switch (currencyCode) {
            case 840 -> USD;
            case 978 -> EUR;
            default -> UNDEFINED;
        };
    }
    private CurrencyNames getCurrencyNameByCcyForPrivatAndNBU(String ccy){
        return switch (ccy){
            case "USD" -> USD;
            case "EUR" -> EUR;
            default -> UNDEFINED;
        };
    }
}
