import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char[] input = reader.readLine().toCharArray();

        int[] convert = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            if (input[i] == '(') {
                convert[i] = 1;
            } else {
                convert[i] = -1;
            }

        }

        int[] pre = new int[input.length];
        int[] post = new int[input.length];

        pre[0] = convert[0];
        for (int i = 1; i < input.length; i++) {
            pre[i] = pre[i-1] + convert[i];
        }

        post[input.length - 1] = convert[input.length - 1];
        for (int i = input.length - 2; i >= 0; i--) {
            post[i] = post[i+1] + convert[i];
        }

        int answer = 0;

        if (pre[input.length - 1] == -2) {
            int idx = 0;
            for (int i = 0; i < input.length; i++) {
                if (pre[i] == -1) {
                    idx = i;
                    break;
                }
            }
            for (int i = 0; i <= idx; i++) {
                if (convert[i] == -1) {
                    answer++;
                }
            }
        } else if (pre[input.length - 1] == 2) {
            int idx = 0;
            for (int i = input.length -1; i >= 0; i--) {
                if (post[i] == 1) {
                    idx = i;
                    break;
                }
            }
            for (int i = idx; i < input.length; i++) {
                if (convert[i] == 1) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

}