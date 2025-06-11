package greedy;

import java.util.Arrays;

public class T2 {
    public static void main(String[] args) {
        int n = 5;
        int m = 8;
        int k = 3;
        int[] arr = {2, 4, 5, 4, 6};
        T2Solution solution = new T2Solution();
        System.out.println(solution.solution(n, m, k, arr));
    }
}

class T2Solution {
    public int solution(int n, int m, int k, int[] arr) {
        Arrays.sort(arr);
        int answer = 0;
        int tempK = k;
        while (m > 0) {
            m--;
            if (tempK-- > 0) {
                answer += arr[n-1];
            } else {
                answer += arr[n-2];
                tempK = k;
            }
        }
        return answer;
    }
}
