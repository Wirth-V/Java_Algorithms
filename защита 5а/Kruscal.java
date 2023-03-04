import java.util.Arrays;
import java.util.Comparator;

public class Kruscal {
    private final int numOfPeaks;                    // количество вершин в графе
    private int currentNumberOfPeaks;                // текущее количество вершин
    private final int[] parent;                      // массив родительских элементов
    private final double[][] min;
    private final Edge[] edge;                       // список ребер

    public Kruscal(int v, int e) {
        this.numOfPeaks = v;
        this.currentNumberOfPeaks = 0;
        edge = new Edge[e];
        parent = new int[numOfPeaks];
        min = new double[numOfPeaks][numOfPeaks];
        currentNumberOfPeaks = 0;
    }

    // добавление ребра
    public void addEdge(int start, int end, int weight) {
        edge[currentNumberOfPeaks] = new Edge(start, end, weight);
        currentNumberOfPeaks++;
    }

    // поиск 
    public int find(int i) {
        while (parent[i] != i)
            i = parent[i];
        return i;
    }

    public void connect(int i, int j, int[] size) {
        i = find(i);
        j = find(j);
        if (size[i] > size[j]) {
            parent[j] = i;
            size[i] += size[j];
        } else {
            parent[i] = j;
            size[j] += size[i];
        }
    }

    public void kruskal() {
        Arrays.sort(edge, new SortByWeight());          //сортировка по весу ребра
        int[] size = new int[numOfPeaks];
        double minDistance = 0;
        for (int i = 0; i < numOfPeaks; i++) {
            parent[i] = i;
            size[i] = 0;
        }

        int edge_count = 0;
        int k = 0;
        while (k < numOfPeaks - 1) {
            Edge e = edge[edge_count];
            edge_count++;
            if (find(e.getFirstPeak()) != find(e.getSecondPeak())) {
                connect(find(e.getFirstPeak()), find(e.getSecondPeak()), size);
                min[e.getFirstPeak()][e.getSecondPeak()] = e.weight();
                k++;
            }
        }
        for (int i = 0; i < min.length; i++) {
            for (int j = 0; j < min.length; j++) {
                if (min[i][j] != 0) {
                    System.out.printf("%d - %d веса = %.2f \n", i, j, min[i][j]);
                    minDistance += min[i][j];
                }
            }
        }
        System.out.printf("Минимальное расстояние = %.2f\n", minDistance);
    }
}

// класс для сортировки по весу ребра
 class SortByWeight implements Comparator<Edge> {
    public int compare(Edge a, Edge b) {
        if ( a.weight() < b.weight() )
            return -1;
        else if ( a.weight() == b.weight() )
            return 0;
        else
            return 1;
    }
}


