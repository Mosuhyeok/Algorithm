import java.util.*;
import java.io.*;

class Main{
	
	static int n,m;
	static char map[][];
	static int dx [] = {0,0,1,-1};
	static int dy [] = {1,-1,0,0};
	static int min = Integer.MAX_VALUE;
	public static void main(String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		Node one = new Node();
		Node two = new Node();
		boolean flag = true;
		for(int i=0; i<n; i++) {
			String input = br.readLine();
			for(int j=0; j<m; j++) {
				map[i][j]= input.charAt(j);
				
				if(map[i][j]=='o') {
					if(flag) {
						one = new Node(i,j,false);
						flag = false;
					}
					else {
						two = new Node(i,j,false);
					}
				}
			}
		}
		dfs(0,one,two);
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(min);
		}
		
	}
	public static void dfs(int level, Node one, Node two) {
		
		if(level ==11) return;
		
		if(one.drop && two.drop) return; 	// 둘다 떨어진경우
		
		if(one.drop || two.drop) {
			min = Math.min(min, level);
			return ;
		}
		for(int i=0; i<4 ;i++) {
		
			int nx = one.x+dx[i];
			int ny = one.y+dy[i];

			Node tmp_one = new Node();
			// 첫번째 동전이 맵 밖으로 떨어진경우
			if(!isRange(nx,ny)) {
				tmp_one = new Node(nx,ny,true);
			}
			
			// 첫번째 동전이 이동하는 경우
			else if(map[nx][ny]!='#') {
				tmp_one = new Node(nx,ny,false);
			}
			// 벽에 막혀 움직이지 않는 경우
			else {
				tmp_one = new Node(one.x,one.y,one.drop);
			}
			
			nx = two.x+dx[i];
			ny = two.y+dy[i];
			Node tmp_two = new Node();
			// 두번째 동전이 맵 밖으로 떨어진경우
			if(!isRange(nx,ny)) {
				tmp_two = new Node(nx,ny,true);
			}
			
			// 두번째 동전이 이동하는 경우
			else if(map[nx][ny]!='#') {
				tmp_two = new Node(nx,ny,false);
			}
			else {
				tmp_two = new Node(two.x,two.y,two.drop);
			}
			
			dfs(level+1,tmp_one,tmp_two);
			
		}
		
	}
	static boolean isRange(int x, int y) {
		if(x>=0 && y>=0 && x<n && y<m) return true;
		return false;
	}
}
class Node{
	int x,y;
	boolean drop;
	public Node() {};
	public Node(int x, int y, boolean drop) {
		super();
		this.x = x;
		this.y = y;
		this.drop = drop;
	}
	
}