import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *  COMP-10205 - Starting code for Assignment # 3
 *
 *  This program has Part A and Part B.
 *  The functionality of Part A is to count how many words there are in a file, and count how many unique words there are. Also, it runs three different
 *  search methods (linear search, binary serach, bashset search) to compare two files and check which words are not found.
 *  The functionality of Part B is to count the sum of distanced between the matched pairs of 'war' and 'peace' words. Also, it sums
 *  the average distance between the matched pairs.
 *
 *  I, Daniel Miguel, 000869785 certify that this material is my original work. No other person's work has been used without due acknowledgement.
 *
 *  File date: 10/17/2024
**/
public class Main {

    /**
     * wordCount method goes through all the words in the file and increases a counter.
     *
     * @param fileName file name we want to load into the method.
     * @return sum of words in the file.
     */
    public static int wordCount(String fileName) {
        int words = 0; // keeps track of how many words there are in the file.

        try {
            Scanner file = new Scanner(new File(fileName));

            String regEx = "\\.|\\?|\\!|\\s|\"|\\(|\\)|\\,|\\_|\\-|\\:|\\;|\\n"; // regEx is used to filter through specific characters.
            file.useDelimiter(regEx);

            // while file has something next.
            while (file.hasNext()) {
                String word = file.next().toLowerCase().trim(); // get the next word make it lower case and trim it.

                // if word is not empty (space).
                if (!word.isEmpty()) {
                    words++; // increment words by 1.
                }
            }
            file.close(); // close the file.

        } catch(FileNotFoundException ex) {
            System.out.println("File not found.");
        }
        return words;
    }

    /**
     * uniqueWords method goes through all the words in the file and stores every word to check if it is unique, and keep track of unique words.
     * @param fileName file name we want to load into the method.
     * @returnsum of unique words in the file.
     */
    public static int uniqueWords(String fileName) {
        ArrayList<BookWord> wordsInFile = new ArrayList<>(); // ArrayList of BookWords will store unique words.
        SimpleHashSet<String> wordSet = new SimpleHashSet<>(); // SimpleHashSet of Strings will store unique words.
        // (the difference is SimpleHashSet is better for efficiency).


        try {
            Scanner file = new Scanner(new File(fileName));

            String regEx = "\\.|\\?|\\!|\\s|\"|\\(|\\)|\\,|\\_|\\-|\\:|\\;|\\n"; // regEx is used to filter through specific characters.
            file.useDelimiter(regEx);

            // while file has something next.
            while (file.hasNext()) {
                String word = file.next().toLowerCase().trim(); // get the next word make it lower case and trim it.

                // if word is not empty (space).
                if (!word.isEmpty()) {

                    // if word is not found in wordSet.
                    if (!wordSet.contains(word)) {
                        wordSet.insert(word); // insert word into wordSet.
                        BookWord newWord = new BookWord(word); // create a new BookWord object.
                        newWord.incrementCount(); // increment the count for this new word.
                        wordsInFile.add(newWord); // add newWord into wordsInFile.
                    } else {
                        // foreach loop will go through the wordsInFile.
                        for (BookWord bookWord : wordsInFile) {

                            // if the word from bookWord is equals to the word.
                            if (bookWord.getText().equals(word)) {
                                bookWord.incrementCount(); // increment the count for this book word.
                                break; // once found break from the condition.
                            }
                        }
                    }
                }
            }
            file.close(); // close the file.

        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        }

        return wordsInFile.size();
    }

    /**
     * linearSearch method goes through all the words in words file to check if the dictionary word was found in the words file.
     * @param wordsFile words file name we want to load into the method.
     * @param dictionaryFile dictionary file name we want to load into the method.
     * @return sum of words not found using linear search.
     */
    public static int linearSearch(String wordsFile, String dictionaryFile) {
        int notFound = 0; // keeps track of how many words were not found.
        ArrayList<String> WarAndPeaceWords = new ArrayList<>(); // ArrayList of Strings will store the words in the WarAndPeace file.
        SimpleHashSet<String> USWords = new SimpleHashSet<>(); // SimpleHashSet of Strings will store the words in the USWords file.

        try {
            Scanner WarAndPeace = new Scanner(new File(wordsFile));
            Scanner US = new Scanner(new File(dictionaryFile));

            String regEx = "\\.|\\?|\\!|\\s|\"|\\(|\\)|\\,|\\_|\\-|\\:|\\;|\\n"; // regEx is used to filter through specific characters.
            WarAndPeace.useDelimiter(regEx);
            US.useDelimiter(regEx);

            // while WarAndPeace file has something next.
            while(WarAndPeace.hasNext()) {
                String word = WarAndPeace.next().toLowerCase().trim(); // get the next word make it lower case and trim it.

                // if word is not empty (space).
                if (!word.isEmpty()) {
                    WarAndPeaceWords.add(word); // add word to WarAndPeaceWords.
                }
            }
            WarAndPeace.close(); // close WarAndPeace file.

            // while US file has something next.
            while(US.hasNext()) {
                String word = US.next().toLowerCase().trim(); // get the next word make it lower case and trim it.

                // if word is not empty (space).
                if (!word.isEmpty()) {
                    USWords.insert(word); // insert word to USWords.
                }
            }
            US.close(); // close US file.

        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        }

        // foreach loop goes through the WarAndPeace array.
        for (String word : WarAndPeaceWords) {

            // if word USWords doesn't contain the word.
            if (!USWords.contains(word)) {
                notFound++; // increment notFound by 1.
            }
        }
        return notFound;
    }

    /**
     * binarySearch method goes through all the words in words file to check if the dictionary word was found in the words file.
     * @param wordsFile words file name we want to load into the method.
     * @param dictionaryFile dictionary file name we want to load into the method.
     * @return sum of words not found using binary search.
     */
    public static int binarySearch(String wordsFile, String dictionaryFile) {
        int notFound = 0; // keeps track of how many words were not found.
        ArrayList<String> WarAndPeaceWords = new ArrayList<>(); // ArrayList of Strings will store the words in the WarAndPeace file.
        ArrayList<String> USWords = new ArrayList<>(); // ArrayList of Strings will store the words in the US file.

        try {
            Scanner WarAndPeace = new Scanner(new File(wordsFile));
            Scanner US = new Scanner(new File(dictionaryFile));

            String regEx = "\\.|\\?|\\!|\\s|\"|\\(|\\)|\\,|\\_|\\-|\\:|\\;|\\n"; // regEx is used to filter through specific characters.
            WarAndPeace.useDelimiter(regEx);
            US.useDelimiter(regEx);

            // while WarAndPeace file has something next.
            while(WarAndPeace.hasNext()) {
                String word = WarAndPeace.next().toLowerCase().trim(); // get the next word make it lower case and trim it.

                // if word is not empty (space).
                if (!word.isEmpty()) {
                    WarAndPeaceWords.add(word); // add word to WarAndPeaceWords.
                }
            }
            WarAndPeace.close(); // close WarAndPeace file.

            // while US file has something next.
            while(US.hasNext()) {
                String word = US.next().toLowerCase().trim(); // get the next word make it lower case and trim it.

                // if word is not empty (space).
                if (!word.isEmpty()) {
                    USWords.add(word); // add word to USWords.
                }
            }
            US.close(); // close US file.

        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        }

        Collections.sort(USWords); // sort the USWords using Collections.

        // for loop will go through WarAndPeaceWords.
        for (String word : WarAndPeaceWords) {
            // index will make a lambda comparison of USWords and word to check if it contains or not.
            int index = Collections.binarySearch(USWords, word, (a, b) -> a.compareTo(b));
            // if index is less than 0 meaning the word wasn't found in USWords.
            if (index < 0) { notFound++; /* increment notFound by 1 */ }
        }

        return notFound;
    }

    /**
     * bashsetSearch method goes through all the words in words file to check if the dictionary word was found in the words file.
     * @param wordsFile words file name we want to load into the method.
     * @param dictionaryFile dictionary file name we want to load into the method.
     * @return sum of words not found using bashset search.
     */
    public static int bashsetSearch(String wordsFile, String dictionaryFile) {
        int notFound = 0; // keeps track of how many words were not found.
        ArrayList<String> WarAndPeaceWords = new ArrayList<>(); // ArrayList of Strings will store the words in the WarAndPeace file.
        SimpleHashSet<String> USWords = new SimpleHashSet<>(); // SimpleHashSet of Strings will store the words in the US file.

        try {
            Scanner WarAndPeace = new Scanner(new File(wordsFile));
            Scanner US = new Scanner(new File(dictionaryFile));

            String regEx = "\\.|\\?|\\!|\\s|\"|\\(|\\)|\\,|\\_|\\-|\\:|\\;|\\n"; // regEx is used to filter through specific characters.
            WarAndPeace.useDelimiter(regEx);
            US.useDelimiter(regEx);

            // while WarAndPeace file has something next.
            while(WarAndPeace.hasNext()) {
                String word = WarAndPeace.next().toLowerCase().trim(); // get the next word make it lower case and trim it.

                // if word is not empty (space).
                if (!word.isEmpty()) {
                    WarAndPeaceWords.add(word); // add word to WarAndPeaceWords.
                }
            }
            WarAndPeace.close(); // close WarAndPeace file.

            // while US file has something next.
            while(US.hasNext()) {
                String word = US.next().toLowerCase().trim(); // get the next word make it lower case and trim it.

                // if word is not empty (space).
                if (!word.isEmpty()) {
                    USWords.insert(word); // insert word to USWords.
                }
            }
            US.close(); // close US file.

        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        }

        // foreach loop will go through WarAndPeaceWords.
        for (String word : WarAndPeaceWords) {

            // if USWords doesn't contain word.
            if (!USWords.contains(word)){ notFound++; /* increment notFound by 1 */}
        }

        return notFound;
    }

    /**
     * proximitySearch method goes through all the word in the file and checks the distance between the words 'peace' and 'war'.
     * @param wordsFile words file name we want to load into the method.
     * @return the sum of distances between the matched pairs 'war' and 'peace', and the average distance between the matched pairs.
     */
    public static int[] proximitySearch(String wordsFile) {
        int wordCount = 0; // keeps track of how many words there are.
        int warWordPos = -1; // keeps track of war word position.
        int peaceWordPos = -1; // keeps track of peace word position.
        int distance = 0; // keeps track of the distance between both words.
        int occurences = 0; // keeps track of how many times the comparison happens.
        try {
            Scanner WarAndPeace = new Scanner(new File(wordsFile));

            String regEx = "\\.|\\?|\\!|\\s|\"|\\(|\\)|\\,|\\_|\\-|\\:|\\;|\\n"; // regEx is used to filter through specific characters.
            WarAndPeace.useDelimiter(regEx);

            // while WarAndPeace file has something next.
            while (WarAndPeace.hasNext()) {
                String word = WarAndPeace.next().toLowerCase().trim(); // get the next word make it lower case and trim it.
                wordCount++; // increment wordCount by 1.

                // if word is not empty (space).
                if (!word.isEmpty()) {

                    // if word is equal to 'peace'.
                    if (word.equals("peace")) {
                        peaceWordPos = wordCount; // update the position of the word (peaceWordPos) to the word number (wordCount).

                        // if warWordPos is not -1 meaning we found the 'war' word already.
                        if (warWordPos != -1) {
                            distance += (peaceWordPos - warWordPos); // increment the distance from the peaceWordPos and warWordPos.
                            occurences++; // increment occurrences by 1.

                            warWordPos = -1; // update the warWordPos back to -1.
                        }

                    // else if word is equal to 'war'.
                    } else if (word.equals("war")) {
                        warWordPos = wordCount; // update the position of the word (warWordPos) to the word number (wordCount).

                        // if peaceWordPos is not -1 meaning we found the 'peace' word already.
                        if (peaceWordPos != -1) {
                            distance += (warWordPos - peaceWordPos); // increment the distance from the peaceWordPos and warWordPos.
                            occurences++; // increment occurrences by 1.

                            peaceWordPos = -1; // update the peaceWordPos back to -1.
                        }
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        }

        int averageDistance = (distance / occurences); // keep track of average distance.

        int[] retval = new int[2];
        retval[0] = distance;
        retval[1] = averageDistance;
        return retval;
    }

    /**
     * main method will keep track of each part runtime and total runtime. Also, display print statements.
     * @param args unused
     */
    public static void main(String[] args) {

        String filename = "src/WarAndPeace.txt"; // WarAndPeace file source.
        String USfilename = "src/US.txt"; // US file source.
        long startTimePartA; // keeps track of start time for Part A.
        long endTimePartA; // keeps track of end time for Part A.
        long startTimePartAB; // keeps track of start time for Part A and Part B.
        long endTimePartAB; // keeps track of end time for Part A and Part B.
        long startTimeMethod; // keeps track of start time for methods.
        long endTimeMethod; // keeps track of end time for methods.
        double execTimeMs; // keeps track of execution time.

        System.out.println("PART A\n======");
        startTimePartAB = System.currentTimeMillis(); // starts counter for Part A and Part B.
        startTimePartA = System.currentTimeMillis(); // starts counter for Part A.

        int wordsCount = wordCount(filename); // wordsCount calls the wordCount method with the filename.
        System.out.printf("There are a total of %,d words in %s.\n",
                wordsCount, filename);

        int uniqueWords = uniqueWords(filename); // uniqueWords calls the uniqueWords method with the filename.
        System.out.printf("There are a total of %,d unique words in %s.\n",
                uniqueWords, filename);


        System.out.println("\nDictionary Search, timing for 3 methods:");

        startTimeMethod = System.currentTimeMillis(); // starts counter for linearSearch method.
        int linearSearch = linearSearch(filename, USfilename); // linearSearch calls the linearSearch method with the filename and USfilename.
        endTimeMethod = System.currentTimeMillis(); // ends counter for linearSearch method.
        execTimeMs = (endTimeMethod - startTimeMethod); // Subtract endTimeMethod with startTimeMethod to get the time of execution.
        System.out.printf("LINEAR SEARCH   - %,d words not found in dictionary - time = %,.0f ms\n", linearSearch, execTimeMs);

        startTimeMethod = System.currentTimeMillis(); // starts counter for binarySearch method.
        int binarySearch = binarySearch(filename, USfilename); // binarySearch calls the binarySearch method with the filename and USfilename.
        endTimeMethod = System.currentTimeMillis(); // ends counter for binarySearch method.
        execTimeMs = (endTimeMethod - startTimeMethod); // Subtract endTimeMethod with startTimeMethod to get the time of execution.
        System.out.printf("BINARY SEARCH   - %,d words not found in dictionary - time = %,.0f ms\n", binarySearch, execTimeMs);

        startTimeMethod = System.currentTimeMillis(); // starts counter for bashsetSearch method.
        int bashsetSearch = bashsetSearch(filename, USfilename); // bashsetSearch calls the bashsetSearch method with the filename and USfilename.
        endTimeMethod = System.currentTimeMillis(); // ends counter for bashsetSearch method.
        execTimeMs = (endTimeMethod - startTimeMethod); // Subtract endTimeMethod with startTimeMethod to get the time of execution.
        System.out.printf("BASHSET SEARCH  - %,d words not found in dictionary - time = %,.0f ms\n", bashsetSearch, execTimeMs);

        endTimePartA = System.currentTimeMillis(); // ends counter for Part A.
        execTimeMs = (endTimePartA - startTimePartA); // Subtract endTimePartA with startTimePartA to get the time of execution.
        System.out.printf("\nTOTAL TIME for all of PART A = %,.0f ms\n", execTimeMs);


        System.out.println("\nPART B\n======");

        int[] proximitySearch = proximitySearch(filename); // proximitySeach calls the proximitySearch method with the filename.
        System.out.printf("The total sum of distances between the matched pairs 'war' and 'peace' = %,d\n", proximitySearch[0]);

        System.out.printf("The average distance between the matched pairs 'war' and 'peace' = %,d\n", proximitySearch[1]);

        endTimePartAB = System.currentTimeMillis(); // ends counter for Part A and Part B.
        execTimeMs = (endTimePartAB - startTimePartAB); // Subtract endTimePartAB with startTimePartAB to get the time of execution.
        System.out.printf("\nTOTAL RUNTIME (Part A + Part B) = %,.0f ms", execTimeMs);
    }
}