import java.io.*;
import java.util.*;
public class Main {

	static int n,m;
	static int map[][];
	static int min = Integer.MAX_VALUE;
	static ArrayList<Node>list = new ArrayList<>();
	public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
    	StringTokenizer st  =new StringTokenizer(br.readLine());
    	
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	map = new int[n][m];
    	
    	for(int i=0; i<n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0; j<m; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    			if(map[i][j]!=0 && map[i][j]!=6) list.add(new Node(i,j,map[i][j]));
    		}
    	}
    	dfs(0,map);
    	System.out.println(min);
    	
	}
	public static void dfs(int level, int copyMap[][]) {
		if(level == list.size()) {
			int cnt =0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(copyMap[i][j]==0) cnt++;
				}
			}
			min = Math.min(min, cnt);
			return ;
		}
		Node camera = list.get(level);
		int num = camera.num;
		int x = camera.x;
		int y = camera.y;
		

		
		// 1번 카메라
		if(num ==1) {
			// 오른쪽으로 쭉 탐색
			int tmp[][] = setting(copyMap);
			while(isRange(x,y+1) && copyMap[x][y+1]!=6) {
				y++;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			dfs(level+1,tmp);
			
			// 위쪽으로 쭉 탐색
			x = camera.x;
			y = camera.y;
			tmp = setting(copyMap);
			while(isRange(x-1,y) && copyMap[x-1][y]!=6) {
				x--;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			dfs(level+1,tmp);
			
			// 왼쪽으로 쭉 탐색
			x = camera.x;
			y = camera.y;
			tmp = setting(copyMap);
			while(isRange(x,y-1) && copyMap[x][y-1]!=6) {
				y--;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			dfs(level+1,tmp);
			
			// 아래쪽 쭉 탐색
			x = camera.x;
			y = camera.y;
			tmp = setting(copyMap);
			while(isRange(x+1,y) && copyMap[x+1][y]!=6) {
				x++;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			dfs(level+1,tmp);
		}
		if(num ==2) {
			int tmp[][] = setting(copyMap);
			
			// 양옆
			// 1)왼쪽으로 쭉 탐색
			x = camera.x;
			y = camera.y;
			while(isRange(x,y-1) && copyMap[x][y-1]!=6) {
				y--;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			// 2) 오른쪽으로 쭉 탐색
			x = camera.x;
			y = camera.y;
			while(isRange(x,y+1) && copyMap[x][y+1]!=6) {
				y++;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			
			dfs(level+1, tmp);
			
			
			// 위아래
			x = camera.x;
			y = camera.y;
			tmp = setting(copyMap);
			while(isRange(x-1,y) && copyMap[x-1][y]!=6) {
				x--;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			
			x = camera.x;
			y = camera.y;
			
			while(isRange(x+1,y) && copyMap[x+1][y]!=6) {
				x++;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			dfs(level+1, tmp);
		}
		if(num ==3) {
			// 위 , 오른쪽
			int tmp[][] = setting(copyMap);
			x = camera.x;
			y = camera.y;
			tmp = setting(copyMap);
			while(isRange(x-1,y) && copyMap[x-1][y]!=6) {
				x--;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			x = camera.x;
			y = camera.y;
			while(isRange(x,y+1) && copyMap[x][y+1]!=6) {
				y++;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			
			dfs(level+1, tmp);
			
			// 위 , 왼쪽
			tmp = setting(copyMap);
			x = camera.x;
			y = camera.y;

			while(isRange(x-1,y) && copyMap[x-1][y]!=6) {
				x--;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			x = camera.x;
			y = camera.y;
	
			while(isRange(x,y-1) && copyMap[x][y-1]!=6) {
				y--;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}

			dfs(level+1, tmp);
			
			// 오른쪽 아래
			tmp = setting(copyMap);
			x = camera.x;
			y = camera.y;
			while(isRange(x,y+1) && copyMap[x][y+1]!=6) {
				y++;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			
			x = camera.x;
			y = camera.y;
			while(isRange(x+1,y) && copyMap[x+1][y]!=6) {
				x++;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			dfs(level+1,tmp);
			
			// 왼쪽 아래
			tmp = setting(copyMap);
			x = camera.x;
			y = camera.y;
			tmp = setting(copyMap);
			while(isRange(x,y-1) && copyMap[x][y-1]!=6) {
				y--;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			
			x = camera.x;
			y = camera.y;
			while(isRange(x+1,y) && copyMap[x+1][y]!=6) {
				x++;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			dfs(level+1,tmp);
			
		}
		if(num ==4) {
			// 위 , 오른 , 왼
			int [][] tmp = setting(copyMap);
			
			x = camera.x;
			y = camera.y;
			while(isRange(x-1,y) && copyMap[x-1][y]!=6) {
				x--;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			
			x = camera.x;
			y = camera.y;
			while(isRange(x,y+1) && copyMap[x][y+1]!=6) {
				y++;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			
			x = camera.x;
			y = camera.y;
			while(isRange(x,y-1) && copyMap[x][y-1]!=6) {
				y--;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			
			dfs(level+1,tmp);
			tmp = setting(copyMap);
			// 위 왼 아래
			x = camera.x;
			y = camera.y;
			while(isRange(x-1,y) && copyMap[x-1][y]!=6) {
				x--;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			
			x = camera.x;
			y = camera.y;
			while(isRange(x,y-1) && copyMap[x][y-1]!=6) {
				y--;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			
			x = camera.x;
			y = camera.y;
			while(isRange(x+1,y) && copyMap[x+1][y]!=6) {
				x++;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			dfs(level+1,tmp);
			
			// 왼,오,아래
			tmp = setting(copyMap);
			
			x = camera.x;
			y = camera.y;
			while(isRange(x,y-1) && copyMap[x][y-1]!=6) {
				y--;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			
			x = camera.x;
			y = camera.y;
			while(isRange(x,y+1) && copyMap[x][y+1]!=6) {
				y++;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			x = camera.x;
			y = camera.y;
			while(isRange(x+1,y) && copyMap[x+1][y]!=6) {
				x++;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			dfs(level+1,tmp);
			
			// 위 아 오른
			tmp = setting(copyMap);
			x = camera.x;
			y = camera.y;
			while(isRange(x-1,y) && copyMap[x-1][y]!=6) {
				x--;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			x = camera.x;
			y = camera.y;
			while(isRange(x+1,y) && copyMap[x+1][y]!=6) {
				x++;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			x = camera.x;
			y = camera.y;
			while(isRange(x,y+1) && copyMap[x][y+1]!=6) {
				y++;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			dfs(level+1,tmp);
			
		}
		if(num ==5) {
			int tmp[][] = setting(copyMap);
			// 오른쪽으로 쭉 탐색
			x = camera.x;
			y = camera.y;
			while(isRange(x,y+1) && copyMap[x][y+1]!=6) {
				y++;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
		
			
			// 위쪽으로 쭉 탐색
			x = camera.x;
			y = camera.y;
			while(isRange(x-1,y) && copyMap[x-1][y]!=6) {
				x--;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
		
			
			// 왼쪽으로 쭉 탐색
			x = camera.x;
			y = camera.y;
			while(isRange(x,y-1) && copyMap[x][y-1]!=6) {
				y--;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
		
			
			// 아래쪽 쭉 탐색
			x = camera.x;
			y = camera.y;
			while(isRange(x+1,y) && copyMap[x+1][y]!=6) {
				x++;
				if(isSkip(x,y,copyMap)) continue;	// 카메라
				tmp[x][y] = -1;
			}
			dfs(level+1,tmp);
		}
	}
	public static int[][] setting(int [][] copyMap){
		int tmp[][] = new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				tmp[i][j] = copyMap[i][j];
			}
		}
		return tmp;
	}
	public static boolean isSkip(int x, int y, int copyMap[][]) {
		
		if(copyMap[x][y] ==1 || copyMap[x][y]==2 || copyMap[x][y]==3 || copyMap[x][y]==4 || copyMap[x][y]==5) return true;
		return false;
		
	}
	public static boolean isRange(int x, int y) {
		if(x>=0 && y>=0 && x<n && y<m) return true;
		return false;
	}
}
class Node{
	int x,y,num;

	public Node(int x, int y, int num) {
		super();
		this.x = x;
		this.y = y;
		this.num = num;
	}
	
}