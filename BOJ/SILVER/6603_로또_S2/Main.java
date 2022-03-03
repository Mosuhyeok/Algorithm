import java.util.*;
import java.io.*;

class Main{
	
	static int n;
	static int arr[];
	static boolean visited[];
	static ArrayList<Integer>list = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			list.clear();	
			n = Integer.parseInt(st.nextToken());
			visited = new boolean[n];
			arr = new int[n];
			if(n==0) break;
			for(int i=0; i<n; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			combination(0,0);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	public static void combination(int level, int now) {
		if(level == 6) {
			for(int i=0; i<6; i++) {
				sb.append(arr[i]+" ");
			}
			sb.append("\n");
			return ;
		}
		
		
		for(int i=now; i<n; i++) {
			arr[level] = list.get(i);
			combination(level+1, i+1);
		}
		
	}
}
