import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

class Main {

    static int answer = 0;
    static int[][] board;
    static boolean[] visited = new boolean[26];
    static int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int R;
    static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        board = new int[R][C];
        for (int i = 0; i < R; i++) {
            char[] line = reader.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                board[i][j] = line[j] - 'A';
            }
        }

        recursive(0, 0, 1);

        System.out.println(answer);
    }

    public static void recursive(int x, int y, int len) {
        visited[board[x][y]] = true;
        answer = Math.max(answer, len);

        for (int i = 0; i < move.length; i++) {
            int tempX = x + move[i][0];
            int tempY = y + move[i][1];
            if (tempX >= 0 && tempY >= 0 && tempX < R && tempY < C) {
                if (!visited[board[tempX][tempY]]) {
                    recursive(tempX, tempY, len + 1);
                    visited[board[tempX][tempY]] = false;
                }
            }
        }

    }
}
