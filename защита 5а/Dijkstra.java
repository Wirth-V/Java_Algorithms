import java.util.*;

@SuppressWarnings("unchecked")
class Dijkstra {
    private final int NumOfPeaks;               // количество вершин

    // получение количества вершнин
    public int getNumOfPeaks() {
        return this.NumOfPeaks;
    }

    private ArrayList<WeightedEdge>[] array;   
    private WeightedEdge[] edgesForShortestWay;     // ребра кратчайших путей
    private double[] distanceTo;                    // кратчайший путь до нужной вершины
    private IndexPQ<Double> pq;

    public Dijkstra(int numOfPeaks){
        this.NumOfPeaks = numOfPeaks;
        array = new ArrayList[NumOfPeaks];
        for (int i = 0; i < numOfPeaks; i++){
            array[i] = new ArrayList<>();
        }
    }
    
    // добавление вершины
    public void addEdge(WeightedEdge we){
        array[we.getVert()].add(we);
    }

    // ребра направленные из v
    public Iterable<WeightedEdge> array(int v){
        return array[v];
    }

    public Iterable<WeightedEdge> edges(){
        ArrayList<WeightedEdge> list = new ArrayList<>();
        for (int v = 0; v < NumOfPeaks; v++)
            for (WeightedEdge e: array[v]) {
                list.add(e);
                System.out.println(e);
            }
        return list;
    }

    // алгоритм Дейкстры
    @SuppressWarnings("rawtypes")
    public void DIjkstra(int startPoint){ 
        edgesForShortestWay = new WeightedEdge[getNumOfPeaks()];
        distanceTo =new double[getNumOfPeaks()];
        pq = new IndexPQ(getNumOfPeaks());
        for (int v = 0; v < getNumOfPeaks(); v++)
            distanceTo[v] = Double.POSITIVE_INFINITY;
        distanceTo[startPoint] = 0.0;
        pq.insert(startPoint, 0.0);
        while (!pq.isEmpty())
            relax( pq.delMin());
    }

    private void relax( int v){
        for(WeightedEdge e: array(v)){
            int w = e.getFinalPeak();
            if(distanceTo[w] > distanceTo[v]+ e.getWeight()){
                distanceTo[w] = distanceTo[v] + e.getWeight();
                edgesForShortestWay[w]= e;
                if(pq.contains(w))
                    pq.change(w, distanceTo[w]);
                else
                    pq.insert(w, distanceTo[w]);

            }
        }
    }

    // путь до вершины
    public double distanceTo(int v){
        return distanceTo[v];
    }

    // проверка на наличие пути до вершины
    public boolean hasPath(int v) { return distanceTo[v] < Double.POSITIVE_INFINITY;}

    // получение пути до вершины
    @SuppressWarnings("rawtypes")
    public Iterable<WeightedEdge> pathTo(int v){
        if(!hasPath(v))
            return null;
        Stack<WeightedEdge> path = new Stack();
        String str = String.format("путь из 0 в %d", v);
        System.out.println(str);
        for(WeightedEdge e = edgesForShortestWay[v]; e!=null; e = edgesForShortestWay[e.getVert()]){
            path.push(e);
            System.out.println(e.toString());
        }
        System.out.println("вес пути = " + distanceTo(v));
        print();
        return path;
    }

    // вывод
    public void print(){
        for(int i = 0; i < edgesForShortestWay.length; i++){
            WeightedEdge e = edgesForShortestWay[i];
            if(e!=null) {
                System.out.println(e);
            }
        }
    }
}

// допольнительный класс взвешенного графа
class WeightedEdge {
    private final int vert;             // начальная вершина
    private final int finalPeak;        // конечная вершина
    private final double weight;        // вес(значение) ребра

    // стандартный конструктор
    public WeightedEdge(int vert, int finalPeak, double weight) {
        this.vert = vert;
        this.finalPeak = finalPeak;
        this.weight = weight;
    }

    public double getWeight() { return weight; }        // получение веса

    public int getVert() { return vert; }               // получение начальной вершины

    public int getFinalPeak() { return finalPeak; }     // получение конечной вершины

    public String toString(){
        return String.format("%d->%d %.2f",vert,finalPeak,weight);
    }  
}