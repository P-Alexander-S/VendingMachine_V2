import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Cell {
// У каждой ячейки есть номер, цена и название товара, который там находится.
// Так же есть оставшееся количество товара в ячейке.
// Ячейки создаются в VendingMachine.
// Так как название и цена товаров в ячейке совпадают, они записываются в параметрах конструктора Product.

    String productName;
    String cellNumber;
    int priceOfProductInCell;
    private int quantity;
    ArrayList<Product> products;

    public Cell(String cellNumber, String productName) {
        this.cellNumber = cellNumber;
        this.productName = productName;
        quantity = 5 + (int) (Math.random() * 6);
        products = new ArrayList<>(quantity);
        priceOfProductInCell = 1 + (int) (Math.random() * 100);
        for (int i = 0; i < quantity; i++) {
            products.add(new Product(productName, priceOfProductInCell));
        }
        cellSort(products);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //Сортирует товар в ячейке по сроку годности (от меньшего к большему)
    public void cellSort(ArrayList<Product> arrayList) {
        Collections.sort(arrayList, Comparator.comparing(Product::getDateOfExpiration));
    }
}
