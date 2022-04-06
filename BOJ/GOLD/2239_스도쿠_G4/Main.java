import java.util.*;

import javax.print.attribute.standard.DateTimeAtProcessing;

import java.io.*;
public class Main {
	static int map[][] = new int[9][9];
	static ArrayList<Node>list = new ArrayList<>();
	public static void main(String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		for(int i=0; i<9; i++) {
			String str = br.readLine();
			for(int j=0; j<9; j++) {
				map[i][j]= str.charAt(j)-'0';
				if(map[i][j]==0) list.add(new Node(i, j));
			}
		}
		
		
		dfs(0);
		
		
	}
	public static void dfs(int level) {
		if(level == list.size()) {
			
			// dfs니까 가장 먼저 도착하는게 사전순으로 작은거
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					System.out.print(map[i][j]+"");
				}
				System.out.println();
			}
			System.exit(0);
		}
		
		Node a = list.get(level);
		
		for(int i=1; i<=9; i++) {
			if(garo(a.x, i) && sero(a.y, i) && square(a.x, a.y, i)) {
				map[a.x][a.y] = i;
				dfs(level+1);
				map[a.x][a.y] = 0;
			}
		}
		
	}
	public static boolean square(int x, int y, int num) {
		
		int r = 0;
		int c = 0;
		if(x>=0 && x<=2) r = 0;
		if(x>=3 && x<=5) r = 3;
		if(x>=6) r = 6;
		
		if(y>=0 && y<=2) c = 0;
		if(y>=3 && y<=5) c = 3;
		if(y>=6) c = 6;
		
		for(int i=r; i<r+3; i++) {
			for(int j=c; j<c+3; j++) {
				if(map[i][j]== num) return false;
			}
		}
		return true;
		
	}
	public static boolean garo(int x, int num) {
		
		for(int i=0; i<9; i++) {
			if(map[x][i]== num) return false;
		}
		return true;
		
	}
	public static boolean sero(int y, int num) {
		
		for(int i=0; i<9; i++) {
			if(map[i][y]== num) return false;
		}
		return true;
	}
}
class Node{
	int x,y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

