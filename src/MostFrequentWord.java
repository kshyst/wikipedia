import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MostFrequentWord extends Thread{
    private final String text;
    private String mostFrequentWord;
    private int frequency;

    MostFrequentWord(String text){
        this.text = text;
    }
    public String getMostFrequentWord(String text) {
        Map<String, Integer> wordCounts = new HashMap<>();
        StringBuilder word = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                word.append(Character.toLowerCase(c));
            } else if (word.length() > 0) {
                String finalWord = word.toString();
                wordCounts.put(finalWord, wordCounts.getOrDefault(finalWord, 0) + 1);
                word = new StringBuilder();
            }
        }

        if (word.length() > 0) {
            String finalWord = word.toString();
            wordCounts.put(finalWord, wordCounts.getOrDefault(finalWord, 0) + 1);
        }

        frequency = Collections.max(wordCounts.values());
        return Collections.max(wordCounts.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public void run(){
        mostFrequentWord = getMostFrequentWord(text);
    }

    public String getMostFrequentWord(){
        return mostFrequentWord;
    }

    public int getFrequency(){
        return frequency;
    }
}
