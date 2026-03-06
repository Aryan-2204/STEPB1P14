import java.util.*;

public class UsernameChecker {

    // username -> userId
    private HashMap<String, Integer> users = new HashMap<>();

    // username -> number of attempts
    private HashMap<String, Integer> attempts = new HashMap<>();

    // Constructor with some existing users
    public UsernameChecker() {
        users.put("john_doe", 101);
        users.put("admin", 1);
        users.put("alex99", 202);
    }

    // Check username availability
    public boolean checkAvailability(String username) {

        // track attempts
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);

        return !users.containsKey(username);
    }

    // Suggest alternatives
    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String suggestion = username + i;

            if (!users.containsKey(suggestion)) {
                suggestions.add(suggestion);
            }
        }

        String dotSuggestion = username.replace("_", ".");
        if (!users.containsKey(dotSuggestion)) {
            suggestions.add(dotSuggestion);
        }

        return suggestions;
    }

    // Get most attempted username
    public String getMostAttempted() {

        String maxUser = "";
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : attempts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxUser = entry.getKey();
            }
        }

        return maxUser + " (" + maxCount + " attempts)";
    }

    // Main method for testing
    public static void main(String[] args) {

        UsernameChecker checker = new UsernameChecker();

        System.out.println("john_doe available: " + checker.checkAvailability("john_doe"));
        System.out.println("jane_smith available: " + checker.checkAvailability("jane_smith"));

        System.out.println("Suggestions for john_doe: " + checker.suggestAlternatives("john_doe"));

        // simulate repeated attempts
        checker.checkAvailability("admin");
        checker.checkAvailability("admin");
        checker.checkAvailability("admin");

        System.out.println("Most attempted username: " + checker.getMostAttempted());
    }
}