package a;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste..neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;


public class a {
	private static String input = "/Task4/src/data.csv";
	private static final int NEIGHBORHOOD_SIZE = 4;
	private static DataModel model = null;
	private static LogLikelihoodSimilarity similarity = null;
	private static UserNeighborhood neighborhood = null;
	private static UserBasedRecommender recommender = null;
	
	private static String[] books = { "To Kill a Mockingbird", "Harry Potter and the Order of the Phoenix", "Pride and Prejudice","Twilight", "The Chronicles of Narnia", "Romeo and Juliet", "The Alchemist", "Crime and Punishment", "Dracula", "A Game of Thrones"};

    private static String[] userNames = { "amrit", "raghu", "sunny", "suraj", "vikas", "robby", "ravinder", "varun", "sumit", "khushpinder", "aman", "nitin", "robin", "singh", "aditya", "rahul", "raman", "manu", "garav", "mani"};
		 
    public static void main(String args[]) throws IOException, TasteException {
    	model = new FileDataModel(new File(input));
    	similarity = new LogLikelihoodSimilarity(model);
    	neighborhood = new NearestNUserNeighborhood(NEIGHBORHOOD_SIZE, similarity, model);
    	
    	recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
    	
    	List<RecommendedItem> recommendations = recommender.recommend(1,5);
    	
    	System.out.println("Recommendations for customer " + userNames[0] + " are:");
    	System.out.println("************************************************");
    	
    	System.out.println("BookId \t title \t \t estimated preference");
    	for(RecommendedItem recommendation : recommendations) {
    		int bookId = (int) recommendation.getItemID();
    		float estimatedPref = recommender.estimatePreference(1, bookId);
    		System.out.println(bookId + " " + books[bookId - 1] + "\t" + estimatedPref);
    		
    	}
    	
    	System.out.println("************************************************");
    	long[] userIDs = recommender.mostSimilarUserIDs(1,5);
    	System.out.println("Most similar users for " + usernames[0] + "are");
    	for(long id : userIDs) {
    		System.out.println(id + " " +usernames[(int) id - 1]);
    	}
    }

}
	