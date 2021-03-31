import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Statistic extends StatisticData {
    //Класс для записи в файл статистики купленного товара в формате "название товара - общая прибыль".

    private Map<String, Integer> statistic = new HashMap<>();


    //В конструкторе инициализируется Map statistic. Первый цикл для общей инициализации (общая прибылью "0"),
    // второй для заполнения инициализации прибыли купленных товаров, если такие имеются.
    public Statistic() {
        ArrayList<History> histories = NewVendingMachine.history;

        for (int i = 0; i < VendingMachine.arrayOfProducts.size(); i++) {
            statistic.put(VendingMachine.arrayOfProducts.get(i), 0);
        }
        for (History history : histories) {
            statistic.put(history.getName(), statistic.get(history.getName()) + history.getPrice());
        }
    }

    //Выводит в файл с текущей датой и временем прибыльность товара.
    @Override
    public void print() {
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_MM_yyyy_hh-mm");
        String formatFileName = simpleDateFormat.format(date1.getTime());

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
                    "stat_price_" + formatFileName + ".log", true));

            //Сортирует Map по значению и записывает в файл.
            statistic.entrySet().stream().sorted
                    (Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .forEach(stringIntegerEntry ->
                            {
                                try {
                                    bufferedWriter.write(stringIntegerEntry.getKey() + " - "
                                            + stringIntegerEntry.getValue());
                                    bufferedWriter.newLine();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                    );
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Ошибка файла");
        }
    }
}