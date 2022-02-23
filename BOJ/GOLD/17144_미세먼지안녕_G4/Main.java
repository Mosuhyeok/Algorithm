import java.util.*;
import java.io.*;

class Main{
	static int r,c,t;
	static int map[][];
	static Node up;
	static Node down;
	static int dx[] = {0,0,1,-1};
	static int dy[] = {1,-1,0,0};
	public static void main(String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		// Input 시작
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());	// 시간초
		map = new int[r+1][c+1];
		boolean flag = true;
		for(int i=1; i<=r; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=1; j<=c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j]== -1) {
					// 순서대로 탐색하니 위에있는 공기청정기가 무조건 빨리 나온다
					// 따라서 flag 값을 두어서 위에 공기청정기랑 아래 공기청정기를 구분
					if(flag) {
						up = new Node(i,j);	
						flag = false;
					}
					else {
						down = new Node(i,j);
					}
				}
			}			
		}
		
		// t초동안 돌리기
		while(t-- >0) {
			
			ArrayList<Info>info = new ArrayList<>();	// 미세먼지 확산 정보를 담을 리스트
			
			// r,c에 있는 미세먼지는 인접한 4방향으로 확산된다
			
			for(int i=1; i<=r; i++) {
				for(int j=1; j<=c; j++) {
					int cnt = 0;	// 확산 되는 칸의 개수
					if(map[i][j]!=0 && map[i][j]!=-1) {
						
						for(int k=0; k<4; k++) {
							int nx = i+dx[k];
							int ny = j+dy[k];
							
							// 인접한 칸에 공기청정기가 없고 칸의 범위 안에 있을 경우 확산
							if(isRange(nx,ny) && map[nx][ny]!=-1) {
								cnt++;
								// map[nx][ny] +=map[i][j]/5    -> 하면 안됨
								info.add(new Info(nx,ny,map[i][j]/5));		// 동시에 퍼트려야 하니 저장해둠
							}
						}
						map[i][j] -= (map[i][j]/5)*cnt;	// 확산되고 남은 미세먼지 양
					}
				}
			}
			
			// 동시에 확산되니 info 리스트에 담겨져 있는 확산정보 퍼트리기
			for(int i=0; i<info.size(); i++) {
				Info a = info.get(i);
				map[a.x][a.y]+= a.spread;
			}
			// 2. 공기청정기 작동
			int copy_map[][] = copy();
			
			// 위쪽 공기청정기
			int x = up.x;
			int y = up.y;
			
			// 시계방향  -> 방향
			for(int i=c; i>2; i--) {
				map[x][i] = copy_map[x][i-1];
			}
			map[x][2] = 0;	
			
			// 시계방향  ↑ 방향 
			for(int i=1; i<x; i++) {
				map[i][c] = copy_map[i+1][c];
			}
	
			// 시계방향  <- 방향
			
			for(int i=1; i<c; i++) {
				map[1][i] = copy_map[1][i+1];
			}
			
			// 시계방향  ↓ 방향
			for(int i=x-1; i>=2; i--) {
				map[i][1] = copy_map[i-1][1];
			}
			// 아래쪽 공기청정기
			x = down.x;
			y = down.y;
			
			// 반시계 -> 방향
			
			for(int i=c; i>2; i--) {
				map[x][i] = copy_map[x][i-1];
			}
			map[x][2] = 0;
			// 반시계 ↓ 방향
			for(int i=r; i>x; i--) {
				map[i][c] = copy_map[i-1][c];
			}
			
			// 반시계 <- 방향
			
			for(int i=1; i<c; i++) {
				map[r][i] = copy_map[r][i+1];
			}
			
			// 반시계 ↑ 방향
			
			for(int i=x+1; i<r; i++) {
				map[i][1] = copy_map[i+1][1];
			}
		}
		int sum = 0;
		for(int i=1; i<=r; i++) {
			for(int j=1; j<=c; j++) {
				if(map[i][j]!=-1) sum+=map[i][j];
			}
		}
		System.out.println(sum);
	}
	static void print() {
		System.out.println("==========");
		for(int i=1; i<=r; i++) {
			for(int j=1; j<=c; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	static int[][] copy(){
		
		int [][] copy_map = new int[r+1][c+1];
		
		for(int i=1; i<=r; i++) {
			for(int j=1; j<=c; j++) {
				copy_map[i][j] = map[i][j];
			}
		}
		return copy_map;
	}
	

	static boolean isRange(int x, int y) {
		if(x>=1 && y>=1 && x<=r && y<=c) return true;
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
class Info{
	int x,y,spread;

	public Info(int x, int y, int spread) {
		super();
		this.x = x;
		this.y = y;
		this.spread = spread;
	}
	
}
