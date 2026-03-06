import java.util.*;

public class FlashSaleInventory {

    HashMap<String, Integer> stock = new HashMap<>();
    HashMap<String, Queue<Integer>> waitingList = new HashMap<>();

    public void addProduct(String productId, int quantity) {
        stock.put(productId, quantity);
        waitingList.put(productId, new LinkedList<>());
    }

    public synchronized String purchaseItem(String productId, int userId) {

        int available = stock.getOrDefault(productId, 0);

        if (available > 0) {
            stock.put(productId, available - 1);
            return "Success, remaining: " + (available - 1);
        }

        waitingList.get(productId).add(userId);
        return "Added to waiting list, position #" + waitingList.get(productId).size();
    }

    public int checkStock(String productId) {
        return stock.getOrDefault(productId, 0);
    }
}