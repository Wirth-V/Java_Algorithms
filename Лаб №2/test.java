
import java.util.Scanner;

public class test {      // stucture to access some value by "key". for ex: key may be string and value - some object
   

    public static void main(String[] args) throws Exception{ // Exception may be thrown from get_by_key() after we try to get value by unexcisting key
        
    
        System.out.println("\n\n---Creating map:");
        _map<Integer, String> Bascetballers = new _map();
        Integer[] ages = { 26, 10, 12, 24, 13};
        String[] names =    {"Folin", "CS", "Badge-e", "Mil-pops", "Bee-F"};
        

        
        for(int i = 0; i < 5; ++i)
            Bascetballers.append(ages[i], names[i]);    
        
        _map.map_dump(Bascetballers);
        System.out.println("\n\n---Who has age 12? - " + Bascetballers.get_by_key(12).toString());

        System.out.println("\n\n---Making phisical copy(shot) of current map`s state");
        _map<Integer, String> Shot = new _map(Bascetballers);

        System.out.println("\n\n---Apending new element to old map:");
        Bascetballers.append(15, "Joshua");
        System.out.println("\n\n---Now map looks like this:");_map.map_dump(Bascetballers);
        System.out.println("\n\n---And shot of map didn`t changed(which means - we made copy phisicaly, not by link):");
        _map.map_dump(Shot);

        System.out.println("\n\n---Is thre element with key = 12 in 'Shot'? - " + Shot.is_excists(12));
        System.out.println("--- and in src map? - " + Bascetballers.is_excists(12));
        
        System.out.println("\n\n---Deleting Shot of map:");
        Shot.vipe();
        _map.map_dump(Shot);
        System.out.println("\n\n---Is map 'Shot' empty now? - " + Shot.is_empty());

        System.out.println("\n\n---Lets initialize excisting map(Shot) by another map (without recreating) - it`s .copy_map() work: ");
        Shot.copy_map(Bascetballers);
        _map.map_dump(Shot);
        System.out.println("\n\n---Is map 'Shot' empty now? - " + Shot.is_empty());
    }
}
