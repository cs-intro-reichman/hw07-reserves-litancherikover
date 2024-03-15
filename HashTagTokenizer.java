

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) 
	{
		//Initializing an array for the words in the dictinaory
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i=0; i<dictionary.length; i++)
		{
			//Insert each word into the dict
			dictionary[i]=in.readLine(); 
		}
		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary)
	{
		//The function designed to take a string as an input and determine its presence in the dictionary
		for ( int i = 0; i < dictionary.length; i++)
		{
			if (word.equals(dictionary[i]))
				return true;
		}
		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) 
	{

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) 
		{
            return;
        }
 
        int N = hashtag.length();

        for (int i = 1; i <= N; i++) 
		{
			if (existInDictionary(hashtag.substring(0, i), dictionary)) 
			{
				System.out.println(hashtag.substring(0,i));
				breakHashTag(hashtag.substring(i), dictionary);
				break;
			}
        }
    }

}
