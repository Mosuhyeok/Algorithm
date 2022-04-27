import java.io.*;
import java.util.*;

import javax.swing.border.Border;

public class Main {
	static int test = 0;
	static Node arr[] = new Node[32];
	static ArrayList<Integer>list = new ArrayList<>();
	static Horse horse[] = new Horse[4];
	static boolean flag[] = new boolean[4];
	static boolean visited [] = new boolean[32];
	static int max = -1;
	static int dfsArr[] = new int[10];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		init();
		dfs(0);
		System.out.println(max);
	}
	public static void go() {
		
		int sum = 0;
		for(int i=0; i<10; i++) {
			int num = dfsArr[i];
			if(flag[num]) continue;	// 이미 도착칸에 도착한 말
			int before = horse[num].pos;
			// 말이 이동을 마치는 칸에 다른 말이 있으면 못감
			int step = list.get(i);
			int horsePos = horse[num].pos;
			boolean blue = horse[num].blue;
			
			for(int j=0; j<step; j++) {
				if(horsePos ==100) break;	// 목적지에 도착
				if(blue) {
					blue = false;
					horsePos = arr[horsePos].blue;
				}
				else {
					horsePos = arr[horsePos].red;
				}
			}
			if(horsePos == 100) {
				visited[horse[num].pos] = false;
				flag[num] = true;
			}
			else {
				if(visited[horsePos]) continue;
				// 여기로 이동 가능
				horse[num].blue = blue;
				visited[horsePos] = true;
				visited[before] = false;
				sum+=arr[horsePos].val;
				horse[num].pos = horsePos;
				if(horsePos == 5 || horsePos==10 || horsePos ==15) {
					horse[num].blue = true;
				}
			}
			
		}
		
		max = Math.max(max, sum);
	}
	public static void dfs(int level) {
		
		if(level ==10) {
			visited = new boolean[32];
			
			for(int i=0; i<4; i++) {
				horse[i] = new Horse(0, false);
			}
			flag = new boolean[4];
			
			go();
			return;
		}
		
		dfsArr[level] = 0;
		dfs(level+1);
		dfsArr[level] = 1;
		dfs(level+1);
		dfsArr[level] = 2;
		dfs(level+1);
		dfsArr[level] = 3;
		dfs(level+1);
	}
	public static void init() {
		int num = 0;
		arr[0] = new Node(1, 0, 0);
		for(int i=1; i<=19; i++) {
			arr[i] = new Node(i+1, 0, num+=2);
		}
		arr[5].blue = 21;
		arr[10].blue = 28;
		arr[15].blue = 25;
		
		arr[20] = new Node(100, 0, 40);
		
		arr[21] = new Node(22, 0, 13);
		arr[22] = new Node(23, 0, 16);
		arr[23] = new Node(24, 0,19);
		arr[24] = new Node(30, 0, 25);
		arr[25] = new Node(26, 0, 28);
		arr[26] = new Node(27, 0, 27);
		arr[27] = new Node(24, 0, 26);
		arr[28] = new Node(29, 0, 22);
		arr[29] = new Node(24, 0, 24);
		arr[30] = new Node(31, 0, 30);
		arr[31] = new Node(20, 0, 35);
	}
}
class Node{
	int red,blue,val;

	public Node(int red, int blue, int val) {
		this.red = red;
		this.blue = blue;
		this.val = val;
	}
}
class Horse{
	int pos;
	boolean blue;
	public Horse(int pos, boolean blue) {
		this.pos = pos;
		this.blue = blue;
	}
	
}