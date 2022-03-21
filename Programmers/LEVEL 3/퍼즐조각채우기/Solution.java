import java.util.*;


class Solution {
	static int n;
	static boolean visited[][];
	static int dx [] = {-1,1,0,0};		// 위 아래 왼 오
	static int dy [] = {0,0,-1,1};
	static int answer = 0;
    public static int solution(int[][] game_board, int[][] table) {
       
        
        
        n = table.length;
        
 
        visited = new boolean[n][n];
        for(int i=0; i<n; i++) {
        	for(int j=0; j<n; j++) {
        		if(table[i][j]==1 && !visited[i][j]) {
        			bfs(i,j,table,game_board);
        		}
        	}
        }
        
        for(int i=0; i<n; i++) {
        	for(int j=0; j<n; j++) {
        		System.out.print(game_board[i][j]+" ");
        	}
        	System.out.println();
        }
        
        
        System.out.println(answer);
        return answer;
    }
    public static int count(ArrayList<Node>list, int[][] game_board) {
    	
    	Queue<Node> q = new LinkedList<>();		// 빈칸 개수 확인
    	
    	boolean visited[][] = new boolean[n][n];
    	int cnt = 0;
    	
    	for(Node a : list) {
    		if(game_board[a.x][a.y] == 0) {
    			q.add(new Node(a.x,a.y));
    			visited[a.x][a.y] = true;
    		}
    	}
    	
    	while(!q.isEmpty()) {
    		Node a= q.poll();
    		cnt++;
    		
    		for(int i=0; i<4; i++) {
    			int nx = a.x+dx[i];
    			int ny = a.y+dy[i];
    			
    			if(isRange(nx,ny) && !visited[nx][ny] && game_board[nx][ny] ==0) {
    				visited[nx][ny] = true;
    				q.add(new Node(nx,ny));
    			}
    		}
    	}
    	return cnt;
    }
    public static void bfs(int x, int y,int table[][], int game_board[][]) {
    	visited[x][y] = true;
    	
    	int x_min = Integer.MAX_VALUE;
    	int y_min = Integer.MAX_VALUE;
    	Queue<Node> q = new LinkedList<>();
    	q.add(new Node(x,y));
    	

    	int left = Integer.MAX_VALUE;
    	int right = 0;
    	int top = Integer.MAX_VALUE;
    	int bottom = 0;
    	
    	while(!q.isEmpty()) {
    		Node a = q.poll();
    		//x_min = Math.min(x_min, a.x);
    		//y_min = Math.min(y_min, a.y);
    		top = Math.min(top, a.x);
    		bottom = Math.max(bottom, a.x);
    		left = Math.min(left, a.y);
    		right = Math.max(right, a.y);
    		
    		for(int i=0; i<4; i++) {
    			int nx = a.x+dx[i];
    			int ny = a.y+dy[i];
    			
    			if(isRange(nx,ny) && table[nx][ny]==1 && !visited[nx][ny]) {
    				visited[nx][ny] = true;
    				q.add(new Node(nx,ny));
    			}
    		}
    	}
    	
    	int row = Math.abs(top-bottom)+1;
    	int col = Math.abs(left-right)+1;
    	
    	int map[][] = new int[row][col];
    	
    	int x_idx=-1;
    	int y_idx = 0;

    	for(int i=top; i<=bottom; i++) {
    		x_idx++;
    		y_idx = 0;
    		for(int j=left; j<=right; j++) {
    			
    			map[x_idx][y_idx++] = table[i][j];
    		}
    	}
    	
    	// 4방향중 한방향만 맞아도 된다 
    	
    	for(int i=0; i<4; i++) {
    		map = rotate(map);
    		
    		if(isPossible(game_board,map)) {
    			
    			for(int a=0; a<map.length; a++) {
    				for(int j=0; j<map[0].length; j++) {
    					if(map[a][j]==1) answer++;
    				}
    			}
    			return;
    		}
    	}
    }
   
    public static boolean isPossible(int game_board[][], int map[][]) {
    	
    	int total = 0;
    	ArrayList<Node>list = new ArrayList<>();
    	for(int i=0; i<map.length; i++) {
    		for(int j=0; j<map[0].length; j++) {
    			if(map[i][j]==1) total++;
    		}
    	}
    	
    	for(int i=0; i<=n-map.length; i++) {
    		for(int j=0; j<=n-map[0].length; j++) {
    			
    			int cnt = 0;
    			list.clear();
    			
    			for(int a =0; a<map.length; a++) {
    				for(int b=0; b<map[0].length; b++) {
    					if(game_board[i+a][j+b] == 0 && map[a][b]==1) {
    						cnt++;
    						list.add(new Node(i+a,j+b));
    					}
    				}
    			}
    			if(cnt == total) {
    				
    				int zero_cnt = count(list, game_board);
    				if(zero_cnt == cnt) {
    					for(int a =0; a<map.length; a++) {
    						for(int b=0; b<map[0].length; b++) {
    							game_board[i+a][j+b] = 1;
    						}
    					}
    					return true;
    				}
    			}
    		}
    	}
    	return false;
    	
    }
    public static int[][] rotate(int [][] map) {
    	
    	int r = map.length;
    	int c = map[0].length;
    	
    	int copy_map[][] = new int[c][r];
    	
    	for(int i=0; i<c; i++) {
    		for(int j=0; j<r; j++) {
    			copy_map[i][j] = map[r-j-1][i];
    		}
    	}
    	return copy_map;
    }
    public static boolean isRange(int x, int y) {
    	if(x>=0 && y>=0 && x<n && y<n) return true;
    	return false;
    }
    public static void main(String [] args) throws Exception{
    	//int [][] game_board = { {1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}};
    	//int [][] table = {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}};

    	
    	int[][] game_board = {{0,0,1,0,1,0,1,0,1,0,1,0,0,1,0,0,0,0}, {1,0,0,0,1,0,1,0,1,0,1,0,0,1,0,1,1,1}, {0,1,1,1,0,0,1,0,1,0,0,1,1,0,1,0,0,0}, {0,0,0,0,1,1,0,0,1,1,0,1,0,0,1,0,0,0}, {0,1,1,1,0,0,1,1,1,1,0,1,1,1,0,1,1,1}, {1,0,1,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0}, {0,0,0,1,1,1,0,0,1,1,0,1,1,1,1,0,0,1}, {1,1,1,0,0,0,1,1,0,0,1,0,0,0,0,1,1,0}, {0,0,1,0,1,1,1,0,0,1,0,1,1,1,1,0,0,0}, {1,1,0,1,1,0,1,1,1,1,0,1,0,0,0,1,1,1}, {0,0,0,0,1,0,0,0,0,1,0,1,0,0,1,0,1,0}, {1,1,1,1,0,1,1,1,1,1,0,1,0,1,0,0,1,0}, {0,0,1,0,0,0,1,0,0,0,1,0,1,0,1,1,0,0}, {1,0,1,1,0,1,1,0,0,0,1,0,0,0,1,0,0,1}, {1,0,0,1,1,0,0,1,1,1,0,1,1,1,0,1,1,0}, {0,1,1,0,0,1,0,1,0,0,1,0,0,0,0,0,1,0}, {0,0,0,1,0,1,0,1,0,0,1,1,1,1,1,1,1,0}, {0,1,0,1,1,0,0,1,0,1,0,0,0,0,0,0,1,0}};
    	int[][] table = {{1,1,1,1,1,1,0,1,0,1,1,0,0,1,0,0,1,0}, {0,0,0,0,0,0,1,1,1,0,1,0,1,1,0,1,1,0}, {1,0,1,1,0,1,0,1,0,1,1,0,1,0,1,1,0,1}, {1,1,0,1,1,1,0,1,0,1,0,1,1,0,1,0,0,1}, {1,1,1,0,0,0,1,0,1,0,1,0,0,1,0,0,1,1}, {0,0,0,1,1,1,0,1,1,1,0,1,1,0,1,0,0,0}, {1,1,1,0,0,0,0,0,1,1,0,1,1,0,1,1,1,1}, {0,0,1,0,1,1,0,1,0,0,1,0,0,1,0,0,0,0}, {1,0,1,0,0,0,0,1,0,1,1,0,1,1,0,1,1,1}, {1,0,1,0,1,1,1,1,0,1,1,0,0,0,1,1,1,0}, {1,1,0,1,0,0,0,0,1,0,0,1,1,1,0,0,0,0}, {0,0,1,1,1,1,0,1,1,0,1,0,0,0,1,1,0,1}, {1,1,0,1,0,0,1,0,0,1,0,1,0,1,0,1,0,1}, {1,1,0,0,1,1,1,0,1,1,0,1,0,1,0,1,0,1}, {0,0,1,1,0,1,1,0,1,0,1,1,0,0,0,1,0,0}, {1,1,1,0,1,0,0,1,0,1,1,0,0,1,0,1,0,1}, {0,0,0,0,1,0,1,1,1,0,0,1,0,1,1,0,1,1}, {0,1,1,1,1,0,0,1,0,0,1,1,0,1,0,0,1,1}};
    	solution(game_board, table);
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