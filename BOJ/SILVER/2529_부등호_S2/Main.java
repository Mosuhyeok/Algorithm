import java.util.*;
import java.io.*;

class Main{
	
	static int k;
	static char [] op;
	static boolean visited[];
	static long min = Long.MAX_VALUE;
	static long max = 0;
	static String min_str="";
	static String max_str = "";
	static int arr[];
	public static void main(String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		k = Integer.parseInt(br.readLine());
		op = new char[k];
		arr = new int[k+1];
		visited = new boolean[10];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<k; i++) {
			op[i] = st.nextToken().charAt(0);
		}
		dfs(0);
		
		System.out.println(max_str);
		System.out.println(min_str);
	}
	public static void dfs(int level) {
		if(level ==k+1) {
			
			if(isPossible()) {
				long tmp = 0;
				
				for(int i=0; i<k+1; i++) {
					tmp*=10;
					tmp+=arr[i];
				}
				if(tmp > max) {
					max = tmp;
					max_str = "";
					for(int i=0; i<k+1; i++) {
						max_str+=arr[i];
					}
				}
				if(tmp < min) {
					min = tmp;
					min_str = "";
					for(int i=0; i<k+1; i++) {
						min_str+=arr[i];
					}
				}
			}
			return ;
		}
		
		for(int i=0; i<=9; i++) {
			if(!visited[i]) {
				visited[i] = true;
				arr[level] = i;
				dfs(level+1);
				visited[i] = false;
			}
			
		}
	}
	public static boolean isPossible() {
		
		for(int i=0; i<k; i++) {
			int num1 = arr[i];
			int num2 = arr[i+1];
			
			char ch = op[i];
			if(ch=='<') {
			
				if(num1 > num2) return false;
			}
			else { 
				if(num1 < num2) return false;
			}
		}
		
		return true;
	}
}
