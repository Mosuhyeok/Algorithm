import java.io.*;
import java.util.*;
public class Main {
	static int n,q,l;
	static int map[][];
	static int level [];
	static int dx [] = {0,0,-1,1};
	static int dy [] = {1,-1,0,0};
	static boolean visited[][];
	static int max = 0;
	static ArrayList<Integer>list = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String [] t = br.readLine().split(" ");
		
		n = Integer.parseInt(t[0]);	
		q = Integer.parseInt(t[1]);
		
		n = (int)Math.pow(2, n);
		
		map = new int[n][n];
		visited = new boolean[n][n];
		for(int i=0; i<n; i++) {
			t = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(t[j]);
			}
		}
		
		t = br.readLine().split(" ");
		level = new int[q];
		
		for(int i=0; i<q; i++) {
			level[i] = Integer.parseInt(t[i]);
		}
		
		for(int loop = 0; loop<q; loop++) {
			l = (int)Math.pow(2, level[loop]);
			if(l!=1) map = rotate();

			
			decrease();

		}

		int sum = 0;
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				sum+=map[i][j];
			}
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(!visited[i][j] && map[i][j]!=0) {
					bfs(i,j);
				}
			}
		}
		Collections.sort(list);
		Collections.reverse(list);
		System.out.println(sum);
		System.out.println(max);
	}
	public static void bfs(int x, int y) {
		
		Node a = new Node(x,y);
		visited[x][y] = true;
		Queue<Node> q = new LinkedList<>();
		q.add(a);
		
		int tmp = 0;
		
		while(!q.isEmpty()) {
			Node b = q.poll();
			tmp++;
			for(int i=0; i<4; i++) {
				int nx = b.x+dx[i];
				int ny = b.y+dy[i];
				
				if(isRange(nx,ny) && !visited[nx][ny] && map[nx][ny]!=0) {
					q.add(new Node(nx,ny));
					visited[nx][ny] = true;
				}
			}
		}
		max = Math.max(max, tmp);
	}
	public static boolean isRange(int x, int y) {
		if(x>=0 && y>=0 && x<n && y<n) return true;
		return false;
	}
	static int[][] melt() {
		//얼음 녹음
		int[][] result = new int[n][n];

		for (int j = 0; j < n; j++) {
			for (int k = 0; k < n; k++) {
				int cnt = 0;
				for(int i=0; i<4; i++) {
					int nx = j + dx[i];
					int ny = k + dy[i];

					if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
					if(map[nx][ny]>=1) cnt++;
				}
				result[j][k] = map[j][k];
				if (cnt < 3 && result[j][k] > 0) result[j][k]--;
			}
		}
		return result;
	}

	public static void decrease() {
		
		ArrayList<Node> list = new ArrayList<>();
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				int cnt = 0;
				for(int k=0; k<4; k++) {
					int nx = i+dx[k];
					int ny = j+dy[k];
					if(isRange(nx,ny) && map[nx][ny]>=1) cnt++;
				}
				
				if(cnt<3 && map[i][j]>0) {
					list.add(new Node(i,j));
				}
			}
		}
		
		for(Node a: list) {
			map[a.x][a.y]--;
		}
		
	}

	public static int [][] rotate() {
		int [][] tmp = new int[n][n];
		
		for(int i=0; i<n; i+=l) {
			for(int j=0; j<n; j+=l) {
				for(int a=0; a<l; a++) {
					for(int b=0; b<l; b++) {
						tmp[a+i][b+j] = map[i+l-1-b][a+j];
					}
				}
			}
		}
		return tmp;
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