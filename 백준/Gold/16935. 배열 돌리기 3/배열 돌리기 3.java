import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n, m;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        int r = Integer.parseInt(input[2]);

        matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            input = reader.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                matrix[i][j] = Integer.parseInt(input[j]);
            }
        }

        input = reader.readLine().split(" ");
        for (int i = 0; i < r; i++) {
            switch (Integer.parseInt(input[i])) {
                case 1: matrix = one(); break;
                case 2: matrix = two(); break;
                case 3: matrix = three(); break;
                case 4: matrix = four(); break;
                case 5: matrix = five(); break;
                case 6: matrix = six(); break;
            }
        }

        print();
    }

    static int[][] one() {
        int[][] newMatrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newMatrix[n-i-1][j] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    static int[][] two() {
        int[][] newMatrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newMatrix[i][m-j-1] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    static int[][] three() {
        int[][] newMatrix = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newMatrix[j][n-i-1] = matrix[i][j];
            }
        }
        int temp = n;
        n = m;
        m = temp;
        return newMatrix;
    }

    static int[][] four() {
        int[][] newMatrix = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newMatrix[m-j-1][i] = matrix[i][j];
            }
        }
        int temp = n;
        n = m;
        m = temp;
        return newMatrix;
    }

    static int[][] five() {
        int[][] newMatrix = new int[n][m];
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < m/2; j++) {
                newMatrix[i][j+m/2] = matrix[i][j];
            }
        }
        for (int i = 0; i < n/2; i++) {
            for (int j = m/2; j < m; j++) {
                newMatrix[i+n/2][j] = matrix[i][j];
            }
        }
        for (int i = n/2; i < n; i++) {
            for (int j = 0; j < m/2; j++) {
                newMatrix[i-n/2][j] = matrix[i][j];
            }
        }
        for (int i = n/2; i < n; i++) {
            for (int j = m/2; j < m; j++) {
                newMatrix[i][j-m/2] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    static int[][] six() {
        int[][] newMatrix = new int[n][m];
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < m/2; j++) {
                newMatrix[i+n/2][j] = matrix[i][j];
            }
        }
        for (int i = 0; i < n/2; i++) {
            for (int j = m/2; j < m; j++) {
                newMatrix[i][j-m/2] = matrix[i][j];
            }
        }
        for (int i = n/2; i < n; i++) {
            for (int j = 0; j < m/2; j++) {
                newMatrix[i][j+m/2] = matrix[i][j];
            }
        }
        for (int i = n/2; i < n; i++) {
            for (int j = m/2; j < m; j++) {
                newMatrix[i-n/2][j] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    static void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}