import java.io.*;
import java.util.*;
public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String [] t = br.readLine().split(" ");
		int n = Integer.parseInt(t[0]);
		int m = Integer.parseInt(t[1]);
		
		HashMap<String,Integer> a = new HashMap<>();
		ArrayList<String> b = new ArrayList<>();
		int ans = 0;
		for(int i=0; i<n; i++) {
			a.put(br.readLine(),1);
		}
		for(int i=0; i<m; i++) {
			b.add(br.readLine());
		}
		
		
		for(String key: b) {
			if(a.containsKey(key)) ans++;
		}
		System.out.println(ans);
		
	}
 }
