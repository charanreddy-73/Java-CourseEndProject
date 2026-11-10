
import java.util.*;
import java.io.*;
import java.util.stream.*;
class OnlineShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    private final List<String> cart = new ArrayList<>();

    public void addProduct(String product) {
        cart.add(product);
    }

    public List<String> getCart() {
        return Collections.unmodifiableList(cart);
    }
    public void saveCart(String filename) {

        try (ObjectOutputStream out =
                 new ObjectOutputStream(new FileOutputStream(filename))) {

            out.writeObject(this);
            System.out.println("Cart saved successfully: " + filename);

        } catch (IOException e) {
            System.out.println("Error saving cart: " + e.getMessage());
        }
    }
    public static OnlineShoppingCart restoreCart(String filename) {

        try (ObjectInputStream in =
                 new ObjectInputStream(new FileInputStream(filename))) {

            return (OnlineShoppingCart) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error restoring cart: " + e.getMessage());
            return new OnlineShoppingCart();
        }
    }
}

class OrderProcessingSystem {

    public static final Map<String, Double> priceMap = Map.of(
        "Laptop", 50000.0,
        "Headset", 25000.0,
        "Iphone", 94000.0
    );

    public static double calculateTotal(List<String> cart) {

        return cart.stream()
                .mapToDouble(priceMap::get)
                .sum();
    }
}
@FunctionalInterface
interface ProductFilter {

    boolean genFilter(String item, double value);
}
public class ShoppingSystem {

    public static void processCustomer(
            int customerId, Scanner sc) {

        OnlineShoppingCart cart = new OnlineShoppingCart();

        System.out.println("\nCustomer " + customerId);

        System.out.print("Enter number of products: ");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {

            System.out.println(
                "Enter product " + i +
                " (Laptop, Iphone, Headset):"
            );

            String item = sc.next();

            if (OrderProcessingSystem.priceMap.containsKey(item)) {

                cart.addProduct(item);

            } else {

                System.out.println("Invalid product. Try again.");
                i--;
            }
        }
        double total = OrderProcessingSystem.calculateTotal(
            cart.getCart()
        );

        double average = cart.getCart().isEmpty()
                ? 0
                : total / cart.getCart().size();

        System.out.println("\nCustomer " + customerId + " Cart:");
        System.out.println(cart.getCart());

        System.out.println("Total price: Rs. " + total);
        System.out.println("Average price: Rs. " + average);

        // Lambda expressions
        ProductFilter onPrice =
            (item, avg) ->
                OrderProcessingSystem.priceMap.get(item) > avg;

        ProductFilter onCategory =
            (item, value) ->
                item.equals("Laptop") || item.equals("Iphone");

        // Filtering using streams
        System.out.println("\nProducts above average price:");

        cart.getCart().stream()
            .filter(item -> onPrice.genFilter(item, average))
            .forEach(System.out::println);

        System.out.println("\nDisplay devices:");

        cart.getCart().stream()
            .filter(item -> onCategory.genFilter(item, 0))
            .forEach(System.out::println);


        String filename = "customer" + customerId + ".ser";

        cart.saveCart(filename);
        OnlineShoppingCart restoredCart =
            OnlineShoppingCart.restoreCart(filename);

        System.out.println("\nRestored Customer " + customerId + " Cart:");

        System.out.println(restoredCart.getCart());
    }


    public static void main(String[] args)
            throws InterruptedException {

        Scanner sc = new Scanner(System.in);

        Object inputLock = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (inputLock) {
                processCustomer(1, sc);
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (inputLock) {
                processCustomer(2, sc);
            }
        });

        Thread t3 = new Thread(() -> {
            synchronized (inputLock) {
                processCustomer(3, sc);
            }
        });

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        sc.close();

        System.out.println("\nAll customer orders processed.");
    }
}
