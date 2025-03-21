import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[] picture1Arr = new int[N*N];
        int[] picture2Arr = new int[N*N];
        for (int i = 0; i < N; i++) {
            char[] input = reader.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                int idx = (i*N)+j;
                if (input[j] == 'R') {
                    picture1Arr[idx] = 1;
                    picture2Arr[idx] = 1;
                } else if (input[j] == 'G') {
                    picture1Arr[idx] = 2;
                    picture2Arr[idx] = 1;
                } else if (input[j] == 'B') {
                    picture1Arr[idx] = 3;
                    picture2Arr[idx] = 3;
                }
            }
        }
        Picture picture1 = new Picture(picture1Arr, N, N);
        Picture picture2 = new Picture(picture2Arr, N, N);

        System.out.println(picture1.count() + " " + picture2.count());

    }
}

class Picture {
    int[] parent;
    int[] rank;

    public Picture(int[] picture, int x, int y) {
        parent = new int[picture.length];
        rank = new int[picture.length];
        for (int i = 0; i < picture.length; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        for (int i = 0; i < picture.length-1; i++) {
            if ((i+1)%y == 0) {
                if (picture[i] == picture[i+y]) {
                    union(i, i+y);
                }
            } else if (i > (x-1)*y-1) {
                if (picture[i] == picture[i+1]) {
                    union(i, i+1);
                }
            } else {
                if (picture[i] == picture[i+y]) {
                    union(i, i+y);
                }
                if (picture[i] == picture[i+1]) {
                    union(i, i+1);
                }
            }
        }
    }

    private int find(int x) {
        if (x == parent[x]) {
            return x;
        } else {
            return find(parent[x]);
        }
    }

    private void union(int x, int y) {
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
            rank[y]++;
        }
    }

    public int count() {
        int count = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == i) {
                count++;
            }
        }
        return count;
    }
    
}
