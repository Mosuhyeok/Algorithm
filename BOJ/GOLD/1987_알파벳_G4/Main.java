import java.io.*;
import java.util.*;
public class Main {

	static int dx [] = {0,0,1,-1};
	static int dy [] = {1,-1,0,0};
	static boolean visited [][];
	static boolean Alpha[] = new boolean[27];
	static char map[][];
	static int r,c;
	static int max = 0;
	public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	StringTokenizer st  =new StringTokenizer(br.readLine()," ");
    	r = Integer.parseInt(st.nextToken());
    	c = Integer.parseInt(st.nextToken());
    	map = new char[r][c];
    	visited = new boolean[r][c];

    	for(int i=0; i<r; i++) {
    		String str = br.readLine();
    		for(int j=0; j<c; j++) {
    			map[i][j]= str.charAt(j);
    		}
    	}
    	visited[0][0] = true;
    	Alpha[map[0][0] -'A'] = true;
    	dfs(0,0,1);
    	
    	System.out.println(max);
    	
    	
	}
	public static void dfs(int x, int y, int cnt) {


		max = Math.max(max, cnt);
		
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(isRange(nx,ny) && !visited[nx][ny] && !Alpha[map[nx][ny]-'A']) {
				visited[nx][ny] = true;
				Alpha[map[nx][ny]-'A'] = true;
				dfs(nx,ny,cnt+1);
				visited[nx][ny] = false;
				Alpha[map[nx][ny]-'A'] = false;
			
			}
		}
	}
	public static boolean isRange(int x, int y) {
		if(x>=0 && y>=0 && x<r && y<c) return true;
		return false;
	}
}
