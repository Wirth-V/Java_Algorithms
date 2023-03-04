import java.util.Iterator;
import java.util.LinkedList;

@SuppressWarnings("unchecked")
public class BFS {
    private int numOfPeaks;                     // Количество вершин
    private LinkedList<Integer> array[];        // Список смежности
 
    @SuppressWarnings("rawtypes")
    BFS (int v){
        numOfPeaks = v;
        array = new LinkedList[v];
        for (int i=0; i<v; ++i)
            array[i] = new LinkedList();
    }
 
    // Функция добавления ребра в граф
    void addEdge(int v,int w){
        array[v].add(w);
    }
 
    /*
    Алгоритм поиска в ширину:
        изначально помечаем, что все вершины посещены
        отмечаем текущий узел, как посещенный и добавляем его в очередь
        и пока размер очереди не равен нулю, удаляем вершину из очереди и выводим ее
        затем получаем все вершины смежные с startPoint
    */
    public void bfs(int startPoint){
        boolean visited[] = new boolean[numOfPeaks];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[startPoint] = true;
        queue.add(startPoint);
 
        while (queue.size() != 0){
            startPoint = queue.poll();
            System.out.print(startPoint + " ");
            Iterator<Integer> i = array[startPoint].listIterator();
            while (i.hasNext()){
                int n = i.next();
                if (!visited[n]){
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        System.out.println();
    }
}
