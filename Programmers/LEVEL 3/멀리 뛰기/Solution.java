import java.util.*;
class Solution {

	static long answer;
    public static void main(String [] args) {
    	
    	long a = solution(2000);
    	long b = solution(1999);
    	System.out.println(b);
    	System.out.println(a);
    }
    public static long solution(int n) {
    	answer = 0;
        long dp[] = new long[2001];
        
        dp[1] = 1;
        dp[2] = 2;
        
        
        if(n<3) {
        	return dp[n];
        }
        
        
        for(int i=3; i<=2000; i++) {
        	dp[i] = (dp[i-1]+dp[i-2])%1234567;
        }
        
        return dp[n];
    }

}

