import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class CurrencyUtils {
    private static final Map<Currency, Double> exchangeRates = new HashMap<>();

    static {
        // Initialize exchange rates
        exchangeRates.put(Currency.USD, Double.ONE);
        exchangeRates.put(Currency.EUR, 0.85);
        exchangeRates.put(Currency.GBP, 0.72);
        exchangeRates.put(Currency.JPY, 110.0);
        // Add more exchange rates as needed
    }

    public static Double convert(Double amount, Currency sourceCurrency, Currency targetCurrency) {
        Double sourceRate = exchangeRates.get(sourceCurrency);
        Double targetRate = exchangeRates.get(targetCurrency);
        return amount*sourceRate/(targetRate);
    }
}
