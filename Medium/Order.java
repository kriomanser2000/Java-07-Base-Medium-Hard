import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order
{
    private List<Item> items;
    public Order(List<Item> items)
    {
        this.items = items;
    }
    public List<Item> getItems()
    {
        return items;
    }
    class Item
    {
        private String productName;
        private Item(String productName)
        {
            this.productName = productName;
        }
        public String getProductName()
        {
            return productName;
        }
    }
    public void main(String[] args)
    {
        Order order1 = new Order(Arrays.asList(new Item("Milk"), new Item("Phone")));
        Order order2 = new Order(Arrays.asList(new Item("Milk"), new Item("TV")));
        Order order3 = new Order(Arrays.asList(new Item("Phone"), new Item("Laptop")));
        List<Order> orders = Arrays.asList(order1, order2, order3);
        long distinctProductCount = orders.stream()
                .flatMap(order -> order.getItems().stream())
                .map(Item::getProductName)
                .distinct()
                .count();
        System.out.println("Distinct product count: " + distinctProductCount);
    }
}
