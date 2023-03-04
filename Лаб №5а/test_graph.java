import java.util.*;

public class test_graph {
    public static void main(String[] args) {
        ArrayList<LinkedList<Integer>> matrix = new ArrayList<LinkedList<Integer>>();
        ArrayList<LinkedList<Integer>> Matrix2 = new ArrayList<LinkedList<Integer>>();

        Matrix2.add(new LinkedList<Integer>(Arrays.asList(0, 1, 1, 1, 0, 0, 0, 0)));
        Matrix2.add(new LinkedList<Integer>(Arrays.asList(1, 0, 0, 0, 1, 1, 0, 0)));
        Matrix2.add(new LinkedList<Integer>(Arrays.asList(1, 0, 0, 1, 0, 0, 0, 0)));
        Matrix2.add(new LinkedList<Integer>(Arrays.asList(1, 0, 0, 0, 1, 0, 1, 0)));
        Matrix2.add(new LinkedList<Integer>(Arrays.asList(0, 1, 1, 0, 0, 0, 0, 1)));
        Matrix2.add(new LinkedList<Integer>(Arrays.asList(0, 1, 0, 0, 0, 0, 0, 1)));
        Matrix2.add(new LinkedList<Integer>(Arrays.asList(0, 0, 0, 1, 0, 0, 0, 1)));
        Matrix2.add(new LinkedList<Integer>(Arrays.asList(0, 0, 0, 0, 1, 1, 1, 0)));
        //																									1  2  3  4   5   6   7   8
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, 4, 7, 3, -1, -1, -1, -1)));// 1
        matrix.add(new LinkedList<Integer>(Arrays.asList(3, -1, -1, -1, 1, 2, -1, -1)));//2
        matrix.add(new LinkedList<Integer>(Arrays.asList(1, -1, -1, 8, -1, -1, -1, -1)));//3
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, -1, -1, -1, -1, 2, -1, -1)));//4
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, 5, 6, 2, -1, -1, -1, 9)));//5
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, 1, -1, -1, -1, -1, -1, 3)));//6
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, -1, -1, 4, -1, -1, -1, 5)));//7
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, -1, -1, -1, 6, 4, 7, -1)));//8
        graph a = new graph(Matrix2);
        graph b = new graph(matrix);

        System.out.println("Метод углубленного поиска ");
        a.DFS1();

        System.out.println("Метод поиска по ширине ");
        a.BFS(0);

        //System.out.println("Алгоритм Дейкстры ");
        //b.alg_D(0);
        //System.out.println(" ");
        System.out.println("Алгоритм Крускала ");
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++)
            for (int j = 0; j < matrix.size(); j++) {
                if (matrix.get(i).get(j) != -1) {
                    edges.add(new Edge(i, j, matrix.get(i).get(j)));
                }
            }

        int[] K = b.alg_K(edges);
        for (int i = 0; i < K.length - 1; i++)
            System.out.print(K[i] + " ");

        System.out.println(" ");
        System.out.println("Алгоритм Прима");
        ArrayList<Parent> parent1;
        parent1 = b.alg_Prim();
        for (int i = 0; i < parent1.size(); i++) {
            Parent d = parent1.get(i);
            System.out.println("parent = " + d.parent);
            System.out.println("weight = " + d.weight);
        }

        System.out.println("Алгоритм Флойда-Уоршалла");
        int[][] W = b.alg_f_W();
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size(); j++) {
                System.out.print(W[i][j] + "\t");
            }
            System.out.println();
        }

    }
}
