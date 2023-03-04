class test2{
	public static void main(String[] args){
		Integer[] arr = {6, 12, 15, 21, 24, 30};
		prior_queue<Integer> Q = new prior_queue<Integer>(arr);
        	for (Integer el : Q.list)
			System.out.println(el.toString());

		int i=1;
		while(i<10001){
			for(i=0;i<Q.len()-10;i++)
				Q._remove();
			if(i%3==0 && i%9!=0){
			
			}
		}
		i++;
	}
}


	