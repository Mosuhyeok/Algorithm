package tomyself;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	
	static final int BLACK = 1;
	static final int WHITE = 2;
	static int ans_x =0;
	static int ans_y = 0;
	static int map[][];
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		 map = new int[20][20];
		
		for(int i=1; i<=19; i++) {
			String [] t= br.readLine().split(" ");
			for(int j=1; j<=19; j++) {
				map[i][j] = Integer.parseInt(t[j-1]);
			}
		}
		
		
		for(int i=1; i<=19; i++) {
			for(int j=1; j<=19; j++) {
				
				if(map[i][j]==BLACK) {
					if(isPossibleBlack(i, j)) {
						System.out.println(BLACK);
						System.out.println(ans_x+" "+ans_y);
						return ;
					}
				}
				if(map[i][j]==WHITE) {
					if(isPossibleWhite(i, j)) {
						System.out.println(WHITE);
						System.out.println(ans_x+" "+ans_y);
						return;
					}
				}
			}
		}
		
		System.out.println(0);

	}
	public static boolean isPossibleWhite(int x, int y) {
		
		boolean flag = true;
		// 1. 가로 체크
		
		int cnt = 0;
		int nx = x;
		int ny = y;
		for(int i=0; i<4; i++) {
			ny+=1;
			if(isRange(nx,ny) && map[nx][ny]==WHITE) {
				cnt++;
			}
		}
		
		if(cnt==4) {
			if(isRange(x,y-1) && map[x][y-1]==WHITE) flag =false;
			if(isRange(x,y+5) && map[x][y+5]==WHITE) flag = false;
			
			if(flag) {
				ans_x = x;
				ans_y = y;
				return true;
			}
		}
		
		// 2. 세로 체크
		flag = true;
		cnt = 0;
		nx = x;
		ny = y;
		for(int i=0; i<4; i++) {
			nx+=1;
			if(isRange(nx,ny) && map[nx][ny] == WHITE) {
				cnt++;
			}
		}
		if(cnt ==4) {
			
			if(isRange(x-1,y) && map[x-1][y]==WHITE) flag = false;
			if(isRange(x+5,y) && map[x+5][y]==WHITE) flag = false;
			
			if(flag) {
				ans_x = x;
				ans_y = y;
				return true;
			}
		}
		
		// 3. 대각선 체크 1
		flag = true;
		cnt = 0;
		nx = x;
		ny = y;
		
		for(int i=0; i<4; i++) {
			nx+=1;
			ny+=1;
			if(isRange(nx,ny) && map[nx][ny]==WHITE) {
				cnt++;
			}
		}
	
		if(cnt==4) {
			if(isRange(x-1,y-1) && map[x-1][y-1]==WHITE) flag = false;
			if(isRange(x+5,y+5) && map[x+5][y+5]==WHITE) flag = false;
			if(flag) {
				ans_x = x;
				ans_y = y;
				return true; 
			}
		}
		
		// 4. 대각선 체크 2
		
		cnt =0;
		nx = x;
		ny = y;
		flag = true;
		for(int i=0; i<4; i++) {
			nx+=1;
			ny-=1;
			
			if(isRange(nx,ny) && map[nx][ny] == WHITE) {
				cnt++;
			}
		}
		if(cnt ==4) {
			
			if(isRange(x-1,y+1) && map[x-1][y+1] ==WHITE) flag =  false;
			if(isRange(x+5,y-5) && map[x+5][y-5]==WHITE) flag = false;
			if(flag) {
				ans_x = nx;
				ans_y = ny;
				return true; 	
			}
		}
		return false;
	}
	public static boolean isPossibleBlack(int x, int y) {
		
		// 1. 가로 체크
		boolean flag = true;
		int cnt = 0;
		int nx = x;
		int ny = y;
		for(int i=0; i<4; i++) {
			ny+=1;
			if(isRange(nx,ny) && map[nx][ny]==BLACK) {
				cnt++;
			}
		}
		
		if(cnt==4) {
			if(isRange(x,y-1) && map[x][y-1]==BLACK) flag = false;
			if(isRange(x,y+5) && map[x][y+5]==BLACK) flag = false;
		
			if(flag) {
				ans_x = x;
				ans_y = y;
				return true;	
			}
		}
		
		
		// 2. 세로 체크
		flag = true;
		cnt = 0;
		nx = x;
		ny = y;
		for(int i=0; i<4; i++) {
			nx+=1;
			if(isRange(nx,ny) && map[nx][ny] == BLACK) {
				cnt++;
			}
		}
		if(cnt ==4) {
			if(isRange(x-1,y) && map[x-1][y]==BLACK) flag = false;
			if(isRange(x+5,y) && map[x+5][y]==BLACK) flag = false;
			if(flag) {
				ans_x = x;
				ans_y = y;
				return true;	
			}
		}
		
		// 3. 대각선 체크 1
		cnt = 0;
		nx = x;
		ny = y;
		flag = true;
		for(int i=0; i<4; i++) {
			nx+=1;
			ny+=1;
			if(isRange(nx,ny) && map[nx][ny]==BLACK) {
				cnt++;
			}
		}
		
		if(cnt==4) {
			
			if(isRange(x-1,y-1) && map[x-1][y-1]==BLACK) flag = false;
			if(isRange(x+5,y+5) && map[x+5][y+5]==BLACK) flag = false;
			if(flag) {
				ans_x = x;
				ans_y = y;
				return true; 	
			}
			
		}
		
		// 4. 대각선 체크 2
		
		cnt =0;
		nx = x;
		ny = y;
		flag = true;
		for(int i=0; i<4; i++) {
			nx+=1;
			ny-=1;
			
			if(isRange(nx,ny) && map[nx][ny] == BLACK) {
				cnt++;
			}
		}
		if(cnt ==4) {
			if(isRange(x-1,y+1) && map[x-1][y+1] ==BLACK) flag= false;
			if(isRange(x+5,y-5) && map[x+5][y-5]==BLACK) flag= false;
			if(flag) {
				ans_x = nx;
				ans_y = ny;
				return true; 	
			}
		}
		return false;
	}

	
	
	public static boolean isRange(int x, int y) {
		if(x>=1 && y>=1 && x<=19 && y<=19) return true;
		return false;
	}
 }
