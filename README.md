# VendingMachine
Сделано на версии Java 15
## Задание

Написать приложение с консольным интерфейсом, эмулирующее работу вендиногового торгового автомата. Программа должна эмулировать выдачу товаров и вести историю и статистику покупок.
Описание:
Автомат содержит 9 лотков для однотипных товаров, каждый из которых вмещает до 10 единиц товара.
Лотки нумеруются буквами и цифрами A1 - С3.
Товар имеет следующие характеристики:  
	* - название (9 произвольных названий);  
	* - стоимость (случайно от 1 до 100 руб);  
	* - дату истечения срока годности (случайно от 1 дня до 7 от текущей даты).  
При запуске программа должна случайным образом заполнять лотки случайным количеством каждого товара (от 5 до 10 товаров в лоток) в порядке увеличения срока годности. Выдаваться товары из лотка должны, начиная с ближайшего по сроку годности.

Меню должно содержать следующие пункты:  
	1. Вывести меню аппарата - на экран выводится по строке для каждого из лотков в формате:  
	НАЗВАНИЕ ЯЧЕЙКИ - НАЗВАНИЕ ТОВАРА - ЦЕНА - ОСТАВШЕЕСЯ КОЛИЧЕСТВО, пример  
	A1 - ХотДог - 55 - 4  
	2. Купить товар - требуется ввести номер ячейки, сумму, дату и время покупки, в результате товара в нужном лотке должно стать на 1 меньше.
	Необходима проверка на то, что товар еще не кончился, сумма не меньше его стоимости, а срок годности не истек.  
	3. Записать в текстовый файл с именем history_текущая_дата_время.log файл историю покупок построчно, начиная с последней совершенной покупки в формате  
	ДАТА И ВРЕМЯ ПОКУПКИ - НАЗВАНИЕ ТОВАРА - ЦЕНА  
	4. Записать в текстовый файл с именем stat_price_текущая_дата_время.log файл статистику покупок построчно, сгруппированную по товарам, начиная с наибольшей суммарной прибыли в формате  
	НАЗВАНИЕ ТОВАРА - ОБЩАЯ ПРИБЫЛЬ  
	5. Записать в текстовый файл с именем stat_valid_текущая_дата_время.log файл статистику оставшихся в автомате товаров построчно, начиная с ближайшей даты истечения срока годности  
	НАЗВАНИЕ ТОВАРА - ДАТА ИСТЕЧЕНИЯ СРОКА ГОДНОСТИ, пример   
	ХотДог - 2019-05-14  
	Крекер - 2019-05-15  
	ХотДог -2019-05-15  
  
  ## Описание запуска
  
  Скачать проект архивом, разархивировать в доступную папку. В intellij idea нажать "File" -> "Open" -> выбрать скачанную папку -> "Ok".  
  Далее достаточно запустить метод main и следовать инструкциям, выводимым в консоль.
 
 В начале запуска достаточно ввести цифру:  
 "1" - выводит список всех продуктов в соответствии с заданием.  
 "2" - переходит в меню покупки товара. Название ячейки товара поддерживает только латинские буквы.  
 "3" - создает файл в корне проекта и записывает в него информацию об истории покупок в соответствии с заданием.  
 "4" - создает файл в корне проекта и записывает в него информацию о популярных продуктах в соответствии с заданием.  
 "5" - создает файл в корне проекта и записывает в него информацию об оставшихся в автомате продуктах в соответствии с заданием.  
 "6" - выходит из цикла и завершает работу программы.  
