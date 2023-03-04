import java.util.Arrays;  // for Arrays.asList()
import java.util.ArrayList; // To make instance of List we need ArrayList \_(*_*)_/
import java.util.List;    // for List object
import java.lang.Math;    // for floor(), pow(), log()



public class prior_queue <T extends Comparable <T>>  
{   

    
    List<T> list = new ArrayList<T>();                     
    int n;

// --------------- Sub functions
    double log2(int n)
    {
        return Math.log(n) / Math.log(2);
    }

 
    void swap(int i, int j){           
        T buff = list.get(i);
        list.set(i, list.get(j));
        list.set(j, buff);  }


    void build(int cur){
        
        int l_idx = cur*2 + 1;  int r_idx = cur*2 + 2;
        int M_idx = cur;                                
        T par;                                          
        
        // Watching trio (par, l, r). l and par are precounted for "readable-ness"
        if (l_idx < n) { T l = list.get(l_idx); par = list.get(M_idx); if (l.compareTo(par) > 0) M_idx = l_idx; }  
        if (r_idx < n) { T r = list.get(r_idx); par = list.get(M_idx); if (r.compareTo(par) > 0) M_idx = r_idx; }
       
         
        if (M_idx != cur){
            swap(cur, M_idx);
            build(M_idx);       
        }

        if (cur == 0)           
            return;
        else
            build(cur-1);       
    } 


    void shift_down(int cur){
        
        int l_idx = cur*2 + 1;  int r_idx = cur*2 + 2;
        int M_idx = cur;                                
        T par;                                          
        
        if (l_idx < n) { T l = list.get(l_idx); par = list.get(M_idx); if (l.compareTo(par) > 0) M_idx = l_idx; }  
        if (r_idx < n) { T r = list.get(r_idx); par = list.get(M_idx); if (r.compareTo(par) > 0) M_idx = r_idx; }
        
        if (M_idx != cur){
            swap(cur, M_idx);
            shift_down(M_idx); 
            }

        else                       
            return; 
    } 


// --------------- Constructor
    prior_queue(T[] lst){
        //list = Arrays.<T>asList(lst);  
        
        for (T el : lst)
            list.add(el);
            
        n = list.size();
        if (n > 1)
        {
            // seeking last node that has children 
            int h = (int)Math.ceil(log2(n));    
            int cur = (int)Math.pow(2, h-1) - 1;    
            while(2*cur + 1 > n-1)                  
                cur--;                           
            
            build(cur); 
        }
    }
    
// -------------- Required Methods
    public int len()           { return n; }
    public T get_max()         { return list.get(0); }
    public boolean is_empty()  { return (n == 0); }

    public void _add(T val){
        n++;
        list.add(val);                                              

        int i = n-1;                                                
        int par_i = (int)Math.floor((i-1)/2);                      
        
        while (val.compareTo( list.get(par_i) ) > 0 && i != 0){    
            swap(par_i, i);
            i = par_i;                                                
            par_i = (int)Math.floor((i-1)/2);                      
        }
    }
 
    
    public T _remove(){                
        T first = get_max();
        
        // rebuild
        swap(0, n-1);
        list.remove(n-1);
        n--;         

        shift_down(0);                  
        return first;
    }
}




