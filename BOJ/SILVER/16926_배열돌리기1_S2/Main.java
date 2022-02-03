import java.io.*;
import java.util.*;
public class Main {
	
	static int map[][];
	static int copyMap[][];
	static int n,m,r;
	static int dx[] = {1,0,-1,0};
	static int dy[] = {0,1,0,-1};
	static boolean visited[][];
	static int step = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String [] input = br.readLine().split(" ");
		
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		r = Integer.parseInt(input[2]);
		
		map= new int[n][m];
		visited = new boolean[n][m];
		
		
		for(int i=0; i<n; i++) {
			input = br.readLine().split(" ");
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		//r%=4;
		
		for(int i=0; i<r; i++) {
			visited = new boolean[n][m];
			rotate();
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static void rotate() {
		copy();
		int dir = 0;
		int loop = Math.min(n, m)/2;
		for(int i=0; i<loop; i++) {
			int x=i;
			int y = i;
			int start = map[x][y];
			dir = 0;
			while(dir<4) {
				
				int nx = x+dx[dir];
				int ny = y+dy[dir];

				if(nx >=i && ny>=i && nx<n-i && ny<m-i) {
					map[nx][ny] = copyMap[x][y];
					x = nx;
					y = ny;
				}
				else dir++;
			}
		}
		
	}
	public static void copy() {
		copyMap = new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
	}

	public static boolean isPossible() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(!visited[i][j]) return true;
			}
		}
		return false;
	}
 }
