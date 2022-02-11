import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int arr[][];
	static boolean visited[][];
	static int max =0;
	static int dx [] = {0,0,1,-1};
	static int dy [] = {1,-1,0,0};
    public static void main(String[] args) throws Exception{

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	n = Integer.parseInt(br.readLine());
    	arr = new int[n][n];
    	visited= new boolean[n][n];
    	
    	for(int i=0; i<n; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		for(int j=0; j<n; j++) {
    			arr[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	for(int h=0; h<=100; h++) {
    		int cnt = 0;
    		visited = new boolean[n][n];
    		for(int i=0; i<n; i++) {
    			for(int j=0; j<n; j++) {
    				if(!visited[i][j] && arr[i][j]-h>0) {
    					bfs(i,j,h);
    					cnt++;
    				}
    				
    			}
    		}
    		max = Math.max(max, cnt);
    	}
    			
    	System.out.println(max);
    }
   public static void bfs(int x, int y, int h) {
	   Node node = new Node(x,y);
	   
	   Queue<Node> q = new LinkedList<>();
	   q.add(node);
	   visited[x][y] = true;
	   
	   while(!q.isEmpty()) {
		   Node a=  q.poll();
		   
		   for(int i=0; i<4; i++) {
			   int nx = a.x+dx[i];
			   int ny = a.y+dy[i];
			   
			   if(isRange(nx,ny) && !visited[nx][ny] && arr[nx][ny]-h >0) {
				   visited[nx][ny] = true;
				   q.add(new Node(nx,ny));
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
