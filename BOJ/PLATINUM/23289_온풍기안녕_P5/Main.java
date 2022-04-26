import java.io.*;
import java.util.*;

public class Main {
	static int dx [] = {100,0,0,-1,1};
	static int dy [] = {100,1,-1,0,0};
	static int r,c,k;
	static int map[][];
	static ArrayList<Node> list = new ArrayList<>();
	static ArrayList<Node> info = new ArrayList<>();
	static boolean wall[][][];
	static int bfsX[][] = { {100}, {-1,0,1}, {-1,0,1}, {-1,-1,-1}, {1,1,1}};
	static int bfsY[][] = { {100}, {1,1,1}, {-1,-1,-1}, {-1,0,1}, {-1,0,1}};
	static final int EAST = 1;
	static final int WEST = 2;
	static final int NORTH = 3;
	static final int SOUTH = 4;
	static ArrayList<Node> checkList = new ArrayList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[r+1][c+1];
		wall = new boolean[r+1][c+1][5];
		for(int i=1; i<=r; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] ==1) list.add(new Node(i, j,1,0));
				if(map[i][j] ==2) list.add(new Node(i, j,2,0));
				if(map[i][j] ==3) list.add(new Node(i, j,3,0));
				if(map[i][j] ==4) list.add(new Node(i, j,4,0));
				if(map[i][j]==5) checkList.add(new Node(i, j));
				map[i][j]=0; 
			}
		}
		int w = Integer.parseInt(br.readLine());	// 벽 갯수
		for(int i=0; i<w; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			
			if(t==0) {
				wall[x][y][NORTH] = true;
				wall[x-1][y][SOUTH] = true;
			}
			if(t==1) {
				wall[x][y][EAST] = true;
				wall[x][y+1][WEST] = true;
			}
		}
		
		int chocolate = 0;
		while(chocolate<=100) {
			wind();
			adjustTemperature();
			minus();
			chocolate++;
			if(check()) break;
		}
		System.out.println(chocolate);
	}
	public static void wind() {
		info.clear();   // 온풍기로 퍼진 칸들의 좌표 저장할 리스트
		for(int i=0; i<list.size(); i++) {
			Node a = list.get(i);
			bfs(a.x, a.y, a.dir);
		}
		
		for(Node a :info) {
			map[a.x][a.y]+=a.num;
		}
	}
	public static void adjustTemperature() {
		ArrayList<Node> tmp = new ArrayList<>();
		boolean visited[][][][] = new boolean[r+1][c+1][r+1][c+1];
		for(int i=1; i<=r; i++) {
			for(int j=1; j<=c; j++) {
				for(int k=1; k<=4; k++) {
					int nx = i+dx[k];
					int ny = j+dy[k];
					
					if(!isRange(nx, ny)) continue;
					if(visited[i][j][nx][ny]) continue;
					// 벽 여부 확인
					if(k==EAST) {
						if(wall[i][j][EAST]) continue;
					}
					if(k==WEST) {
						if(wall[i][j][WEST]) continue;
					}
					if(k==NORTH) {
						if(wall[i][j][NORTH]) continue;
					}
					if(k==SOUTH) {
						if(wall[i][j][SOUTH]) continue;
					}
					visited[i][j][nx][ny] = true;
					visited[nx][ny][i][j]= true; 
					int diff = Math.abs(map[i][j] - map[nx][ny]);
					diff/=4;
					if(map[i][j] > map[nx][ny]) {
						tmp.add(new Node(i, j,100,diff*-1));
						tmp.add(new Node(nx, ny,100,diff));
					}
					else {
						tmp.add(new Node(i, j,100,diff));
						tmp.add(new Node(nx, ny,100,diff*-1));
					}
				}
			}
		}
		for(Node a:tmp) {
			map[a.x][a.y]+=a.num;
		}
	}
	public static void print() {
		System.out.println(">>>>>>>>>>");
		for(int i=1; i<=r; i++) {
			for(int j=1; j<=c; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static void minus() {
		
		for(int j=2; j<=c-1; j++) {
			if(map[1][j]!=0) map[1][j]--;
			if(map[r][j]!=0) map[r][j]--;
		}
		
		for(int i=1; i<=r; i++) {
			if(map[i][1]!=0) map[i][1]--;
			if(map[i][c]!=0) map[i][c]--;
		}
	}
	public static boolean check() {
		
		for(Node a:checkList) {
			if(map[a.x][a.y]<k) return false;
		}
		return true;
	}
	public static void bfs(int x, int y, int dir) {
		boolean visited[][] = new boolean[r+1][c+1];
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		int nx = x+dx[dir];
		int ny = y+dy[dir];
		visited[nx][ny] = true;
		info.add(new Node(nx, ny,dir,5));
		pq.add(new Node(nx, ny, dir, 5));
		while(!pq.isEmpty()) {
			Node a = pq.poll();
			for(int i=0; i<3; i++) {
				nx = a.x+bfsX[dir][i];
				ny = a.y+bfsY[dir][i];
				if(isRange(nx, ny) && isPossible(a.x, a.y, nx, ny, dir, i) && a.num>1 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					pq.add(new Node(nx, ny,dir,a.num-1));
					info.add(new Node(nx, ny,dir,a.num-1));
				}
			}
		}
	}
	public static boolean isPossible(int x, int y, int nx, int ny, int dir, int i) {
		
		if(dir==EAST) {
			if(i==0) {
				if(wall[x][y][NORTH] || wall[nx][ny][WEST]) return false;
				return true;
			}
			if(i==1) {
				if(wall[x][y][EAST]) return false;
				return true;
			}
			if(i==2) {
				if(wall[x][y][SOUTH] || wall[nx][ny][WEST]) return false;
				return true;
			}
		}
		if(dir==WEST) {
			if(i==0) {
				if(wall[x][y][NORTH] || wall[nx][ny][EAST]) return false;
				return true;
			}
			if(i==1) {
				if(wall[x][y][WEST]) return false;
				return true;
			}
			if(i==2) {
				if(wall[x][y][SOUTH] || wall[nx][ny][EAST]) return false;
				return true;
			}
		}
		if(dir==NORTH) {
			if(i==0) {
				if(wall[x][y][WEST] || wall[nx][ny][SOUTH]) return false;
				return true;
			}
			if(i==1) {
				if(wall[x][y][NORTH]) return false;
				return true;
			}
			if(i==2) {
				if(wall[x][y][EAST] || wall[nx][ny][SOUTH]) return false;
				return true;
			}
		}
		if(dir==SOUTH) {
			if(i==0) {
				if(wall[x][y][WEST] || wall[nx][ny][NORTH]) return false;
				return true;
			}
			if(i==1) {
				if(wall[x][y][SOUTH]) return false;
				return true;
			}
			if(i==2) {
				if(wall[x][y][EAST] || wall[nx][ny][NORTH]) return false;
				return true;
			}
		}
		return false;
	}
	public static boolean isRange(int x, int y) {
		if(x>=1 && y>=1 && x<=r && y<=c) return true;
		return false;
	}
}
class Node implements Comparable<Node>{
	int x,y,dir,num;
	
	public Node(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public Node(int x, int y, int dir,int num) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.num= num;
	}
	@Override
	public int compareTo(Node o) {
		return o.num - this.num;
	}
	
}