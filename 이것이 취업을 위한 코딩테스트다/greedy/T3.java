package greedy;

import java.util.Arrays;

public class T3 {
    public static void main(String[] args) {
        int[][] arr1 = {{3,1,2},{4,1,4},{2,2,2}};
        int[][] arr2 = {{7,3,1,8},{3,3,3,4}};
        T3Solution solution = new T3Solution();
        System.out.println(solution.solution(3, 3, arr1));
        System.out.println(solution.solution(2, 4, arr2));
    }
}

class T3Solution {
    public int solution(int n, int m, int[][] arr) {
        int[] minArr = new int[n];
        for (int i = 0; i < n; i++) {
            minArr[i] = Arrays.stream(arr[i]).min().getAsInt();
        }
        return Arrays.stream(minArr).max().getAsInt();
    }
}
