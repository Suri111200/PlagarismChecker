public interface Similarity extends WordMap{
	
	double score(WordMap a, WordMap b);
}