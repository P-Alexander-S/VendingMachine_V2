import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ValidProducts extends StatisticData {
    //Класс для записи в файл оставшихся товаров.

    private ArrayList<Product> validProducts = new ArrayList<>();

    public ValidProducts(VendingMachine vendingMachine) {
        for (Cell cell : vendingMachine.cells) {
            if (cell.getQuantity() > 0) {
                for (Product product : cell.products)
                    validProducts.add(product);
            }
        }
        Collections.sort(validProducts, Comparator.comparing(Product::getDateOfExpiration));
    }

    //Выводит в файл с текущей датой и временем оставшиеся в автомате товары.
    @Override
    public void print() {
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_MM_yyyy_hh-mm");
        String formatFileName = simpleDateFormat.format(date1.getTime());

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
                    "stat_valid_" + formatFileName + ".log", true));
            for (Product product : validProducts) {
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