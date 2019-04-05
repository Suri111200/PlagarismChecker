public class Jaccard implements Similarity{
	

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
}