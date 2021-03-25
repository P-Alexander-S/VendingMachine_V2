import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Statistic {

    private Map<String, Integer> statistic = new HashMap<>();

    public void createStatistic(ArrayList<History> histories) {
        for (int i = 0; i < 9; i++) {
            statistic.put(VendingMachine.arrayOfProducts.get(i), 0);
        }

        for (History history : histories) {
            statistic.put(history.getName(), statistic.get(history.getName()) + history.getPrice());
        }
    }

    public void printSortStatArray() {

        ArrayList<String> strings = new ArrayList<>();
        ArrayList<Integer> integers = new ArrayList<>();
        for (Map.Entry<String, Integer> map : statistic.entrySet()) {
            strings.add(map.getKey());
            integers.add(map.getValue());
        }
        for (int i = 0; i < strings.size(); i++) {
            for (int j = i; j < strings.size(); j++) {
                if (integers.get(i) < integers.get(j)) {
                    int a = integers.get(i);
                    String str = strings.get(i);

                    integers.set(i, integers.get(j));
                    strings.set(i, strings.get(j));

                    integers.set(j, a);
                    strings.set(j, str);
                }
            }
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_MM_yyyy_hh-mm");
        String formatFileName = simpleDateFormat.format(date1.getTime());
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
                    "stat_price_" + formatFileName + ".log", true));

            for (int i = 0; i < strings.size(); i++) {
                String result = strings.get(i) + " - " + integers.get(i);
                bufferedWriter.write(result);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Ошибка файла");
        }
    }
}
