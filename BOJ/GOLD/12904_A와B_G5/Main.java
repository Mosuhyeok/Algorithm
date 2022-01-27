import java.io.*;
import java.util.*;
public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String ss[] = br.readLine().split("");
		String tt[]= br.readLine().split("");
		
		ArrayList<String> s = new ArrayList<>();
		ArrayList<String>t = new ArrayList<>();
		
		for(int i=0; i<ss.length; i++) {
			s.add(ss[i]);
		}
		for(int i=0; i<tt.length; i++) {
			t.add(tt[i]);
		}
		
		int len = t.size();
		
		while(len != s.size()) {
			String tmp = t.get(len-1);
			if(tmp.equals("A")) {
				t.remove(len-1);
				len--;
				continue;
			}
			t.remove(len-1);
			len--;
			Collections.reverse(t);
		}
		
		for(int i=0; i<s.size(); i++) {
			if(!s.get(i).equals(t.get(i))) {
				System.out.println(0);
				System.exit(0);
			}
		}
		System.out.println(1);
		
	}
 }
