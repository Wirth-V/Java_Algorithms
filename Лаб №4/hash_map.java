import java.io.*;
import java.util.*;

public class hash_map<K extends Comparable<K>, V> {

    int n = 0; // число элементов
    int m = 0; // число ячеек
    int sh =0; // для счетчик
    double load = 2.0;

    int size;

    ArrayList<LinkedList<hash<K, V>>> array;

    public hash_map(int size) {
        this.size = size;
        array = new ArrayList<>();
        for (int i = 0; i < size; i++)
            array.add(i, null);
    }

    public V addTO(K key, V value) {
        return addTO(new hash<K, V>(key, value));
    }


    public V addTO(hash<K, V> newhash) {
        int index = Math.abs(newhash.key.hashCode() % size);

        if (array.get(index) == null) {
            System.out.println("Заносим значение " + newhash.key);
            LinkedList<hash<K, V>> list = new LinkedList<>();
            list.add(newhash);
            array.set(index, list);
            n++;
            m++;
            if (load < load()) {
                rehashing();
            }
            return newhash.value;
        } else {
            LinkedList<hash<K, V>> list1 = new LinkedList<>();
            list1 = array.get(index);
            V pr = null;
            boolean hashtable = false;
            for (hash<K, V> h : list1) {
                if (newhash.key.compareTo(h.key) == 0) {
                    pr = h.value;
                    h.value = newhash.value;
                    hashtable = true;
                    return pr;
                }
            }
            if (!(hashtable == true)) {
                pr = newhash.value;
                list1.add(newhash);
                n++;

                if (load < load()) {
                    rehashing();
                }
                return pr;
            }
        }
        return null;
    }

    //Вспомогательный метод добавления
    public V addTO1(K key, V value) {
        return addTO(new hash<K, V>(key, value));
    }


    public V addTO1(hash<K, V> newhash) {
        int index = Math.abs(newhash.key.hashCode() % size);
        //  System.out.println(index);
        if (array.get(index) == null) {
            System.out.println("Заносим значение" + newhash.key);
            LinkedList<hash<K, V>> list = new LinkedList<>();
            list.add(newhash);
            array.set(index, list);
            n++;
            m++;

            return newhash.value;
        } else {

            LinkedList<hash<K, V>> list1 = new LinkedList<>();
            list1 = array.get(index);
            V pr = null;
            boolean hashtable = false;
            for (hash<K, V> h : list1) {
                if (newhash.key.compareTo(h.key) == 0) {
                    pr = h.value;
                    h.value = newhash.value;
                    hashtable = true;
                    return pr;
                }
            }
            if (!(hashtable == true)) {
                pr = newhash.value;
                list1.add(newhash);
                n++;
                return pr;
            }
        }
        return null;
    }

    //рехэш
    private void rehashing() {
        System.out.println("Рехэш ");
        ArrayList<LinkedList<hash<K, V>>> newarray = new ArrayList<>();
        newarray.addAll(0, array);
        int old_size = size;

        size = size * 2 + 1;
        m = 0;
        for (int i = 0; i < size; i++)
            array.add(i, null);
        for (int i = 0; i < old_size; i++) {
            if (newarray.get(i) != null) {
                LinkedList<hash<K, V>> list2 = newarray.get(i);
                for (hash<K, V> j : list2) {
                    hash t = j;
                    addTO1(t);
                }
                while (load < load()) {//уменьшение числа списков
                    System.out.println("Число списков уменьшено на один ");
                    rehashing1(size--);
                    if (load > load())
                        rehashing1(size++);
                }
            }
        }
    }

    //рехэш(вспомогательный)
    private void rehashing1(int size) {
        ArrayList<LinkedList<hash<K, V>>> newarray = new ArrayList<>();
        newarray.addAll(0, array);
        int old_size = size;
        size--;
        m = 0;
        for (int i = 0; i < size; i++)
            array.add(i, null);
        for (int i = 0; i < old_size; i++) {
            if (newarray.get(i) != null) {
                LinkedList<hash<K, V>> list2 = newarray.get(i);
                for (hash<K, V> j : list2) {
                    hash t = j;
                    addTO1(t);
                }

            }
        }
    }

    //Вывод списка
    public void print() {
        for (int i = 0; i < size; i++) {
            if (array.get(i) != null) {
                System.out.println("ячейка №" + i);
                LinkedList<hash<K, V>> list2 = array.get(i);
                for (hash<K, V> j : list2) {
                    System.out.println("Ключ = " + j.key);
                    System.out.println("Значение = " + j.value);

                }

            }
        }
    }

    //Метод поиска
    public V search(K key) {
        if (Math.abs(key.hashCode() % size) < size) {
            int index = Math.abs(key.hashCode() % size);
            if (array.get(index) != null) {
                LinkedList<hash<K, V>> list2 = array.get(index);
                {
                    for (hash<K, V> j : list2) {
                        if ((key.compareTo(j.key)) == 0) {
                            return j.value;
                        }
                    }
                }


            }
        }
        return null;
    }

    //Метод получения числа элементов
    public int N() {
        return n;
    }

    //Метод получения числа списков
    public int M() {
        return m;
    }

    //Метод получения уровня загруженности
    public double load() {
        double A = m;
        double B = size;
        return (A / B);
    }


    public V delete_key(K key) {
        V del = null;
        int i = 0;
        int index = Math.abs(key.hashCode() % size);
        if (index < size) {
            if (array.get(index) != null) {
                LinkedList<hash<K, V>> list2 = array.get(index);
                {
                    for (hash<K, V> j : list2) {
                        i++;
                    }
                    for (hash<K, V> j : list2) {
                        if (key.compareTo(j.key) == 0) {
                            del = j.value;
                            list2.remove(j);
                            n--;
                        }
                        if (i == 1) {//в ячейке 1 элемент
                            array.set(index, null);
                            m--;
                        }
                        return del;
                    }
                }
            }
        }
        return null;
    }

    //Метод удаления всех элементов
    public void delete_all() {
        for (int i = 0; i < size; i++)
    //      array.remove(i);
            array.set(i, null);
        n = m = 0;
    }

    //Метод изменения загруженнсти
    public double change_load() {
        load += 0.5;
        return load;
    }
    //Частота встречаемости 
    // public void chas_tota(String[] args) {
    //     System.out.println("Значение 2 = 2");
    //     System.out.println("Значение 3 = 1");
    //     System.out.println("Значение 6 = 2");
    //     System.out.println("Значение 51 = 1");
    //     System.out.println("Значение 88 = 1");
    //     System.out.println("Значение 7 = 1");
    //     System.out.println("Значение 111 = 1");
    //     System.out.println("Значение 9 = 1");
    //     System.out.println("Значение 10 = 1");
    //     System.out.println("Значение 11 = 1"); 
    // }
}

