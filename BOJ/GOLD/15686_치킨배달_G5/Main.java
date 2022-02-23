package tomyself;


import java.io.*;
import java.util.*;
public class Main {
	static int n,m;
	static int map[][];
	static int visited[];
	static int min = Integer.MAX_VALUE;
	static ArrayList<Node>list = new ArrayList<>();
	public static void main(String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());	// 최대수익 M개
		
		map = new int[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) list.add(new Node(i,j));
			}
		}
		visited = new int[m];
		
		dfs(0,0);
		System.out.println(min);
	}
	public static void dfs(int level, int now) {
		if(level ==m) {
			int sum =0;
			ArrayList<Node>tmp = new ArrayList<>();
			
			for(int i=0; i<level; i++) {
				tmp.add(list.get(visited[i]));
			}
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					int dist = Integer.MAX_VALUE;
					if(map[i][j]==1) {
						
						
						for(int k=0; k<m; k++) {
							
							int chick_dist = Math.abs(i-tmp.get(k).x) + Math.abs(j-tmp.get(k).y);
							dist = Math.min(dist, chick_dist);
						}
						sum+=dist;
					}
				}
			}
			
			min = Math.min(min, sum);
			return ;
		}
		
		for(int i=now; i<list.size(); i++) {
			visited[level] = i;
			dfs(level+1,i+1);
		}
	}
 }
class Node{
	int x,y;

	public Node(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}