public class WordCount extends Thread {
    private final String text;
    private int wordCount;
    WordCount(String text) {
        this.text = text;
    }

    public static int countWords(String text) {
        int wordCount = 0;
        boolean isWord = false;
        int endOfLine = text.length() - 1;

        for (int i = 0; i < text.length(); i++) {
            // if the char is a letter, isWord = true.
            if (Character.isAlphabetic(text.charAt(i)) && i != endOfLine) {
                isWord = true;
            }
            // if char isn't a letter and there have been letters before (isWord = true), counter goes up.
            else if (!Character.isAlphabetic(text.charAt(i)) && isWord) {
                wordCount++;
                isWord = false;
            }
            // last word of String; if it doesn't end with a non letter, it wouldn't count without this.
            else if (Character.isAlphabetic(text.charAt(i)) && i == endOfLine) {
                wordCount++;
            }
        }
        return wordCount;
    }

    public void run() {
        wordCount = countWords(text);
    }

    public int getWordCount() {
        return wordCount;
    }
}
