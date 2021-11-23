import java.util.*;

public class Solution {


    static ArrayList<Node> list = new ArrayList<>();
    static boolean visited[];
    static int answer = 0;
    static int len = 0;
    static int test[] = new int[3];
    public static void main(String[] args) {
        int d[][] = {{80,20}, {50,40},{30,10}};

        int s = solution(80,d);
        System.out.println(s);
    }
    public static int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        len = dungeons.length;
        for(int i=0; i< dungeons.length; i++){
            list.add(new Node(dungeons[i][0], dungeons[i][1]));
        }

        dfs(0,k,0);

        return answer;
    }
    public static void dfs(int level,int sum, int cnt){
        if(level ==len){
            answer = Math.max(answer,cnt);
            return;
        }

        for(int i=0; i<len; i++){
            if(visited[i]) continue;
            Node a = list.get(i);
            visited[i] = true;
            //test[level] = i;
            if(sumsum-a.y >=0){
                dfs(level+1,sum-a.y,cnt+1);
                visited[i] = false;
            }
            else{
                dfs(level+1,sum,cnt);
                visited[i] = false;
            }
        }
    }
}
class Node{
    int x,y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}