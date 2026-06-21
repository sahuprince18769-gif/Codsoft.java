 import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {

    // Simulating an API database of real-time exchange rates relative to USD
    private static final Map<String, Double> exchangeRates = new HashMap<>();

    static {
        // Base reference: 1 USD
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.92);  // 1 USD = 0.92 EUR
        exchangeRates.put("GBP", 0.79);  // 1 USD = 0.79 GBP
        exchangeRates.put("INR", 83.50); // 1 USD = 83.50 INR
        exchangeRates.put("JPY", 156.0); // 1 USD = 156.0 JPY
        exchangeRates.put("CAD", 1.37);  // 1 USD = 1.37 CAD
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("====== CURRENCY CONVERTER ======");
        System.out.println("Available Currencies: " + exchangeRates.keySet());
        System.out.println("--------------------------------");

        // 1. Currency Selection
        String baseCurrency = getValidCurrency(scanner, "Enter the BASE currency (e.g., USD): ");
        String targetCurrency = getValidCurrency(scanner, "Enter the TARGET currency (e.g., EUR): ");

        // 2. Amount Input
        double amount = getValidAmount(scanner);

        // 3. Currency Conversion & Fetching Rates
        double convertedAmount = convertCurrency(baseCurrency, targetCurrency, amount);

        // 4. Display Result
        System.out.println("\n--------------------------------");
        System.out.printf("Successfully Converted!\n");
        System.out.printf("%.2f %s = %.2f %s\n", amount, baseCurrency, convertedAmount, targetCurrency);
        System.out.println("================================");

        scanner.close();
    }

    /**
     * Helper method to ensure the user inputs a supported currency code.
     */
    private static String getValidCurrency(Scanner scanner, String prompt) {
        String currency;
        while (true) {
            System.out.print(prompt);
            currency = scanner.next().toUpperCase();
            if (exchangeRates.containsKey(currency)) {
                return currency;
            }
            System.out.println("Error: Unsupported currency. Please try again.");
        }
    }

    /**
     * Helper method to ensure the user enters a positive, valid number.
     */
    private static double getValidAmount(Scanner scanner) {
        double amount;
        while (true) {
            System.out.print("Enter the amount to convert: ");
            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
                if (amount >= 0) {
                    return amount;
                }
            } else {
                scanner.next(); // Clear invalid token
            }
            System.out.println("Error: Please enter a valid positive numeric amount.");
        }
    }

    /**
     * Handles the conversion logic using the fetched rates.
     */
    private static double convertCurrency(String from, String to, double amount) {
        // Fetch rates relative to the USD base
        double rateInUSD = exchangeRates.get(from);
        double targetRateInUSD = exchangeRates.get(to);

        // Convert "From Currency" to USD, then USD to "Target Currency"
        return (amount / rateInUSD) * targetRateInUSD;
    }
                                          }
