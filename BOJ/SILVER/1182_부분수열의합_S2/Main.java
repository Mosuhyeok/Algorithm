import java.util.*;
import java.io.*;

class Main{
	
	static int n,s;
	static ArrayList<Integer> list;
	static int ans =0;
	public static void main(String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		list = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		dfs(0,0,false);
		System.out.println(ans);
	}
	public static void dfs(int level, int sum, boolean flag) {
	
		
		if(level == n) {
			if(sum==s && flag) {
				ans++;
			}
			return ;
		}
		
		dfs(level+1,sum+list.get(level),true);
		dfs(level+1,sum,flag);
	}
}
