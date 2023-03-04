
class test
{
    public static void main(String[] args)
    {
        String sep = "\n-----------------------------------------------\n";
        Integer[] arr = {3, 67, 7, 4, 3169, 400, 17, 37, 735, 11, 0, -9, 33, 16};
 
        System.out.println(sep + " Created Queue(puramid)" + sep);
        prior_queue<Integer> Q = new prior_queue<Integer>(arr); 
        for (Integer el : Q.list) 
            System.out.println(el.toString());

        System.out.println(sep + " Adding element " + 5555 + " to Queue" + sep);
        Q._add(5555);
        for (Integer el : Q.list) 
            System.out.println(el.toString());

        System.out.println();
       
        System.out.println(sep + " Removing top of Queue" + sep);
        Q._remove();
        for (Integer el : Q.list) 
            System.out.println(el.toString());
     
        System.out.println(" \n Is Queue empty? \n " + Q.is_empty() + sep);
        System.out.println("How many elements in Queue now? \n " + Q.len() + sep);
        System.out.println("Which element has biggest priority? \n " + Q.get_max() + sep);


}
}