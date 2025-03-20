import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

class Main {

    static int[] parent;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(reader.readLine());

        for (int test  = 0; test < T; test++) {
            String[] input = reader.readLine().split(" ");
            int H = Integer.parseInt(input[0]);
            int W = Integer.parseInt(input[1]);

            parent = new int[H*W];
            rank = new int[H*W];
            int[] grid = new int[H*W];
            for (int i = 0; i < H*W; i++) {
                parent[i] = i;
                rank[i] = 0;
            }

            for (int i = 0; i < H; i++) {
                char[] line = reader.readLine().toCharArray();

                for (int j = 0; j < W; j++) {
                    int idx = get1DIndex(i, j, W);
                    if (line[j] == '#') {
                        grid[idx] = 1;
                    } else if (line[j] == '.') {
                        grid[idx] = 0;
                    }
                }
            }

            for (int i = 0; i < H*W-1; i++) {
                if (grid[i] == 1) {
                    if (i > (H-1)*W-1) {
                        if (grid[i+1] == 1) {
                            union(i, i+1);
                        }
                    } else if ((i+1)%W == 0) {
                        if (grid[i+W] == 1) {
                            union(i, i+W);
                        }
                    } else {
                        if (grid[i+1] == 1) {
                            union(i, i+1);
                        }
                        if (grid[i+W] == 1) {
                            union(i, i+W);
                        }
                    }
                }
            }

            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < H*W; i++) {
                if (grid[i] == 1) {
                    set.add(find(parent[i]));
                }
            }

            System.out.println(set.size());
        }
    }

    public static int get1DIndex(int i, int j, int n) {
        return (n * i) + j;
    }

    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            return find(parent[x]);
        }
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) {
            return;
        }

        if (rank[x] < rank[y]) {
            parent[x] = y;
        } else if (rank[x] > rank[y]) {
            parent[y] = x;
        } else {
            parent[x] = y;
            rank[x]++;
        }

    }
}
