//import java.util.Arrays;
public class Jaccard implements Similarity{
	
	/*
	public double score (WordMap a, WordMap b){
		
		//AnB
		int numerator = 0;

		String[] theKeysA = a.keys();
		String[] theKeysB = b.keys();
		Integer[] countA = a.counts();
		Integer[] countB = b.counts();

		for(int i=0; i< theKeysA.length; i++){

			if (b.contains(theKeysA[i])){
				numerator+=1;
			}

		}
		//AuB = A+B-AnB
		int denominator =theKeysA.length+theKeysB.length-numerator;

		return numerator/denominator;
	}
	*/


	public double score(WordMap a, WordMap b){
		double toReturn;

		String[] listOne=a.keys();
		String[] listTwo=b.keys();

		double numerator=0;
		double denominator=0;

		if(a==null || b==null){
			toReturn=0;
		}
		if(a==null && b==null){
			toReturn=1;
		}
		//n
		for(int i=0; i<listTwo.length; i++){
			if(a.contains(listTwo[i])){
				numerator++;
			}
		}
    	
    	//u
    	for(int i=0; i<listOne.length; i++){
    			denominator++;
    	}
    	for(int i=0; i<listTwo.length; i++){
    		if(a.contains(listTwo[i])==false){
    			denominator++;
    		}
    	}

    	if(denominator==0)
    		toReturn=1;
    	else
    		toReturn=(numerator/denominator);
    	
		return toReturn;
	}
}