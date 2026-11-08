import java.util.ArrayList;
import java.util.List;

interface Watcher {
    void update(String status);
}

class CustomerPhone implements Watcher {
    private String customerName;

    public CustomerPhone(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public void update(String status) {
        System.out.println(" SMS to " + customerName
                + ": your order is now " + status);
    }
}

class KitchenScreen implements Watcher {
    @Override
    public void update(String status) {
        System.out.println(" Kitchen screen: " + status);
    }
}

class Order {
    private String status;
    private List<Watcher> watchers = new ArrayList<>();

    public void addWatcher(Watcher watcher) {
        watchers.add(watcher);
    }

    public void removeWatcher(Watcher watcher) {
        watchers.remove(watcher);
    }

    public void setStatus(String status) {
        this.status = status;

        System.out.println("Order is now: " + this.status);

        for (Watcher watcher : watchers) {
            watcher.update(this.status);
        }

        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        Order order = new Order();

        CustomerPhone phone = new CustomerPhone("Rahim");
        KitchenScreen kitchen = new KitchenScreen();

        order.addWatcher(phone);
        order.addWatcher(kitchen);

        order.setStatus("Cooking");
        order.setStatus("Out for delivery");

        order.removeWatcher(kitchen);

        order.setStatus("Delivered");
    }
}
