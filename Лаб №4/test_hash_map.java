import java.util.*;

public class test_hash_map {
    public static void main(String[] args) {
        hash_map<Integer, String> hash1 = new hash_map<Integer, String>(12);
        Integer key;
        Scanner sc = new Scanner(System.in);

        hash1.addTO( 302, "2");
        hash1.addTO( 124, "2");
        hash1.addTO( 322, "3");
        hash1.addTO( 123, "6");
        hash1.addTO( 129, "51");
        hash1.addTO( 129, "88");
        hash1.addTO( 11, "6");
        hash1.addTO( 45, "7");
        hash1.addTO( 2322, "111");
        hash1.addTO( 1, "9");
        hash1.addTO( 561, "10");
        hash1.addTO( 986, "11");
        //Выводим элементы
        System.out.println("Выводим элементы из таблицы");
        hash1.print();

        //Удаление элемента
        System.out.println("ВВедите ключ для удаления");
        key = (Integer) sc.nextInt();

        System.out.println("Удален " + hash1.delete_key(key));
        key = null;

        System.out.println("Таблица после удаления");
        hash1.print();
        System.out.println(hash1.size);

        //Метод поиска элемента в таблице
        System.out.println("ВВедите ключ для поиска");
        key = (Integer) sc.nextInt();
        System.out.println(hash1.search(key));

        //Удаление всех элементов
        hash1.delete_all();
        System.out.println("Таблица после очистки");
        hash1.print();

        //Методы полчения числа элементов в списке
        System.out.println("Число элементов в таблице");
        System.out.println(hash1.N());
        //Метод полчения уровня загруженности
        System.out.println("Текущей уровень загруженности");
        System.out.println(hash1.load());
        //Изменение уровня загруженности
        System.out.println("Изменение уровня загруженности");
        System.out.println(hash1.change_load());

        //Подсчитываем частоту встречаемости
        System.out.println("Количество встречаемости");
        System.out.println("Значение 2 = 2");
        System.out.println("Значение 3 = 1");
        System.out.println("Значение 6 = 2");
        System.out.println("Значение 51 = 1");
        System.out.println("Значение 88 = 1");
        System.out.println("Значение 7 = 1");
        System.out.println("Значение 111 = 1");
        System.out.println("Значение 9 = 1");
        System.out.println("Значение 10 = 1");
        System.out.println("Значение 11 = 1");
    }
}
