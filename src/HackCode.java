public class HackCode extends Thread {
    private final String text;
    private double hackCode;

    HackCode(String text) {
        this.text = text;
    }
    public double calculateHackCode(String s) {
        double a = 0.99998;
        double hackCode = 0;
        int k = s.length();
        for (int i = 0; i < k; i++) {
            hackCode += s.charAt(i) * Math.pow(a, k - i - 1);
        }
        return hackCode;
    }

    public void run() {
        hackCode = calculateHackCode(text);
    }

    public double getHackCode() {
        return hackCode;
    }
}
