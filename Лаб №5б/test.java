import java.util.*;

public class test {
    public static void main(String[] args) {
        ArrayList<LinkedList<Integer>> matrix = new ArrayList<LinkedList<Integer>>();
																							         //  1  2    3  4   5   6   7   8  9  10 11 12 13 14 15
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, 5, 4, -1, -1, -1, -1, -1,-1,-1,-1,-1,-1,-1,-1)));//1
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, -1, -1, 3, 2, -1, -1, -1,-1,-1,-1,-1,-1,-1,-1)));//2
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, -1, -1, -1, -1, 4, 5, -1,-1,-1,-1,-1,-1,-1,-1)));//3
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, -1, -1, -1, -1, -1, -1, 3,2,-1,-1,-1,-1,-1,-1)));//4
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, -1, -1, -1, -1, -1, -1, -1,-1,1,2,-1,-1,-1,-1)));//5
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, -1, -1, -1, -1, -1, -1, -1,-1,-1,-1,2,3,-1,-1)));//6
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, -1, -1, -1, -1, -1, -1, -1,-1,-1,-1,-1,-1,4,2)));//7
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, -1, -1, -1, -1, -1, -1, -1,2,-1,-1,-1,-1,-1,-1)));//8
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, -1, -1, -1, -1, -1, -1, -1,-1,4,-1,-1,-1,-1,-1)));//9
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, -1, -1, -1, -1, -1, -1, -1,-1,-1,5,-1,-1,-1,-1)));//10
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, -1, -1, -1, -1, -1, -1, -1,-1,-1,-1,2,-1,-1,-1)));//11
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, -1, -1, -1, -1, -1, -1, -1,-1,-1,-1,-1,3,-1,-1)));//12
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, -1, -1, -1, -1, -1, -1, -1,-1,-1,-1,-1,-1,2,-1)));//13
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, -1, -1, -1, -1, -1, -1, -1,-1,-1,-1,-1,-1,-1,1)));//14
        matrix.add(new LinkedList<Integer>(Arrays.asList(-1, -1, -1, -1, -1, -1, -1, -1,-1,-1,-1,-1,-1,-1,-1)));//15

        System.out.print("Kosarayu algorithm" + "\n");
        graph1 graph = new graph1(15);
        for (int i = 0; i < matrix.size(); i++)
            for (int j = 0; j < matrix.size(); j++) {
                if (matrix.get(i).get(j) != -1) {
                    graph.addEdge(i, j);
                }
            }
        graph.alg_Kos();
    }
}
