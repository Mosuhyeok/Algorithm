import java.io.*;
import java.util.*;

public class Main {
	static ArrayList<Integer>map[][];
	static int m,s;
	static int dx[] = {100,0,-1,-1,-1,0,1,1,1};
	static int dy[] = {100,-1,-1,0,1,1,1,0,-1};
	static int shark_x,shark_y;
	static int smellMap[][] = new int[5][5];
	static boolean visited[] = new boolean[3];
	static int sx [] = {-1,0,1,0};
	static int sy [] = {0,-1,0,1};
	static int arr[] = new int[3];
	static ArrayList<ArrayList<Integer>>dirList = new ArrayList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		getDirPumutation(0);
		
		
		map = new ArrayList[5][5];
		for(int i=1; i<=4; i++) {
			for(int j=1; j<=4; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			map[x][y].add(dir);
		}
		st = new StringTokenizer(br.readLine());
		shark_x = Integer.parseInt(st.nextToken());
		shark_y = Integer.parseInt(st.nextToken());
		
		while(s-->0) {
			// 1. 복제
			ArrayList<Integer>[][]copy = copy();
			
			//2. 물고기 이동
			fishMove();
			
			// 3. 상어 이동
			sharkMove();
			// 4. 격자 냄새 -1
			removeSmell();
			
			// 5.복제하기
			addCopyFish(copy);
		}
		
		int ans = 0;
		for(int i=1; i<=4; i++) {
			for(int j=1; j<=4; j++) {
				ans+=map[i][j].size();
			}
		}
		System.out.println(ans);
	}
	public static void addCopyFish(ArrayList<Integer>[][]copy) {
		for(int i=1; i<=4; i++) {
			for(int j=1; j<=4; j++) {
				for(int d: copy[i][j]) {
					map[i][j].add(d);
				}
			}
		}
	}
	public static void removeSmell() {
		for(int i=1; i<=4; i++) {
			for(int j=1; j<=4; j++) {
				if(smellMap[i][j]==0) continue;
				smellMap[i][j]--;
			}
		}
	}
	public static void print() {
		System.out.println(">>>>>>");
		for(int i=1; i<=4; i++) {
			for(int j=1; j<=4; j++) {
				if(map[i][j].size()!=0) {
					System.out.print(map[i][j].size()+" ");
				}
				else {
					System.out.print(0+" ");
				}
			}
			System.out.println();
		}
	}
	public static void sharkMove() {
		
		int dirListIdxNum = 0;
		int max = -1;
		for(int i=0; i<dirList.size(); i++) {
			boolean visited[][] = new boolean[5][5];
			int nx = shark_x;
			int ny = shark_y;
			int sum = 0;
			boolean flag = true;
			ArrayList<Integer> tt = dirList.get(i);
			for(int j=0; j<tt.size(); j++) {
				int d = tt.get(j);
				nx+=sx[d];
				ny+=sy[d];
				if(!isRange(nx, ny)) {
					flag = false;
					break;
				}
				if(!visited[nx][ny]) {
					sum+=map[nx][ny].size();
					visited[nx][ny] = true;
				}
			}
			if(flag) {
				if(max < sum) {
					max = sum;
					dirListIdxNum = i;
				}
			}
		}
		ArrayList<Integer> tmpList = dirList.get(dirListIdxNum);
		
//		if(map[shark_x][shark_y].size() !=0) {
//			map[shark_x][shark_y].clear();
//			smellMap[shark_x][shark_y] = 3;
//		}
		for(int i=0; i<tmpList.size(); i++) {
			int d = tmpList.get(i);
			shark_x+=sx[d];
			shark_y+=sy[d];
			if(map[shark_x][shark_y].size() !=0) {
				map[shark_x][shark_y].clear();
				smellMap[shark_x][shark_y] = 3;
			}
		}
	}
	public static void printSmell() {
		System.out.println("smell~~~");
		for(int i=1; i<=4; i++) {
			for(int j=1; j<=4; j++) {
				System.out.print(smellMap[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static void fishMove() {
		
		ArrayList<Node>list = new ArrayList<>();
		for(int i=1; i<=4; i++) {
			for(int j=1; j<=4; j++) {
				int size = map[i][j].size();
				
				for(int k=0; k<size; k++) {
					int originDir = map[i][j].get(k);
					int dir = map[i][j].get(k);
					boolean ismove = false;
						// 디버그시 한번참조 냄새에 관한거
						int nx = i;
						int ny = j;
						for(int l=0; l<8; l++) {
							 nx = i+dx[dir];
							 ny = j+dy[dir];
							
							if(!isRange(nx, ny)) {
								dir = changeDir(dir);
								continue;
							}
							
							if(nx==shark_x && ny==shark_y) {
								dir = changeDir(dir);
								continue;
							}
							
							if(smellMap[nx][ny]!=0) {
								dir = changeDir(dir);
								continue;
							}
							ismove = true;
							break;
						}
						// 못갔을 경우 자기 제자리에 돌아올수 있는지 체크 디버그시
						if(!ismove) {
							nx = i;
							ny = j;
							dir = originDir;
						}
						list.add(new Node(nx, ny, dir));
					
				}
				
			}
		}
		map = new ArrayList[5][5];
		for(int i=1; i<=4; i++) {
			for(int j=1; j<=4; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		for(Node a: list) {
			map[a.x][a.y].add(a.dir);
		}
		
	}
	public static ArrayList<Integer>[][] copy(){
		ArrayList<Integer>[][]copy;
		copy = new ArrayList[5][5];
		for(int i=1; i<=4; i++) {
			for(int j=1; j<=4; j++) {
				copy[i][j] = new ArrayList<>();
			}
		}
		
		for(int i=1; i<=4; i++) {
			for(int j=1; j<=4; j++) {
				for(int dir : map[i][j]) {
					copy[i][j].add(dir);
				}
			}
		}
		return copy;
	}
	public static void getDirPumutation(int level) {
		
		if(level ==3) {
			ArrayList<Integer> tmp = new ArrayList<>();
			for(int i=0; i<3; i++) {
				tmp.add(arr[i]);
			}
			dirList.add(tmp);
			return;
		}
		// 방향 중복순열
		
		for(int i=0; i<4; i++) {
			arr[level]=i;
			getDirPumutation(level+1);
		}
	}
	public static int changeDir(int dir) {
		dir--;
		if(dir==0) return 8;
		return dir;
	}
	public static boolean isRange(int x, int y) {
		if(x>=1 && y>=1 && x<=4 && y<=4) return true;
		return false;
	}
}
class Node{
	int x,y,dir;
	public Node(int dir) {
		this.dir = dir;
	}
	public Node(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}
class Info implements Comparable<Info>{
	int num,idx;
	public Info(int num, int idx) {
		this.num = num;
		this.idx = idx;
	}
	@Override
	public int compareTo(Info o) {
		return this.num-o.num;
	}
	
}