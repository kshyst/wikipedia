public class LongestWord extends Thread{

    private final String text;
    private String longestWord;

    LongestWord(String text){
        this.text = text;
    }

    public static String findLongestWord(String text){
        char[] textInChars = text.toCharArray();
        while (textInChars.length > 0 && !Character.isAlphabetic(textInChars[textInChars.length - 1])) {
            textInChars = text.substring(0, textInChars.length - 1).toCharArray();
        }
        String[] words = new String(textInChars).split("[^a-zA-Z]+");
        String longestWord = "";
        for (String word : words) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }
        return longestWord;
    }

    public void run(){
        longestWord = findLongestWord(text);
    }

    public String getLongestWord(){
        return longestWord;
    }
}
