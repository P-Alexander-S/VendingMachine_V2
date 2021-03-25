import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class History {

    private Calendar calendar;
    private String time;
    private String name;
    private int price;

    public History(Cell cell) {
        this.price = cell.priceOfProductInCell;
        this.name = cell.productName;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static void getHistory(ArrayList<History> histories) {

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
