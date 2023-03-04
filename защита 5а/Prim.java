import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class Prim {
    private final int NumOfPeaks;               // количество вершин графа
    private int NumOfEdges;                     // количество ребер
    private final ArrayList<Edge>[] list;       // список смежности
    private Edge[] edgeTo;                      // кратчайшие ребра из вершины
    private double[] distanceTo;                // вес ребер
    private boolean[] marked;                   // отмеченные ребра
    private IndexPQ<Double> pq;                 // пригодные перекрестные ребра

    public Prim(int NumOfPeaks) {
        this.NumOfPeaks = NumOfPeaks;
        this.NumOfEdges = 0;
        list = new ArrayList[NumOfPeaks];
        for (int i = 0; i < NumOfPeaks; i++) {
            list[i] = new ArrayList<>();
        }
    }

    // получение количества вершин графа
    public int NumOfPeaks() {
        return NumOfPeaks;
    }

    // добавление ребра
    public void addEdge(Edge e) {
        int firstPeak = e.getFirstPeak(), secondPeak = e.other(firstPeak);
        list[firstPeak].add(e);
        list[secondPeak].add(e);
        NumOfEdges++;
    }

    public Iterable<Edge> list(int firstPeak) {
        return list[firstPeak];
    }
    
    @SuppressWarnings("rawtypes")
    public void AlgPrim() {
        edgeTo = new Edge[NumOfPeaks()];
        distanceTo = new double[NumOfPeaks()];
        marked = new boolean[NumOfPeaks()];
        for (int i = 0; i < NumOfPeaks(); i++)
            distanceTo[i] = Double.POSITIVE_INFINITY;
        pq = new IndexPQ(NumOfPeaks());
        distanceTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty())
            visit(pq.delMin());
    }

    // прохождение вершины
    public void visit(int firstPeak) {
        marked[firstPeak] = true;
        for (Edge e : list(firstPeak)) {
            int secondPeak = e.other(firstPeak);
            if (marked[secondPeak])
                continue;
            if (e.weight() < distanceTo[secondPeak]) {
                edgeTo[secondPeak] = e;
                distanceTo[secondPeak] = e.weight();
                if (pq.contains(secondPeak))
                    pq.change(secondPeak, distanceTo[secondPeak]);
                else {
                    System.out.println(e.toString());
                    pq.insert(secondPeak, distanceTo[secondPeak]);
                }

            }
        }
    }
}

// допольнительный класс ребро
class Edge implements Comparable<Edge> {
    private final int firstPeak;            //первая вершина
    private final int secondPeak;           //вторая вершина
    private final double weight;            //вес ребра

    public Edge(int firstPeak, int secondPeak, double weight){
        this.firstPeak = firstPeak;
        this.secondPeak = secondPeak;
        this.weight = weight;
    }

    public String toString(){
        return String.format("%d - %d веса = %.2f", firstPeak, secondPeak, weight);
    }


    // получение веса ребра
    public double weight() {
        return weight;
    }

    // получение первой вершины
    public int getFirstPeak() {
        return firstPeak;
    }

    // получение второй вершины
    public int getSecondPeak(){
        return secondPeak;
    }

    public int other(int vertex){
        if(vertex == firstPeak) return secondPeak;
        else if (vertex == secondPeak) return firstPeak;
        else throw new IllegalArgumentException("недопустимое ребро");
    }

    public int compareTo(Edge that){
        if (this.weight() < that.weight()) return -1;
        else if (this.weight() > that.weight()) return +1;
        else return 0;
    }

}
