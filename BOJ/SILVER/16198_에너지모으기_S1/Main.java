import java.util.*;
import java.io.*;

class Main{
	static long max =0 ;
	static int n;
	public static void main(String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		ArrayList<Integer>list = new ArrayList<>();
		
		StringTokenizer st  = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		dfs(0,list,0);
		System.out.println(max);
	}
	public static void dfs(int level, ArrayList<Integer>list, long sum) {
		
		
		if(list.size()==2) {
			max = Math.max(max, sum);
			return ;
		}
		
		for(int i=1; i<list.size()-1; i++) {
			
			ArrayList<Integer>tmp = copy(list);
			int tmp_sum = tmp.get(i-1)*tmp.get(i+1);
			tmp.remove(i);
			dfs(level+1,tmp,sum+tmp_sum);
		}
		
	}
	public static ArrayList<Integer>copy(ArrayList<Integer>list){
		ArrayList<Integer>tmp = new ArrayList<>();
		
		for(int i=0; i<list.size(); i++) {
			tmp.add(list.get(i));
		}
		return tmp;
	}
}
