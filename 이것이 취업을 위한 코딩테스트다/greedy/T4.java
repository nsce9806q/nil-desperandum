package greedy;

public class T4 {
    public static void main(String[] args) {
        int n = 25;
        int k = 3;
        T4Solution solution = new T4Solution();
        System.out.println(solution.solution(n, k));
    }
}

class T4Solution {
    public int solution(int n, int k) {
        int answer = 0;
        while (n > 1) {
            answer++;
            if (n % k == 0) {
                n /= k;
            } else {
                n -= 1;
            }
        }
        return answer;
    }
}
