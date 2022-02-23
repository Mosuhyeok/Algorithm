import java.util.*;
import java.io.*;

class Main{
	static int n;
	static int dx [] = {0,0,1,-1};
	static int dy [] = {1,-1,0,0};
	static int map[][];
	static Shark shark;
	static boolean visited[][];
	static int time = 0;
	static ArrayList<Node>fish = new ArrayList<>();
	public static void main(String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visited = new boolean[n][n];
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==9) {
					shark = new Shark(i,j,2,0);
				}
			}
		}
		
		solve();
	}
	public static void solve() {
		
		while(true) {
			fish = new ArrayList<>();
			ArrayList<Node>list = new ArrayList<>();
			// 1. 아기상어보다 작은 고기들 리스트
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(map[i][j]!=0 && map[i][j] != 9&& map[i][j] < shark.size) {
						list.add(new Node(i,j,Math.abs(shark.x-i) + Math.abs(shark.y-j)));
					}
				}
			}
			
			// 만약 먹을수 있는 고기가 없다면 끝
			if(list.size()==0) {
				System.out.println(time);
				System.exit(0);
			}
			
			// 정렬 해둔 것중 해당 목적지에 도착할수 있는지 체크
			for(int i=0; i<list.size(); i++) {
				Node fish = list.get(i);
				bfs(fish.x,fish.y);
			}
			if(fish.size() ==0) {
				System.out.println(time);
				System.exit(0);
			}
		
			Collections.sort(fish);
			Node one = fish.get(0);
			time+=one.dist;
			map[shark.x][shark.y] = 0;	// 원래 상어자리 0으로
			
			
			shark.x = one.x;
			shark.y = one.y;
			map[shark.x][shark.y] = 9;	// 이동한 위치 상어 자리로 이동
			
			shark.eat++;
			
			if(shark.eat == shark.size) {
				shark.eat =0;
				shark.size++;
			}
		}
	}
	public static void bfs(int x, int y) {
		visited = new boolean[n][n];
		Queue<Node> q = new LinkedList<>();
		
		q.add(new Node(shark.x,shark.y,0));
		visited[shark.x][shark.y] = true;
		while(!q.isEmpty()) {
			Node a = q.poll();
			if(a.x ==x && a.y==y) {
				fish.add(new Node(x,y,a.dist));
				return;
			}
			for(int i=0; i<4; i++) {
				int nx = a.x+dx[i];
				int ny = a.y+dy[i];
				
				if(isRange(nx,ny) && !visited[nx][ny] && map[nx][ny]<=shark.size) {
					q.add(new Node(nx,ny,a.dist+1));
					visited[nx][ny] =true;
				}
			}
		}
	}
	public static boolean isRange(int x, int y) {
		if(x>=0 && y>=0 && x<n && y<n) return true;
		return false;
	}
}
class Shark{
	int x,y,size,eat;

	public Shark(int x, int y, int size, int eat) {
		super();
		this.x = x;
		this.y = y;
		this.size = size;
		this.eat = eat;
	}
}
class Node implements Comparable<Node>{
	int x,y,dist;

	public Node(int x, int y, int dist) {
		super();
		this.x = x;
		this.y = y;
		this.dist = dist;
	}

	@Override
	public int compareTo(Node o) {
		if(this.dist ==o.dist) {
			if(this.x==o.x) {
				return this.y- o.y;
			}
			return this.x-o.x;
		}
		return this.dist - o.dist;
	}
	
}