import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NewVendingMachine {

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public static ArrayList<History> history;
    public static MenuFormat menu = new MenuFormat();


    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.machineInitialize();
        history = new ArrayList<>();
        menu.stringFormat("Введите соответствующую цифру, чтобы выполнить операцию");
        StatisticData statisticData;

        while (true) {
            menu.stringFormat(
                    """
                            1. Показать ассортимент
                            2. Купить товар
                            3. Записать историю покупок
                            4. Записать статистику покупок
                            5. Записать информацию об оставшихся продуктах
                            6. Выход""");
            int choice = 0;
            try {
                choice = Integer.parseInt(reader.readLine());
            } catch (Exception e) {
                menu.stringFormat("Введите корректный номер желаемой операции.");
            }

            switch (choice) {
                case 1:
                    vendingMachine.printInfo();
                    System.out.println("\n");
                    break;
                case 2:
                    while (true) {
                        vendingMachine.buy();
                        menu.stringFormat("Не желаете купить что-либо еще?\n1. Да\n2. Нет");
                        int answer = 0;
                        try {
                            answer = Integer.parseInt(reader.readLine());
                        } catch (Exception e) {
                            menu.stringFormat("Были варианты 1 и 2, но вы ввели даже не цифру." +
                                    "Ничего не поделать, выходим в главное меню.");
                            break;
                        }
                        if (answer != 1) {
                            if (answer == 2)
                                menu.stringFormat("Выходим в меню.");
                            else menu.stringFormat("Были варианты 1 и 2. А Вы ввели " + answer +
                                    "! Ничего не поделать, выходим в главное меню.");
                            break;
                        }
                    }
                    break;

                case 3:
                    statisticData = new HistoryToFile();
                    statisticData.print();
                    menu.stringFormat("История покупок успешно создана.");
                    break;

                case 4:
                    statisticData = new Statistic();
                    statisticData.print();
                    menu.stringFormat("Статистика покупок успешно создана.");
                    break;

                case 5:
                    statisticData = new ValidProducts(vendingMachine);
                    statisticData.print();
                    menu.stringFormat("Статистика оставшихся товаров успешно создана.");
                    break;

                case 6:
                    menu.stringFormat("Приходите еще!");
                    return;

                default:
                    menu.stringFormat("Выберите существующий пункт меню.");
                    break;
            }
        }
    }
}
