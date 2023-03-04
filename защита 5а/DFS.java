import java.util.Iterator;
import java.util.LinkedList;

@SuppressWarnings("unchecked")
public class DFS {
    public LinkedList<Integer>[] array;          // Список смежности
    public boolean visited[];                    // Посешённые вершины
 
    DFS(int peaks){
        array = new LinkedList[peaks];
        visited = new boolean[peaks];
 
        for (int i = 0; i < peaks; i++)
            array[i] = new LinkedList<Integer>();
    }
 
    public void addEdge(int start, int end){
        array[start].add(end);
    }
 
    @SuppressWarnings("rawtypes")
    public void dfs(int v){
        visited[v] = true;
        System.out.print(v + " ");
 
        Iterator i = array[v].listIterator();
        while (i.hasNext())
        {
            int tmp = (int) i.next();
            if (!visited[tmp])
                dfs(tmp);
        }
        System.out.println();
    }
}
