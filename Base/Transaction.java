import java.util.ArrayList;
import java.util.List;

public class Transaction
{
    private double amount;
    private String type;
    private int id;
    public Transaction(double amount, String type, int id)
    {
        this.amount = amount;
        this.type = type;
        this.id = id;
    }
    public double getAmount()
    {
        return amount;
    }
    public String getType()
    {
        return type;
    }
    public static void main(String[] args)
    {
        List<Transaction> transactions = List.of(
                new Transaction(100.0, "credit", 1),
                new Transaction(200.0, "debit", 2),
                new Transaction(150.0, "credit", 3),
                new Transaction(50.0, "debit", 4),
                new Transaction(75.0, "credit", 5)
        );
        double totalCredit = transactions.stream()
                .filter(transaction -> "credit".equals(transaction.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();
        System.out.println("Total credit: " + totalCredit);
    }
}
