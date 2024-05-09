public class ThreadRunner extends Thread {

    private final String text;
    private String mostFrequentWord;
    private int frequency;
    private String longestWord;
    private double hackCode;
    private int wordCount;

    ThreadRunner(String text){
        this.text = text;
    }

    public void run(){
        WordCount wordCount = new WordCount(text);
        MostFrequentWord mostFrequentWord = new MostFrequentWord(text);
        LongestWord longestWord = new LongestWord(text);
        HackCode hackCode = new HackCode(text);

        wordCount.start();
        mostFrequentWord.start();
        longestWord.start();
        hackCode.start();

        try {
            wordCount.join();
            mostFrequentWord.join();
            longestWord.join();
            hackCode.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.wordCount = wordCount.getWordCount();
        this.mostFrequentWord = mostFrequentWord.getMostFrequentWord();
        this.frequency = mostFrequentWord.getFrequency();
        this.longestWord = longestWord.getLongestWord();
        this.hackCode = hackCode.getHackCode();
    }
}
