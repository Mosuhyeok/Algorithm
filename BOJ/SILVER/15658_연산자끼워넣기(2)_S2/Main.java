import java.util.*;
import java.io.*;

class Main{
	
	static int n;
	static int num[];
	static int op[];
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	public static void main(String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		n = Integer.parseInt(br.readLine());
		num = new int[n];	// 0 1 2 3 4 5
		op = new int[4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<4; i++) {
			int num = Integer.parseInt(st.nextToken());
			op[i]= num;
		}
		
		dfs(1,num[0]);
		System.out.println(max);
		System.out.println(min);
	}
	public static void dfs(int level, int sum) {
		if(level == n) {
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return ;
		}
		
		for(int i=0; i<4; i++) {
			
			if(op[i] ==0) continue;
			
			if(i==0) {
				op[i]--;
				dfs(level+1,sum+num[level]);
				op[i]++;
			}
			if(i==1) {
				op[i]--;
				dfs(level+1,sum-num[level]);
				op[i]++;
			}
			if(i==2) {
				op[i]--;
				dfs(level+1,sum*num[level]);
				op[i]++;
			}
			if(i==3) {
				op[i]--;
				dfs(level+1,sum/num[level]);
				op[i]++;
			}
		}
	}
}
