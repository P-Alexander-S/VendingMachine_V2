import java.util.*;

public class Product {
// У каждого товара есть название, цена и срок годности.
// При создании товара случайно инициализируется только его срок годности.
// Товары создаются в классе Cell.


    private String name;
    private int price;
    private Calendar dateOfExpiration;

    public Product(String name, int price) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, 1 + (int) (Math.random() * 7));

        this.name = name;
        this.price = price;
        this.dateOfExpiration = calendar;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Calendar getDateOfExpiration() {
        return dateOfExpiration;
    }
}
