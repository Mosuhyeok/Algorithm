import java.util.*;
import java.io.*;

class Main{
	static int v,e,k;
	static int dist[];
	static ArrayList<Node>[] list;
	public static void main(String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		v = Integer.parseInt(st.nextToken());	// 정점의 수 v 최대 2만개
		e = Integer.parseInt(st.nextToken());	// 간선의 수 e 최대 30만개
		
		k = Integer.parseInt(br.readLine());	// 시작 정점의 번호
		
		dist = new int[v+1];
		
		list = new ArrayList[v+1];
		for(int i=0; i<=v; i++) {
			list[i] = new ArrayList<>();
		}
		
		Arrays.fill(dist, Integer.MAX_VALUE);
	
		for(int i=0; i<e; i++) {
			st  =new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[start].add(new Node(end,w));
		}
		
		djistra();
		
		for(int i=1; i<=v; i++) {
			if(dist[i] == Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(dist[i]);
		}
	}
	public static void djistra() {
		boolean [] visited = new boolean[v+1];
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(k,0));
		dist[k] = 0;
		
		while(!pq.isEmpty()) {
			Node a = pq.poll();
			
			visited[a.city] = true;
			
			for(Node b : list[a.city]) {
				if(!visited[b.city]) {
					if(dist[b.city] > dist[a.city] + b.w) {
						dist[b.city] = dist[a.city]+b.w;
						pq.add(new Node(b.city, dist[b.city]));
					}
				}
			}
		}
		
	}
}
class Node implements Comparable<Node>{
	int city,w;

	public Node(int city, int w) {
		super();

		this.city = city;
		this.w = w;
	}

	@Override
	public int compareTo(Node o) {
		return this.w - o.w;
	}
	
	
}