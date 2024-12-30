import java.util.Hashtable;

public class BookWord {
    private String text;
    private int count;

    public BookWord(String wordText) {
        this.text = wordText;
    }

    public String getText() { return text; }

    public int getCount() { return count; }

    public void incrementCount() { count++; }

    public boolean equals(Object wordToCompare) { return text.equals(wordToCompare); }

    /**
     *
     *
     * Reference - https://codeql.github.com/codeql-query-help/java/java-hashing-without-hashcode/
     * @return hash
     */
    public int hashCode()
    {
        int hash = 7;

        for (int i = 0; i < text.length(); i++) {
            hash = 31 * hash + text.charAt(i);
        }

        hash = 31 * hash + count;

        return hash;
    }

    @Override
    public String toString() { return String.format("Word: '%s', Count: %d", text, count); }
}
