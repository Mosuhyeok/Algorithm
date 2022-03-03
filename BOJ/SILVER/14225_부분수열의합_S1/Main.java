import java.util.*;
import java.io.*;

class Main{
	
	static int n;
	static ArrayList<Integer>list;
	static boolean num [] = new boolean[2000001];
	public static void main(String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		n = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<n; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		dfs(0,0);
		
		for(int i=0; i<num.length; i++) {
			if(!num[i]) {
				System.out.println(i);
				System.exit(0);
			}
		}
		
		
	}
	public static void dfs(int level, int sum) {
		num[sum] = true;
		if(level == n) return;
		
		dfs(level+1,sum);
		dfs(level+1,sum+list.get(level));
		
	}
}
