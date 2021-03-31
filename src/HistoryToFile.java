import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class HistoryToFile extends StatisticData {
    //Класс осуществляет сортировку и вывод в файл истории покупок.

    private ArrayList<History> histories;

    public HistoryToFile() {
        histories = new ArrayList<>(NewVendingMachine.history);
    }

    //Выводит в файл с текущей датой и временем историю покупок, начиная с последней совершенной.
    @Override
    public void print() {
        Collections.reverse(histories);
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_MM_yyyy_hh-mm");
        String formatFileName = simpleDateFormat.format(date1.getTime());
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
                    "history_" + formatFileName + ".log", true));
            for (History history : histories) {
                String date = NewVendingMachine.simpleDateFormat.format(history.getCalendar().getTime());
                String time = history.getTime();
                String name = history.getName();
                int price = history.getPrice();
                String results = date + " " + time + " " + name + " " + price;
                bufferedWriter.write(results);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Ошибка файла");
        }
    }
}
