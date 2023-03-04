import java.util.*;

public class Graph2 {
    ArrayList<LinkedList<Integer>> matrix;
    int size;
    boolean[] visited;
    int inf = Integer.MAX_VALUE / 2;
    Queue<Integer> q;
    ArrayList<Integer>[] list;
    int V;

    public Graph2(ArrayList<LinkedList<Integer>> matrix) {
        this.matrix = matrix;
        this.size = matrix.size();
        System.out.println("size = " + (size = matrix.size()));

    }

    public Graph2(int v) {
        V = v;
        list = new ArrayList[v];
        for (int i = 0; i < v; i++)
            list[i] = new ArrayList<>();
    }


    public void alg_Tar(int v) {
        q = new LinkedList<Integer>();
        visited = new boolean[size];
        DFS(v);
        visited[v] = true;
        q.add(v);
        while (!q.isEmpty()) {
            v = q.remove();
            for (int u = 0; u < size; u++) {
                if (matrix.get(v).get(u) != -1) {
                    if (!visited[u]) {
                        DFS(u);
                        q.add(u);
                    }
                }
            }
        }
    }

    public void DFS(int v) {
        visited[v] = true;
        System.out.print((v + 1) + " ");
        for (int u = 0; u < size; u++) {
            if (matrix.get(u).get(v) != -1) {
                if (!visited[u]) {
                    DFS(u);
                }
            }
        }
    }

    //Алгоритм Флери
    public void alg_F() {
        Integer u = 0;
        for (int i = 0; i < size; i++) {
            if (list[i].size() % 2 == 1) {
                u = i;
                break;
            }
        }
        EulerUtil(u);
        System.out.println();
    }

    public void EulerUtil(Integer u) {
        for (int i = 0; i < list[u].size(); i++) {
            Integer v = list[u].get(i);
            if (valid_next_edge(u, v)) {
                System.out.print(u + "-" + v + " ");
                removeEdge(u, v);
                EulerUtil(v);
            }
        }
    }


    public boolean valid_next_edge(Integer u, Integer v) {
        if (list[u].size() == 1) {
            return true;
        }
        visited = new boolean[V];
        int count1 = DFS_Count(u, visited);
        removeEdge(u, v);
        visited = new boolean[V];
        int count2 = DFS_Count(u, visited);
        addEdge(u, v);
        return count1 <= count2;
    }

    public int DFS_Count(Integer v, boolean[] visited) {
        visited[v] = true;
        int count = 1;
        for (int list : list[v]) {
            if (!visited[list]) {
                count = count + DFS_Count(list, visited);
            }
        }
        return count;
    }

    public void addEdge(Integer u, Integer v) {
        list[u].add(v);
        list[v].add(u);
    }

    public void removeEdge(Integer u, Integer v) {
        list[u].remove(v);
        list[v].remove(u);
    }

    public void DFS1(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + 1 + " ");
        int n;

        for (Integer integer : list[v]) {
            n = integer;
            if (!visited[n])
                DFS1(n, visited);
        }
    }

    public Graph2 transpose() {
        Graph2 g = new Graph2(V);
        for (int v = 0; v < V; v++) {
            for (Integer integer : list[v]) g.list[integer].add(v);
        }
        return g;
    }

    public void fill(int v, boolean[] visited, Stack stack) {
        visited[v] = true;
        for (int n : list[v]) {
            if (!visited[n])
                fill(n, visited, stack);
        }
        stack.push(new Integer(v));
    }

    //Алгоритм Косараю
    public void alg_Kos() {
        Stack stack = new Stack();
        visited = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        for (int j = 0; j < V; j++)
            if (!visited[j])
                fill(j, visited, stack);

        Graph2 g = transpose();
        for (int k = 0; k < V; k++)
            visited[k] = false;
        while (!stack.empty()) {
            int v = (int) stack.pop();

            if (!visited[v]) {
                g.DFS1(v, visited);
                System.out.println();
            }
        }
    }
}
