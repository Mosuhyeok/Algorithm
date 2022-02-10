import java.io.*;
import java.util.*;
public class Main {
	static ArrayList<Integer>list = new ArrayList<>();
	static int arr[];
	static int n,k;
	static StringBuilder sb = new StringBuilder();
	 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] t = br.readLine().split(" ");
		
		n = Integer.parseInt(t[0]);
		k = Integer.parseInt(t[1]);
		
		arr = new int[n+1];
		
		for(int i=1; i<=n; i++) arr[i] = i;
		int cnt = 0;
		int idx = 0;
		sb.append("<");
		while(cnt!=n) {
			
			if(idx > n) idx= 1;
			
			idx = getIdx(idx);
			cnt++;
		}
		String str = sb.toString();
		
		StringBuilder ans = new StringBuilder();
		
		for(int i=0; i<str.length()-2; i++) {
			ans.append(str.charAt(i));
		}
		
		ans.append(">");
		System.out.println(ans);
		
	}
	public static int getIdx(int idx) {
		int cnt = 0;
		int next = idx+1;
		
		while(cnt!=k) {
			if(next>n) next = 1;
			
			if(arr[next]!=-1) {
				cnt++;
			}
			next++;
		}
		next--;
		sb.append(arr[next]+", ");
		arr[next] = -1;
		return next;
	}
 }
