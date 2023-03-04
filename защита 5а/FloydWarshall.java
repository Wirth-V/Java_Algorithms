public class FloydWarshall{
    private int NumOfPeaks;//количество вершин в графе
    private final int INF = 1000000;
    private Vertex[] vertex;//список вершин
    private int adj[][];//матрица смежности
    public FloydWarshall(int v){
        this.NumOfPeaks = v;
        vertex = new Vertex[NumOfPeaks];
        adj = new int[NumOfPeaks][NumOfPeaks];
        for(int j = 0; j < NumOfPeaks; j++)
            for(int k = 0; k < NumOfPeaks; k++)
            {
                if(j == k)
                    adj[j][k] = 0;
                else
                adj[j][k] = INF;
            }
    }

    public void addEdge(int v, int w, int weight){
        adj[v][w] = weight;
        adj[w][v] = weight;
    }

    public void floydWarshall() {
        int distance[][] = new int[NumOfPeaks][NumOfPeaks];
        int i, j, k;
        for (i = 0; i < NumOfPeaks; i++)
            for (j = 0; j < NumOfPeaks; j++) {
                distance[i][j] = adj[i][j];
            }
        for (k = 0; k < NumOfPeaks; k++) {
            for (i = 0; i < NumOfPeaks;i++) {
                for (j = 0; j < NumOfPeaks; j++) {
                    if (distance[i][k] + distance[k][j] < distance[i][j])
                        distance[i][j] = distance[i][k] + distance[k][j];
                }
            }
        }
        print(distance);
    }

    // вывод 
    void print(int n[][]) {
        for (int i = 0; i < NumOfPeaks; ++i) {
            for (int j = 0; j < NumOfPeaks; ++j) {
                if (n[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(n[i][j] + "   ");
            }
            System.out.println();
        }
    }

    // допольнительный класс 
    class Vertex{
        public char с;
        public boolean isIn;
        public Vertex(char С)
        {
            с = С;
            isIn = false;
        }
    }

}
