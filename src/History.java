import java.util.Calendar;

public class History {
    //Данный класс содержит информацию о купленном товаре.

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

}
