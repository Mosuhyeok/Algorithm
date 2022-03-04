import java.util.*;
import java.io.*;

class Main{
	static long max =0 ;
	static int n;
	static boolean visited[][];
	static int dx [] = {-1,-1,1,1};
	static int dy [] = {-1,1,-1,1};
	static int ans =0;
	
	public static void main(String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		visited = new boolean[n][n];
		
		for(int garo=0; garo<n; garo++) {
			visited[0][garo] = true;
			dfs(1);
			visited[0][garo] = false;
		}
		System.out.println(ans);
	}
	public static void dfs(int row) {
		
		if(row == n) {

			ans++;
			return ;
		}
		
		
		for(int garo =0; garo<n; garo++) {
			
			// 세로
			if(seroPossible(garo) && crossPossible(row, garo)) {
				visited[row][garo] = true;
				dfs(row+1);
				visited[row][garo] = false;
			}
		}
		
	}

	public static boolean seroPossible(int x) {
		for(int i=0; i<n; i++) {
			if(visited[i][x]) return false;
		}
		return true;
	}
	public static boolean crossPossible(int x, int y) {
		
		int tmp_x = x;
		int tmp_y = y;
		
		while(isRange(tmp_x+dx[0], tmp_y+dy[0])) {
			tmp_x+=dx[0];
			tmp_y+=dy[0];
			
			if(visited[tmp_x][tmp_y]) return false;
		}
		
		tmp_x = x;
		tmp_y = y;
		while(isRange(tmp_x+dx[1], tmp_y+dy[1])) {
			tmp_x+=dx[1];
			tmp_y+=dy[1];
			
			if(visited[tmp_x][tmp_y]) return false;
		}
		tmp_x = x;
		tmp_y = y;
		while(isRange(tmp_x+dx[2], tmp_y+dy[2])) {
			tmp_x+=dx[2];
			tmp_y+=dy[2];
			
			if(visited[tmp_x][tmp_y]) return false;
		}
		tmp_x = x;
		tmp_y = y;
		while(isRange(tmp_x+dx[3], tmp_y+dy[3])) {
			tmp_x+=dx[3];
			tmp_y+=dy[3];
			
			if(visited[tmp_x][tmp_y]) return false;
		}
		return true;
	}
	public static boolean isRange(int x, int y) {
		if(x>=0 && y>=0 && x<n && y<n) return true;
		return false;
	}
}
