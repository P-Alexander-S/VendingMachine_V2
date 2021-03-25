import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class VendingMachine {

    public static ArrayList<String> arrayOfProducts = new ArrayList<>(Arrays.asList("Mango", "Melon",
            "Watermelon", "Limon", "Orange", "Banana", "Peach", "Apple", "Cherry"));
    private ArrayList<String> numberOfCell = new ArrayList<>(Arrays.asList("A1", "A2", "A3", "B1",
            "B2", "B3", "C1", "C2", "C3"));
    public ArrayList<Cell> cells = new ArrayList();


    public VendingMachine(){

    }

    public void machineInitialize() {
        Collections.shuffle(arrayOfProducts);
        for (int i = 0; i < numberOfCell.size(); i++) {
            cells.add(new Cell(numberOfCell.get(i), arrayOfProducts.get(i)));
        }
    }

    public void printInfo() {
        System.out.println("№ | Name | Price | Quantity");
        for (int i = 0; i < cells.size(); i++) {
            System.out.println("----------------------");
            Cell cell = cells.get(i);
            System.out.printf("%s | %s | %d | %d \n", cell.cellNumber, cell.productName, cell.priceOfProductInCell,
                    cell.getQuantity());
        }
    }

    public void buy() {
        while (true) {
            System.out.println("Введите номер ячейки, цену, дату и время приобретения товара. Например:\n"
                    + "B1 99 24-03-2021 09:30\nГде B1 - номер ячейки (латинские буквы), " +
                    "99 - цена, 24-03-2021 - дата, 17:25 - время");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String info = null;
            try {
                info = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                String[] strings = info.split("[ ,]");
                String desiredCellNum = strings[0].toUpperCase(Locale.ROOT);
                int priceOffer = Integer.parseInt(strings[1]);
                String date = strings[2];
                if (!date.matches("(0\\d|[12]\\d|31)-(0[1-9]|1[012])-\\d\\d\\d\\d")) {
                    NewVendingMachine.menu.stringFormat("Введен неправильный формат даты.");
                    break;
                }
                Calendar desiredDate = Calendar.getInstance();
                desiredDate.setTime(NewVendingMachine.simpleDateFormat.parse(strings[2]));
                String time = strings[3];
                if (!time.matches("(([01]\\d)|(2[0-3])):((0\\d)|([0-5]\\d))")) {
                    NewVendingMachine.menu.stringFormat("Введен неправильный формат времени.");
                    break;
                }


                Cell desiredCell = null;

                try {
                    desiredCell = cells.get(numberOfCell.indexOf(desiredCellNum));
                } catch (Exception e) {
                    NewVendingMachine.menu.stringFormat("Неверный формат ячейки.");
                }

                if (desiredCell.getQuantity() == 0) {
                    NewVendingMachine.menu.stringFormat("Товар закончился.");
                    break;
                }
                Product desiredProduct = desiredCell.products.get(0);
                int price = desiredProduct.getPrice();

                if (priceOffer < price) {
                    NewVendingMachine.menu.stringFormat("Вам не хватает денег.");
                    break;
                }
                if (desiredDate.compareTo(desiredProduct.getDateOfExpiration()) > 0) {
                    NewVendingMachine.menu.stringFormat("К этой дате желаемый Вами продукт будет испрочен." +
                            " Просьба воздержаться от покупки!");
                    break;
                } else {
                    History history = new History(desiredCell);
                    history.setTime(time);
                    history.setCalendar(desiredDate);
                    NewVendingMachine.history.add(history);
                    desiredCell.setQuantity(desiredCell.getQuantity() - 1);
                    desiredCell.products.remove(0);
                    NewVendingMachine.menu.stringFormat("Спасибо за покупку!");
                    break;
                }

            } catch (Exception e) {
                NewVendingMachine.menu.stringFormat("Проверьте введенные данные.");
                break;
            }
        }
    }
}
