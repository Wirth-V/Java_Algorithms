
public class test_AVL {      // stucture to access some value by "key". for ex: key may be string and value - some object
   

    public static void main(String[] args){
        AVL_tree<String> nigga = new AVL_tree("Folin");
        String [] homies = {"CS", "Badge-e", "Mil-pops", "Bee-F"};
        
        System.out.println("\n\n---Creating tree:");
        AVL_tree<Integer> nums = new AVL_tree();
        Integer[] same = { 26, 10, 12, 24, 13};

        
        for (Integer eva : same) nums.append(eva);

        AVL_tree.tree_dump(nums);

        System.out.println("\n\n---Making copy(shot) of current tree`s state");
        AVL_tree shot = new AVL_tree(nums); 

        System.out.println("\n\n---Appendeding elements 7, 12 in tree:");  
        nums.append(7);  nums.append(12);      
        AVL_tree.tree_dump(nums);

        nums = null;
        System.out.println("\n\n---Copy`s(shot`s) data - still same even \"source tree\" had changed:");
        AVL_tree.tree_dump(shot);



}

}
