import java.lang.Math;
import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        long[] arr = new long[bans.length];
        
        for (int i=0; i<bans.length; i++) {
            arr[i] = getOrder(bans[i]);
        }
        
        Arrays.sort(arr);
        
        for (Long item: arr) {
            if (item <= n) {
                n++;
            }
        }
        return getSpell(n-1);
    }
    
    String getSpell(long n) {
        String spell = "";

        while (n >= 0) {
            spell = (char) ('a' + (n % 26)) + spell;
            n = n / 26 - 1;
            if (n < 0) break;
        }

        return spell;
    }
    
    long getOrder(String spell) {
        long order = 0;
        char[] spellArr = spell.toCharArray();
        int n = spellArr.length;
        for (int i=0; i<n; i++) {
            char temp = spellArr[i];
            order += ((int) temp - 96) * (long) Math.pow(26, n-i-1);
        }
        return order;
    }
}