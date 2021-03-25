import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ValidProducts {

    private ArrayList<Product> validProducts = new ArrayList<>();

    public ArrayList<Product> getSortedValidProducts() {
        for (int i = 0; i < validProducts.size(); i++) {
            for (int j = i; j < validProducts.size(); j++) {
                if (validProducts.get(i).getDateOfExpiration().compareTo
                        (validProducts.get(j).getDateOfExpiration()) > 0) {
                    Product product = validProducts.get(i);
                    validProducts.set(i, validProducts.get(j));
                    validProducts.set(j, product);
                }
            }
        }
        return validProducts;
    }

    public void getValidProducts(VendingMachine vendingMachine) {

        for (int i = 0; i < vendingMachine.cells.size(); i++) {
            Cell cell = vendingMachine.cells.get(i);
            int left = cell.getQuantity();
            if (left > 0) {
                for (int j = 0; j < left; j++) {
                    validProducts.add(cell.products.get(j));
                }
            }
        }
    }

    public void writeProductsLeft() {
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_MM_yyyy_hh-mm");
        String formatFileName = simpleDateFormat.format(date1.getTime());
        ArrayList<Product> vp = getSortedValidProducts();

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
                    "stat_valid_" + formatFileName + ".log", true));
            for (Product product : vp) {
                String name = product.getName();
                String date = NewVendingMachine.simpleDateFormat.format(product.getDateOfExpiration().getTime());
                String results = name + " " + date;
                bufferedWriter.write(results);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Ошибка файла");
        }
    }
}
