import java.util.*;
import java.io.*;

class Solution {
    static char map[][];
    static int n,m;
    static int dx[] = {-1,0,1,0};
    static int dy [] = {0,1,0,-1};
    static boolean visited[][][];
    static ArrayList<Integer>list = new ArrayList<>();
    public static int[] solution(String[] grid) {
        n = grid.length;
        m = grid[0].length();

        map = new char[n][m];
        visited = new boolean[n][m][4];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                map[i][j] = grid[i].charAt(j);
            }
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                for(int k=0; k<4; k++){
                    bfs(i,j,k);
                }
            }
        }


        for(int i=list.size()-1; i>=0; i--){
            if(list.get(i) == 0 )list.remove(i);
        }



        Collections.sort(list);
        int[] answer = {};
        answer = new int[list.size()];

        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
    public static void bfs(int x, int y, int dir){
        Queue<Node> q = new LinkedList<>();


        q.add(new Node(x,y,dir));
        int cnt = 0;

        while (!q.isEmpty()){
            Node a = q.poll();
            if(!isRange(a.x,a.y)){
                if (a.dir==0) a.x = n-1;
                if (a.dir==1) a.y = 0;
                if (a.dir==2) a.x =0;
                if (a.dir==3){
                    a.y = m-1;
                }
            }
            int tmp_dir = a.dir;
            if(visited[a.x][a.y][a.dir]) break;
            visited[a.x][a.y][a.dir] = true;
            if(map[a.x][a.y] =='L'){
                tmp_dir--;
                if(tmp_dir==-1) tmp_dir=3;
            }
            if(map[a.x][a.y] == 'R'){
                tmp_dir++;
                if(tmp_dir==4) tmp_dir=0;
            }

            int nx = a.x+dx[tmp_dir];
            int ny = a.y+dy[tmp_dir];

            q.add(new Node(nx,ny,tmp_dir));
            cnt++;
        }
        list.add(cnt);
    }
    public static boolean isRange(int x, int y){
        if(x>=0 && y>=0 && x<n && y<m) return true;
        return false;
    }

    public static void main(String[] args) {
        String [] grid = {"SL","LR"};
        solution(grid);
    }
}
class Node{
    int x,y,dir;

    public Node(int x, int y,int dir) {
        this.x = x;
        this.y = y;
        this.dir=dir;
    }
}