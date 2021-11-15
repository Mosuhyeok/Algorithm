import java.io.*;
import java.util.*;
public class Main {

    static final int FIRST = 1;
    static final int SECOND = 2;
    static final int THIRD = 3;
    static final int FOURTH = 4;
    static final int FIFTH = 5;
    static final int SIXTH = 6;

    static int n,m,k;
    static int dice[] = new int[7];
    static int map[][];
    static int dx [] ={0,0,1,-1};
    static int dy [] = {1,-1,0,0};
    static int score = 0;
    static int dir;
    static boolean visited[][];
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        String [] t=  br.readLine().split(" ");
        n = Integer.parseInt(t[0]);
        m = Integer.parseInt(t[1]);
        k = Integer.parseInt(t[2]);

        map = new int[n+1][m+1];
        visited = new boolean[n+1][m+1];
        for(int i=1; i<=n; i++){
            t = br.readLine().split(" ");
            for(int j=1; j<=m; j++){
                map[i][j] = Integer.parseInt(t[j-1]);
            }
        }
        dir = 0;

        setDice();
        Node node = new Node(1,1);

        while (k-- >0){

            int nx = node.x+dx[dir];
            int ny = node.y+dy[dir];

            if(isRange(nx,ny)){
                node.x=  nx;
                node.y= ny;
            }
            else {
                if(dir==0){
                    dir = 1;
                }
                else if(dir==1){
                    dir = 0;
                }
                else if(dir==2){
                    dir = 3;
                }
                else if(dir==3){
                    dir=2;
                }
                nx = node.x+dx[dir];
                ny= node.y+dy[dir];

                node.x= nx;
                node.y=ny;
            }

            score+=map[nx][ny]*getScore(nx,ny);
            rotateDice(dir);
            dir = getDir(dice[THIRD], map[nx][ny],dir);
        }
        System.out.println(score);
    }


    public static void rotateDice(int dir){
        int tmp;

        if (dir ==0){       // 동쪽으로 주사위를 굴렸을 때
            tmp = dice[FOURTH];
            dice[FOURTH] = dice[THIRD];
            dice[THIRD] = dice[SECOND];
            dice[SECOND] = dice[FIRST];
            dice[FIRST] = tmp;
        }
        if(dir==1){         // 서쪽으로
            tmp = dice[FIRST];
            dice[FIRST] = dice[SECOND];
            dice[SECOND] = dice[THIRD];
            dice[THIRD] = dice[FOURTH];
            dice[FOURTH] = tmp;
        }
        if(dir==2){         // 남쪽으로
            tmp = dice[FIRST];
            dice[FIRST] = dice[SIXTH];
            dice[SIXTH] = dice[THIRD];
            dice[THIRD] = dice[FIFTH];
            dice[FIFTH] = tmp;
        }
        if(dir==3){         // 북쪽으로
            tmp = dice[FIRST];
            dice[FIRST] = dice[FIFTH];
            dice[FIFTH] = dice[THIRD];
            dice[THIRD] = dice[SIXTH];
            dice[SIXTH] = tmp;
        }
    }

    public static int getDir(int A, int B, int dir){

        if(A>B){
            // 이동방향 시계방향 90도 회전

            switch (dir){
                case 0:
                    return 2;
                case 1:
                    return 3;
                case 2:
                    return 1;
                case 3:
                    return 0;
            }

        }
        else if(A<B){
            // 반시계로 90도
            switch (dir){
                case 0:
                    return 3;
                case 1:
                    return 2;
                case 2:
                    return 0;
                case 3:
                    return 1;
            }
        }
        else{
            return dir;
        }
        return 1000000;
    }
    public static int getScore(int x, int y) {
        int cnt = 0;
        visited = new boolean[n+1][m+1];
        Queue<Node> q = new LinkedList<>();

        q.add(new Node(x,y));
        visited[x][y]= true;
        while (!q.isEmpty()){
            Node a=  q.poll();
            cnt++;
            for(int i=0; i<4; i++){
                int nx = a.x+dx[i];
                int ny = a.y+dy[i];

                if(isRange(nx,ny) && !visited[nx][ny] && map[nx][ny]==map[x][y]){
                    q.add(new Node(nx,ny));
                    visited[nx][ny] = true;
                }
            }
        }
        return cnt;
    }
    public static boolean isRange(int x, int y){
        if(x>=1 && y>=1 && x<=n && y<=m){
            return true;
        }
        return false;
    }
    public static void setDice(){
        dice[FIRST] =1;     // 제일 윗면
        dice[SECOND] = 3;   // 오른쪽
        dice[THIRD] = 6;    // 제일 밑면
        dice[FOURTH] = 4;   // 왼쪽
        dice[FIFTH] = 5;    // 뒷면
        dice[SIXTH] = 2;    // 앞면
    }

}
class Node{
    int x,y;

    Node(int x, int y){
        this.x=x;
        this.y=y;
    }
}
