import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int countFive(long n) {
        int count = 0;
        while (n > 0) {
            if (n % 10 == 5) {
                count++;
            }
            n /= 10;
        }
        return count;
    }

    static long recursive(long n, int k) {
        if (k <= countFive(n)) {
            return n;
        }
        long temp = n % 10;
        n /= 10;
        if (temp > 5) {
            n++;
            if (k <= countFive(n)) {
                return (n) * 10;
            }
        }
        return recursive(n, k-1) * 10 + 5;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        long n = Long.parseLong(input[0]);
        int k = Integer.parseInt(input[1]);

        System.out.println(recursive(n+1, k));
    }

}