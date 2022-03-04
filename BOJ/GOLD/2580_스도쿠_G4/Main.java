import java.util.*;
import java.io.*;

class Main{
	static int map[][] = new int[9][9];
	static ArrayList<Node>list = new ArrayList<>();
	public static void main(String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0; i<9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==0) list.add(new Node(i,j));

			}
		}
		dfs(0,map);
		
		
	}
	public static void dfs(int level, int [][] copyMap) {
		//System.out.println(level);
		if(level == list.size()) {
			
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					System.out.print(copyMap[i][j]+" ");
				}
				System.out.println();
			}
			System.exit(0);
			return ;
		}
		
		
		int x=  list.get(level).x;
		int y = list.get(level).y;
		
		for(int i=1; i<=9; i++) {
			
			if(isPossibleGaro(x, y, i, copyMap) && isPossibleSero(x, y, i, copyMap) && isPossibleThreeByThree(x, y, i, copyMap)) {
				copyMap[x][y] = i;
				dfs(level+1,copyMap);
				copyMap[x][y] = 0;
			}
		}
		
	}
	public static boolean isPossibleThreeByThree(int x, int y, int num, int [][]copyMap) {
		
		int tmp_x = x/3;
		int tmp_y = y/3;
		
		int start_x = (tmp_x*3);
		int start_y = (tmp_y*3);
		
//		System.out.println("x y는");
//		System.out.println(x+" "+y);
//		
//		System.out.println("start_x , start_y 는");
//		System.out.println(start_x + " "+start_y);
		for(int i=start_x; i<start_x+3; i++) {
			for(int j=start_y; j<start_y+3; j++) {
				if(copyMap[i][j] == num) return false;
			}
		}
		
		return true;
	}
	public static boolean isPossibleGaro(int x, int y, int num, int[][] copyMap) {
		
		for(int i=0; i<9; i++) {
			if(copyMap[x][i] == num) return false; 
		}
		return true;
	}
	public static boolean isPossibleSero(int x, int y, int num, int[][] copyMap) {
		
		for(int i=0; i<9; i++) {
			if(copyMap[i][y] == num) return false; 
		}
		return true;
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