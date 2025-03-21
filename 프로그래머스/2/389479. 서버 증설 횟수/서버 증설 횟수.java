class Solution {
    
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int server[] = new int[24];
        
        for (int i=0; i<24; i++) {
            server[i] = 1;
        }
        
        for (int i=0; i<24; i++) {
            if (players[i] >= server[i] * m) {
                int need = (players[i] / m) - server[i] + 1;
                answer += need;
                for (int j=0; j<k; j++) {
                    int idx = i+j;
                    if (idx < 24) {
                        server[idx] += need; 
                    }
                }
            }
        }
        
        return answer;
    }
}