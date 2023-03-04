@SuppressWarnings("unchecked")
// Очередь с приориетом (индесная)
public class IndexPQ <T extends Comparable< T>> {

    private int N; // Количество элементов
    private final int[] pq;
    private final int[] qp;
    private T[] keys; // Элементы с приоритетами

    public IndexPQ(int maxN){
         keys = (T[]) new Comparable [maxN + 1];
         pq = new int[maxN + 1];
         qp = new int[maxN + 1];
         for(int i = 0;i<=maxN; i++)
             qp[i] = -1;
    }

    public boolean isEmpty(){
         return N==0;
    }

    // Проверка связи индекса с элементом
    public boolean contains(int k){
         return qp[k] != -1;
    }

    // Вставка
      public void insert(int k, T key){
         N++;
         qp[k] = N;
         pq[N] = k;
         keys[k] = key;
         swim(N);
      }

      // Удаление
      public int delMin(){
         int index = pq[1];
         exch(1, N--);
         sink(1);
         keys[pq[N+1]] = null;
         qp[pq[N+1]] = -1;
         return index;
      }

      // Расширение
      public void exch(int i, int j){
         int t = pq[i];
         pq[i] = pq[j];
         pq[j]=t;
       }

       // Восходящее восстановление пирамиды
       public void swim(int k){ 
         while (k>1 && less(k/2, k)){
             exch(k/2,k);
             k=k/2;
         }
       }

    // Наименьшее
    private boolean less(int i, int k) {
         return compare(i,k)<0;
    }


    // Сравнение двух объектов
    public int compare(int o1, int o2 ) {
         int k = 0;
        if( o1 == o2 )
            k = 0;
        if( o1 > o2 )
            k = 1;
        if( o1 < o2 )
            k = -1;
        return k;
    }

    // Нисходящее восстановление пирамиды
    private void sink(int k){
         while (2*k <= N){
             int j = 2*k;
             if(j< N && less(j,j+1))
                 j++;
             if(!less(k,j))
                 break;
             exch(k,j);
             k = j;
         }
    }

    // Замена на key с идексом k
    public void change(int k, T key){
         keys[k]=key;
         swim(qp[k]);
         sink(qp[k]);

    }

}