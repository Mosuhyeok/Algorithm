import java.util.*;
import java.io.*;

class Main{
	static int n;
	static char map[][];
	static int cnt = 0;
	static int cnt2 = 0;
	static boolean visited[][];
	static int dx[] = {0,0,1,-1};
	static int dy[] = {1,-1,0,0};
	public static void main(String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		n = Integer.parseInt(br.readLine());
		
		map = new char[n][n];
		
		for(int i=0; i<n; i++) {
			String str = br.readLine();
			for(int j=0; j<n; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		visited = new boolean[n][n];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(!visited[i][j]) {
					bfs(i,j,map[i][j]);
					cnt++;
				}
			}
		}
		
		visited = new boolean[n][n];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(!visited[i][j]) {
					bfs2(i,j,map[i][j]);
					cnt2++;
				}
			}
		}
		System.out.println(cnt+" "+cnt2);
	}
	public static void bfs(int x, int y, char ch) {
		Queue<Node>q = new LinkedList<>();
		visited[x][y] = true;
		q.add(new Node(x,y));
		
		while(!q.isEmpty()) {
			Node a = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = a.x+dx[i];
				int ny = a.y+dy[i];
				
				if(isRange(nx,ny) && !visited[nx][ny] && map[nx][ny]==ch) {
					visited[nx][ny] = true;
					q.add(new Node(nx,ny));
				}
			}
		}
	}
	public static void bfs2(int x, int y, char ch) {
		Queue<Node>q = new LinkedList<>();
		visited[x][y] = true;
		q.add(new Node(x,y));
		
		while(!q.isEmpty()) {
			Node a = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = a.x+dx[i];
				int ny = a.y+dy[i];
				
				if(isRange(nx,ny) && !visited[nx][ny]) {
					if(ch =='R' || ch=='G') {
						if(map[nx][ny]=='R' || map[nx][ny]=='G') {
							visited[nx][ny] = true;
							q.add(new Node(nx,ny));		
						}
					}
					else {
						if(map[nx][ny]==ch) {
							visited[nx][ny] = true;
							q.add(new Node(nx,ny));
						}
					}
				}
			}
		}
	}
	public static boolean isRange(int x, int y) {
		if(x>=0 && y>=0 && x<n && y<n) return true;
		return false;
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
