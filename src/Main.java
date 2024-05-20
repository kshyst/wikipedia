import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        final String text;
        try {
            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
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

        ThreadRunner threadRunner = new ThreadRunner(text);
        threadRunner.run();

        try {
            threadRunner.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        final long elapsedMillis = (System.nanoTime() - startTime) / 1000000;
        System.out.println("Word count: " + ThreadRunner.getWordCount());
        System.out.println("The longest word is \"" + ThreadRunner.getLongestWord() + "\" with a length of " + ThreadRunner.getLongestWord().length() + ".");
        System.out.println("The most frequent word is \"" + ThreadRunner.getMostFrequentWord() + "\" with " + ThreadRunner.getFrequency() + " appearances.");
        System.out.printf("HackCode: %.3f" , ThreadRunner.getHackCode());
    }
}
