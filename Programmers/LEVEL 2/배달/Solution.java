import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {

    public static void main(String[] args) {


    }
    public int solution(int N, int[][] road, int K) {

        int dist[][] = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            Arrays.fill(dist[i],987654321);
        }
        ArrayList<Node> list = new ArrayList<>();

        for(int i=0; i<road.length; i++){
            int a = road[i][0];
            int b = road[i][1];
            int w = road[i][2];

            dist[a][b] = Math.min(dist[a][b],w);
            dist[b][a] = Math.min(dist[b][a],w);
        }

        for(int k=1; k<=N; k++){
            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    if(i==j) continue;
                    dist[i][j] = Math.min(dist[i][k]+dist[k][j],dist[i][j]);
                }
            }
        }


        int answer = 0;

        for(int i=2; i<=N; i++){
            if(dist[1][i] <= K){
                answer++;
            }
        }

        return answer;
    }
}
class Node{
    int a,b,w;
    Node(int a, int b, int w){
        this.a = a;
        this.b = b;
        this.w = w;
    }
}