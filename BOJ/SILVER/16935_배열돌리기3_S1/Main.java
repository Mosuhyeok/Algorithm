import java.io.*;
import java.util.*;

public class Main {
	static int n,m,r;
	static int map[][];
	static int order[];
    public static void main(String[] args) throws Exception{
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine()," ");
    	
    	n = Integer.parseInt(st.nextToken());	// 세로
    	m = Integer.parseInt(st.nextToken());	// 가로
    	r = Integer.parseInt(st.nextToken());
    	order = new int[r];	// 명령어
    	map = new int[n][m];
    	
    	for(int i=0; i<n; i++) {
    		st = new StringTokenizer(br.readLine()," ");
    		for(int j=0; j<m; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	st = new StringTokenizer(br.readLine()," ");
    	for(int i=0; i<r; i++) {
    		order[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	for(int i=0; i<r; i++) {
    		int num = order[i];
    		
    		if(num==1) operationOne();
    		if(num==2) operationTwo();
    		if(num==3) operationThree();
    		if(num==4) operationFour();
    		if(num==5) operationFive();
    		if(num==6) operationSix();
    	}
    	
    	print();
    	br.close();
    }
    static void print() {
    	for(int i=0; i<n; i++) {
    		for(int j=0; j<m; j++) {
    			System.out.print(map[i][j]+" ");
    		}
    		System.out.println();
    	}
    }
    static void clone(int [][] copy) {
    	map = new int[copy.length][copy[0].length];
    	
    	for(int i=0; i<n; i++) {
    		for(int j=0; j<m; j++) {
    			map[i][j] = copy[i][j];
    		}
    	}
    }
    static void operationOne() {
    	// 배열 상하 반전
    	
    	int loop = n-1;
    	int x = 0;
    	while(loop>=1) {
    		int [] arr = map[x];
    		map[x] = map[x+loop];
    		map[x+loop] = arr;
    		x++;
    		loop-=2;
    	}
    	
    	
    }
    static void operationTwo() {
    	// 배열 좌우 반전
    	
       	int loop = m-1;
    	int y = 0;
    	while(loop>=1) {
    		int [] arr = new int[n];	// 세로모양 배열 담을거
    		
    		for(int i=0; i<n; i++) {
    			int tmp = map[i][y];
    			map[i][y] = map[i][y+loop];
    			map[i][y+loop] = tmp;
    		}
    		y++;
    		loop-=2;
    	}
    	
    	
    }
    static void operationThree() {
    
    	int copy [][] = new int[m][n];
   
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				copy[i][j] = map[n-1-j][i];
			}
		}
    
    	int tmp = n;
    	n = m;
    	m = tmp;
    	clone(copy);

    	
    }
    static void operationFour() {
    	
    	int copy [][] = new int[m][n];
   
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				copy[i][j] = map[j][m-i-1];
			}
		}

    	int tmp = n;
    	n = m;
    	m = tmp;
    	clone(copy);

    }
    static void operationFive() {
    	
    	int sero = n/2;
    	int garo = m/2;
    	
    	int copy[][][] = new int[4][sero][garo];
    	
    	int cnt = -1;
    	for(int i=0; i<n; i+=sero) {
    		for(int j=0; j<m; j+=garo) {
    			cnt++;
    			for(int a= 0; a<sero; a++) {
    				for(int b=0; b<garo; b++) {
    					copy[cnt][a][b] = map[a+i][b+j];
    				}
    			}
    		}
    	}
    	
    	int [] []tmp = copy[3];
    	copy[3] = copy[2];
    	copy[2] = tmp;
    	int dir = 3;
    	for(int i=0; i<n; i+=sero) {
    		for(int j=0; j<m; j+=garo) {
    			for(int a=0; a<sero; a++) {
    				for(int b=0; b<garo; b++) {
    					map[i+a][j+b] = copy[dir][a][b];
    				}
    			}
    			if(dir==3) dir=0;
    			else if(dir==0) dir=2;
    			else if(dir==2) dir=1;
    		}
    	}
    	
    }
    static void operationSix() {
    	int sero = n/2;
    	int garo = m/2;
    	
    	int copy[][][] = new int[4][sero][garo];
    	
    	int cnt = -1;
    	for(int i=0; i<n; i+=sero) {
    		for(int j=0; j<m; j+=garo) {
    			cnt++;
    			for(int a= 0; a<sero; a++) {
    				for(int b=0; b<garo; b++) {
    					copy[cnt][a][b] = map[a+i][b+j];
    				}
    			}
    		}
    	}
    	boolean flag = true;
    	int [] []tmp = copy[3];
    	copy[3] = copy[2];
    	copy[2] = tmp;
    	int dir = 1;
    	for(int i=0; i<n; i+=sero) {
    		for(int j=0; j<m; j+=garo) {
    			for(int a=0; a<sero; a++) {
    				for(int b=0; b<garo; b++) {
    					map[i+a][j+b] = copy[dir][a][b];
    				}
    			}
    			if(flag) {
    				dir++;
    				if(dir==3) {
    					dir=0;
    					flag = false;
    				}
    			}
    			else {
    				dir=3;
    			}
    		}
    	}
    }
}
