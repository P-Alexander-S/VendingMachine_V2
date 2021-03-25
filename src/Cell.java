import java.util.ArrayList;

public class Cell {

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

    public void cellSort(ArrayList<Product> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            for (int j = i; j < arrayList.size(); j++) {
                if (arrayList.get(i).getDateOfExpiration().compareTo
                        (arrayList.get(j).getDateOfExpiration()) > 0) {
                    Product product = arrayList.get(i);
                    arrayList.set(i, arrayList.get(j));
                    arrayList.set(j, product);
                }
            }
        }
    }
}
