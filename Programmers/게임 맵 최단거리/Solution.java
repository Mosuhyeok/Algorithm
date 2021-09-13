import java.util.*;
class Solution {
	

	static int dx [] = {0,0,1,-1};
	static int dy [] = {1,-1,0,0};
	public static void main(String [] args) {
    	
		int a[][]= {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}};
    
		int b = solution(a);
		System.out.println(b);
	}
    public static int solution(int[][] maps) {

        boolean visited [][] = new boolean[maps.length][maps[0].length];
        Queue<Node>q = new LinkedList<>();
        q.add(new Node(0,0,1));
        visited[0][0]= true;
        
        while(!q.isEmpty()) {
        	Node a=  q.poll();
        	
        	if(a.x == maps.length-1 && a.y== maps[0].length-1) {
        		return a.cnt;
        	}
        	
        	
        	for(int i=0; i<4; i++) {
        		int nx = a.x+dx[i];
        		int ny = a.y+dy[i];
        		if(isRange(nx,ny,maps.length,maps[0].length) && maps[nx][ny]!=0 && !visited[nx][ny]) {
        			q.add(new Node(nx,ny,a.cnt+1));
        			visited[nx][ny] = true;
        		}
        	}
        }
        
        
        return -1;
    }
    public static boolean isRange(int x, int y, int n, int m) {
    	if(x>=0 && y>=0 && x<n && y<m) {
    		return true;
    	}
    	return false;
    }
}
class Node{
	int x,y,cnt;
	
	Node(int x, int y, int cnt){
		this.x=x;
		this.y=y;
		this.cnt = cnt;
	}
}