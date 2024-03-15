
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) 
	{
		if (str.length() == 0) 
		{
			return "";
		}
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) 
	{
		//convert input to lowercase 
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		if (word1.isEmpty()) return word2.length();
        if (word2.isEmpty()) return word1.length();
		if (word1.charAt(0) == word2.charAt(0))
		{
			return  levenshtein(tail(word1), tail(word2));
		}
		else
		{
			return 1 + Math.min( Math.min(levenshtein(tail(word1), tail(word2)) ,
			 levenshtein(word1, tail(word2))),levenshtein(word2, tail(word1)) ) ;
		}
	}

	public static String[] readDictionary(String fileName) 
	{
		//Same method as in Hash...
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i=0; i<dictionary.length; i++)
		{
			//Insert each word into the dict
			dictionary[i]=in.readLine(); 
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) 
	{
		String MinimalWord = "";
		int MinimalDistance = word.length(); 
		for(int i = 1; i < dictionary.length; i++)
		{
			//Get back the distance between the word from dict and the input
			int Distance = levenshtein(dictionary[i], word);
			if (Distance < MinimalDistance)
			{
				MinimalDistance = Distance;
				MinimalWord = dictionary[i];
			}
			if (MinimalDistance <= threshold)
			{
				return MinimalWord;
			}
		}
		return word;
	}

}
