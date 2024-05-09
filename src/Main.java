import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        //Input:
        final String text;
        final int THREAD_COUNT;
        try {
            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            THREAD_COUNT = Integer.parseInt(bufferedReader.readLine());
            String line;
            while (true) {
                line = bufferedReader.readLine();
                if (line.equals("!end"))
                    break;
                builder.append(line).append("\n");
            }
            text = builder.substring(0, builder.length() - 1);
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        final long startTime = System.nanoTime();
        //Process:
        //Write your code here

        WordCount wordCount = new WordCount(text);
        wordCount.start();
        try {
            wordCount.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LongestWord longestWord = new LongestWord(text);
        longestWord.start();
        try {
            longestWord.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        MostFrequentWord mostFrequentWord = new MostFrequentWord(text);
        mostFrequentWord.start();
        try {
            mostFrequentWord.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        HackCode hackCode = new HackCode(text);
        hackCode.start();
        try {
            hackCode.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int N = 4; // replace with the number of threads you want

        ExecutorService executor = Executors.newFixedThreadPool(N);

        Future<?> wordCountFuture = executor.submit(new WordCount(text));
        Future<?> longestWordFuture = executor.submit(new LongestWord(text));
        Future<?> mostFrequentWordFuture = executor.submit(new MostFrequentWord(text));
        Future<?> hackCodeFuture = executor.submit(new HackCode(text));

        try {
            wordCountFuture.get();
            longestWordFuture.get();
            mostFrequentWordFuture.get();
            hackCodeFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        executor.shutdown();

        final long elapsedMillis = (System.nanoTime() - startTime) / 1000000;
        //Output:
        //Print the results here
        System.out.println("Word count: " + wordCount.getWordCount());
        System.out.println("The longest word is \"" + longestWord.getLongestWord() + "\" with a length of " + longestWord.getLongestWord().length() + ".");
        System.out.println("The most frequent word is \"" + mostFrequentWord.getMostFrequentWord() + "\" with " + mostFrequentWord.getFrequency() + " appearances.");
        System.out.printf("HackCode: %.3f" , hackCode.calculateHackCode(text));
    }
}
