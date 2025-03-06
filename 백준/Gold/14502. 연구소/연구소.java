import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        int[][] laboratory = new int[n][m];

        for (int i = 0; i < n; i++) {
            input = reader.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                laboratory[i][j] = Integer.parseInt(input[j]);
            }
        }

        int answer = 0;

        for (int i = 0; i < n*m; i++) {
            for (int j = 0; j < n*m; j++) {
                for (int k = 0; k < n*m; k++) {
                    if (i != j && j != k && k != i) {
                        int ix = i / m;
                        int iy = i % m;
                        int jx = j / m;
                        int jy = j % m;
                        int kx = k / m;
                        int ky = k % m;
                        if (laboratory[ix][iy] == 0 && laboratory[jx][jy] == 0 && laboratory[kx][ky] == 0) {
                            int[][] temp = getCopy(laboratory);
                            temp[ix][iy] = 1;
                            temp[jx][jy] = 1;
                            temp[kx][ky] = 1;
                            spreadVirus(temp);
                            int safe = countVirus(temp);
                            if (answer < safe) {
                                answer = safe;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static int[][] getCopy(int[][] laboratory) {
        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copy[i][j] = laboratory[i][j];
            }
        }
        return copy;
    }

    static void spreadVirus(int[][] laboratory) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                recursive(laboratory, i, j);
            }
        }
    }

    static void recursive(int[][] laboratory, int i, int j) {
        if (laboratory[i][j] == 2) {
            if (i > 0) {
                if (laboratory[i - 1][j] == 0) {
                    laboratory[i - 1][j] = 2;
                    recursive(laboratory, i - 1, j);
                }
            }
            if (j > 0) {
                if (laboratory[i][j - 1] == 0) {
                    laboratory[i][j - 1] = 2;
                    recursive(laboratory, i, j - 1);
                }
            }
            if (i < n - 1) {
                if (laboratory[i + 1][j] == 0) {
                    laboratory[i + 1][j] = 2;
                    recursive(laboratory, i + 1, j);
                }
            }
            if (j < m - 1) {
                if (laboratory[i][j + 1] == 0) {
                    laboratory[i][j + 1] = 2;
                    recursive(laboratory, i, j + 1);
                }
            }
        }
    }

    static int countVirus(int[][] laboratory) {
        int safe = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (laboratory[i][j] == 0) {
                    safe++;
                }
            }
        }
        return safe;
    }


}