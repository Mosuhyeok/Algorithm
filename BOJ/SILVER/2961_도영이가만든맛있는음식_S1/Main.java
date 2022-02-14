import java.io.*;
import java.util.*;

public class Main {
	static ArrayList<Node> list;
	static int n;
	static int arr[];
	static boolean visited[];
	static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	list = new ArrayList<>();
    	n = Integer.parseInt(br.readLine());
    	arr = new int[n];
    	visited = new boolean[n];
    	for(int i=0; i<n; i++) {
    		String [] t = br.readLine().split(" ");
        	list.add(new Node(Integer.parseInt(t[0]),Integer.parseInt(t[1])));
    	}
    	dfs(0);
    	System.out.println(min);
    }
    public static void dfs(int level) {
    	if(level == n) {
    		int s = 1;
    		int b = 0;
    		boolean flag = false;
    		for(int i=0; i<n; i++) {
    			if(visited[i]) {
    				flag = true;
    				s*=list.get(i).s;
    				b+=list.get(i).b;
    			}
    		}
    		if(flag) min = Math.min(min, Math.abs(s-b));
    		return ;
    	}
    	
    	visited[level] = true;
    	dfs(level+1);
    	visited[level] =  false;
    	dfs(level+1);
    	
    }
}
class Node{
	int s,b;

	public Node(int s, int b) {
		super();
		this.s = s;
		this.b = b;
	}
}	