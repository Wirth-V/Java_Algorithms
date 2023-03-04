import java.util.*;

public class Test_Graph2 {
    public static void main(String[] args) {
        ArrayList<LinkedList<Integer>> matrix = new ArrayList<LinkedList<Integer>>();
        //Добавление элементов
        //																									1  2  3  4   5   6   7   8
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, 3, 6, 2, -1, -1, -1, -1)));// 1
        matrix.add(new LinkedList<Integer>(Arrays.asList(3, -1, -1, -1, 1, 2, -1, -1)));//2
        matrix.add(new LinkedList<Integer>(Arrays.asList(4, -1, -1, -1, 5, -1, -1, -1)));//3
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, -3, -1, -1, -1, 2, -1, -1)));//4
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, 4, 5, 1, -1, -1, -1, 8)));//5
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, 1, -1, -1, -1, -1, -1, 2)));//6
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, -1, -1, 3, -1, -1, -1, 4)));//7
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, -1, -1, -1, 5, 3, 6, -1)));//8

        Graph2 b = new Graph2(matrix);
        System.out.println("Алгоритм Тарьяна");
        b.alg_Tar(0);

        System.out.println("");
        System.out.print("Алгоритм Флери" + "\n");
        Graph2 c = new Graph2(matrix.size());
        for (int i = 0; i < matrix.size(); i++)
            for (int j = 0; j < matrix.size(); j++) {
                if (matrix.get(i).get(j) != -1) {
                    c.addEdge(i, j);
                }
            }
        c.alg_F();

        System.out.print("Алгоритм Косараджу" + "\n");
        Graph2 d = new Graph2(8);
        for (int i = 0; i < matrix.size(); i++)
            for (int j = 0; j < matrix.size(); j++) {
                if (matrix.get(i).get(j) != -1) {
                    d.addEdge(i, j);
                }
            }
        d.alg_Kos();

    }
}
