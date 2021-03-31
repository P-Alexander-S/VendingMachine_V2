import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class VendingMachine {
    //Содержит список названий товаров и ячеек, а так же массив проинициализированных ячеек.

    public static ArrayList<String> arrayOfProducts = new ArrayList<>(Arrays.asList("Mango", "Melon",
            "Watermelon", "Lemon", "Orange", "Banana", "Peach", "Apple", "Cherry"));
    private ArrayList<String> numberOfCell = new ArrayList<>(Arrays.asList("A1", "A2", "A3", "B1",
            "B2", "B3", "C1", "C2", "C3"));
    public ArrayList<Cell> cells = new ArrayList();


    public VendingMachine() {

    }

    //Метод случайным образом определяет какой тип товара будет в ячейке, а также инициализирует её (заполняет торговый
    // автомат).
    public void machineInitialize() {
        Collections.shuffle(arrayOfProducts);
        for (int i = 0; i < numberOfCell.size(); i++) {
            cells.add(new Cell(numberOfCell.get(i), arrayOfProducts.get(i)));
        }
    }

    //Метод выводит в консоль информацию о всех товарах
    public void printInfo() {
        System.out.println("№ | Name | Price | Quantity");
        for (Cell cell : cells) {
            System.out.println("----------------------");
            System.out.printf("%s | %s | %d | %d \n", cell.cellNumber, cell.productName, cell.priceOfProductInCell,
                    cell.getQuantity());
        }
    }

    //Метод считыет с консоли строку, проверяет на соответствие указанному шаблону, затем проверяет количество товара,
    // количество денег пользователя и не будет ли испорчен товар. Если все в порядке - записывает данные о купленном
    // товаре в историю (а эту историю записывает в массив иторий), удаляет этот товар из ячейки и уменьшает
    // кол-во товара там на 1.
    public void buy() {
        while (true) {
            NewVendingMachine.menu.stringFormat("Введите номер ячейки, цену, дату и время приобретения товара." +
                    " Например:\n"
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
                if (!date.matches(Constants.DATE_REGEX)) {
                    NewVendingMachine.menu.stringFormat("Введен неправильный формат даты.");
                    break;
                }
                Calendar desiredDate = Calendar.getInstance();
                desiredDate.setTime(NewVendingMachine.simpleDateFormat.parse(strings[2]));
                String time = strings[3];
                if (!time.matches(Constants.TIME_REGEX)) {
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
                    NewVendingMachine.menu.stringFormat("Спасибо за покупку! Ваша сдача - "
                            + (priceOffer - price) + " руб.");
                    break;
                }

            } catch (Exception e) {
                NewVendingMachine.menu.stringFormat("Проверьте введенные данные.");
                break;
            }
        }
    }
}
