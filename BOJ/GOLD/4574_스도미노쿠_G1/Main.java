import java.util.*;
import java.io.*;

class Main{
	static int map[][];
	static int n;
	static boolean domino[][];
	static int dx [] = {1,0};
	static int dy [] = {0,1};

	static boolean flag;
	public static void main(String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int idx =0;
		while(true) {
			idx++;
			flag= false;
			map = new int[9][9];
			domino = new boolean[10][10];

			n = Integer.parseInt(br.readLine());
			if(n==0) System.exit(0);
			
			for(int i=0; i<n; i++) {
				StringTokenizer st  =new StringTokenizer(br.readLine());
				
				int U = Integer.parseInt(st.nextToken());	// 도미노에 쓰여있는 한 숫자
				String LU = st.nextToken();	// U의 위치를 나타낸다.
				int V = Integer.parseInt(st.nextToken());	// 도미노에 써있는 다른숫자
				String LV = st.nextToken();// 도미노에 써있는 다른 숫자
				
				
				int x = LU.charAt(0)-'A';
				int y = LU.charAt(1) - '0'-1;
				
				map[x][y] = U;
				
				x = LV.charAt(0)-'A';
				y = LV.charAt(1)-'0'-1;
				map[x][y] = V;
				domino[U][V] = true;
				domino[V][U] = true;
								
			}
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 1~9가 있는 위치
			
			for(int i=1; i<=9; i++) {
				String str = st.nextToken();
				
				int x = str.charAt(0)-'A';
				int y = str.charAt(1)-'0'-1;
				
				map[x][y] = i;
			}

			System.out.println("Puzzle "+idx);
			dfs(0);
		}
	}
	public static void dfs(int level) {
		if(flag) return;
		if(level == 81) {
			flag = true;
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					System.out.print(map[i][j]+"");
				}
				System.out.println();
			}
			return ;
		}
		
		int x = level/9;
		int y = level%9;
		
		if(map[x][y]!=0) dfs(level+1);
		else {
			// 1~9 도미노 넣어봄
			for(int i=1; i<=9; i++) {
				for(int j=1; j<=9; j++) {
					if(i==j) continue;
					if(domino[i][j]) continue;
					
					for(int k=0; k<2; k++) {
						
							int nx = x+dx[k];
							int ny = y+dy[k];
							
							if(isRange(nx,ny) && map[nx][ny] ==0) {
								
								
								if(garoSero(x, y, i) && three(x, y, i) && garoSero(nx, ny, j) && three(nx, ny, j)) {
									
									map[x][y] = i;
									map[nx][ny]=j;
									
									domino[i][j] = true;
									domino[j][i] = true;
									
									dfs(level+1);
									
									map[x][y] =0;
									map[nx][ny] =0;
									
									domino[i][j] = false;
									domino[j][i] = false;
								}
							}

						
					}

				}
			}
			
		}
	
		
		
	}
	public static boolean three(int x, int y, int num) {
		
		int tmp_x = x/3;
		int tmp_y = y/3;
		
		int start_x = (tmp_x*3);
		int start_y = (tmp_y*3);
		
		for(int i=start_x; i<start_x+3; i++) {
			for(int j=start_y; j<start_y+3; j++) {
				if(map[i][j] == num) return false;
			}
		}
		
		return true;
	}
	public static boolean garoSero(int x, int y, int num) {
		
		for(int i=0; i<9; i++) {
			if(map[x][i] == num) return false; 
		}
		for(int i=0; i<9; i++) {
			if(map[i][y] == num) return false; 
		}
		return true;
	}
	public static boolean isRange(int x, int y) {
		if(x>=0 && y>=0 && x<9 && y<9) return true;
		return false;
		
	}
}
