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
            if (Character.isAlphabetic(text.charAt(i)) && i != endOfLine) {
                isWord = true;
            }
            else if (!Character.isAlphabetic(text.charAt(i)) && isWord) {
                wordCount++;
                isWord = false;
            }
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
