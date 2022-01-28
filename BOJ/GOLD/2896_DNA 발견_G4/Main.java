import java.io.*;
import java.util.*;
public class Main {
	
	static final int A = 1;
	static final int B = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		int dp[][] = new int[str.length()][2];
		
		// A = 0 
		// B = 1
		
		if(str.charAt(0) == 'A') {
			dp[0][B] = 1;
		}
		else dp[0][A] = 1;
		
		for(int i=1; i<str.length(); i++) {
			char ch = str.charAt(i);
			if(ch=='A') {
				dp[i][A] = Math.min(dp[i-1][A], dp[i-1][B]+1);
				dp[i][B] = Math.min(dp[i-1][A]+1, dp[i-1][B]+1);
			}
			else {
				// ch== 'B'
				dp[i][A] = Math.min(dp[i-1][A]+1, dp[i-1][B]+1);
				dp[i][B] = Math.min(dp[i-1][A]+1, dp[i-1][B]);
			}
		}
		int ans = Math.min(dp[n-1][A], dp[n-1][B]+1);
		System.out.println(ans);
	}
 }
