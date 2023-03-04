import java.util.*;

public class graph {
    ArrayList<LinkedList<Integer>> matrix;
    int size;
    boolean[] visited;
    int inf = Integer.MAX_VALUE / 2;

    public graph(ArrayList<LinkedList<Integer>> matrix) {
        this.matrix = matrix;
        this.size = matrix.size();
    }

    //Метод углубленного поиска
    public void DFS1() {
        visited = new boolean[size];
        DFS(0);
    }

    public void DFS(int v) {
        visited[v] = true;
        System.out.println("Посетил  V" + (v + 1));
        for (int u = 0; u < size; u++) {
            if (matrix.get(u).get(v) == 1) {
                if (!visited[u]) {
                    DFS(u);
                }
            }
        }
    }

    public int size() {
        return size = matrix.size();
    }

    //Метод поиска по ширине
    public void BFS(int s) {
        Queue<Integer> q = new LinkedList<Integer>();
        visited = new boolean[size];

        q.add(s);
        visited[s] = true;
        System.out.println("Посетил V" + (s + 1));
        while (!q.isEmpty()) {
            int v = q.remove();
            for (int u = 0; u < size; u++) {
                if (matrix.get(v).get(u) == 1) {
                    if (!visited[u]) {
                        System.out.println("Посетил V" + (u + 1));
                        visited[u] = true;
                        q.add(u);
                    }
                }
            }
        }
    }

    //Алгоритм Дейкстры
    public void alg_D(int s) {

        int[] D = new int[size];
        visited = new boolean[size];

        for (int u = 0; u < size; u++) {
            D[u] = inf;
            visited[u] = false;
        }

        D[s] = 0;

        for (; ; ) {
            int v = -1;
            for (int n = 0; n < size; n++)
                if (!visited[n] && D[n] < inf && (v == -1 || D[v] > D[n]))
                    v = n;
            if (v == -1)
                break;
            visited[v] = true;
            for (int n = 0; n < size; n++)
                if (!visited[n] && matrix.get(v).get(n) < inf && matrix.get(v).get(n) != -1) {
                    D[n] = min(D[n], D[v] + matrix.get(v).get(n));
                }

        }
        for (int u = 0; u < size; u++)
            System.out.print(D[u] + " ");
    }

    public int min(int a, int b) {
        if (a > b) {
            return b;
        } else {
            return a;
        }
    }

    //Алгоритм Крускала
    public int[] alg_K(ArrayList<Edge> edges) {
        Collections.sort(edges, new weightComparator());
        CNM(size); //система непересекающихся множеств
        int[] D = new int[size];
        int ret = 0;
        int j = 0;
        for (Edge e : edges)
            if (union(e.u, e.v)) {
                ret += e.weight;
                D[j] = ret;
                ret = 0;
                j++;
                if (j == size)
                    break;
            }

        return D;
    }

    int[] set;
    int[] rank;

    public void CNM(int size) {
        set = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++)
            set[i] = i;
    }

    public int set(int x) {
        return x == set[x] ? x : (set[x] = set(set[x]));
    }

    boolean union(int u, int v) {
        if ((u = set(u)) == (v = set(v)))
            return false;
        if (rank[u] < rank[v]) {
            set[u] = v;
        } else {
            set[v] = u;
            if (rank[u] == rank[v])
                rank[u]++;
        }
        return true;
    }

    public class weightComparator implements Comparator<Edge> {
        public int compare(Edge e1, Edge e2) {
            return e1.weight - e2.weight;
        }
    }

    //Алгоритм Прима
    public ArrayList<Parent> alg_Prim() {
        visited = new boolean[size];
        int[] D = new int[size];
        ArrayList<Parent> parent = new ArrayList<>();
        int[] PARENT = new int[size];

        for (int u = 0; u < size; u++) {
            D[u] = inf;
            PARENT[u] = 0;
        }
        D[0] = 0;
        for (; ; ) {
            int v = -1;
            for (int nv = 0; nv < size; nv++)
                if (!visited[nv] && D[nv] < inf && (v == -1 || D[v] > D[nv]))
                    v = nv;
            if (v == -1) break;
            visited[v] = true;
            for (int nv = 0; nv < size; nv++)
                if (!visited[nv] && matrix.get(v).get(nv) < inf && matrix.get(v).get(nv) != -1) {
                    PARENT[nv] = nv;
                    D[nv] = min(D[nv], matrix.get(v).get(nv));
                }
        }


        for (int v = 0; v < size; v++) {
            parent.add(new Parent(PARENT[v], D[v]));
        }
        return parent;
    }

    //Алгоритм Флойда-Уоршалла
    public int[][] alg_f_W() {
        int[][] D = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int m = 0; m < size; m++) {
                if (matrix.get(i).get(m) != -1)
                    D[i][m] = matrix.get(i).get(m);
                else
                    D[i][m] = inf;
            }
        for (int k = 0; k < size; k++)
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    D[i][j] = min(D[i][j], D[i][k] + D[k][j]);
        return D;
    }
}
