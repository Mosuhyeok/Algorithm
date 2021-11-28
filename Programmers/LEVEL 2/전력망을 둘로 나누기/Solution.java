import java.util.*;
public class Solution {
    public static boolean visited [];
    public static ArrayList<Integer> list [];
    public static void main(String[] args) {


        int [][] wires = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};

        int s= solution(9,wires);
        System.out.println(s);

    }
    public static int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        visited = new boolean[n+1];
        list = new ArrayList[n+1];

        for(int i=0; i<=n; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i< wires.length; i++){
            int cityA = wires[i][0];
            int cityB = wires[i][1];

            list[cityA].add(cityB);
            list[cityB].add(cityA);
        }

        for(int i=0; i< wires.length; i++){
            int cityA = wires[i][0];
            int cityB = wires[i][1];

            int cityACnt = bfs(cityA,cityA,cityB,n);
            int cityBCnt = bfs(cityB,cityA,cityB,n);

            answer = Math.min(answer,Math.abs(cityACnt-cityBCnt));
        }
        return answer;
    }
    public static int bfs(int now, int x , int y, int n){

        visited = new boolean[n+1];
        visited[x] = true;
        visited[y] = true;

        Queue<Integer> q = new LinkedList<>();
        q.add(now);
        int cnt = 0;
        while (!q.isEmpty()){
            int a = q.poll();
            cnt++;
            for(int num : list[a]){
                if(!visited[num]){
                   visited[num] = true;
                   q.add(num);
                }
            }
        }

        return cnt;
    }
}
