import java.util.*;
class Solution {
	public static void main(String [] args) {
    	
	}
    public static int solution(int N, int[][] road, int K) {
        int answer = 1;
        
        int dist[][] = new int[N+1][N+1];
        
        for(int i=1; i<=N; i++) {
        	Arrays.fill(dist[i], 987654321);
        }
        
        for(int i=0; i<road.length; i++) {
        	int s = road[i][0];
        	int e = road[i][1];
        	int w = road[i][2];
        	
        	
        	if(dist[s][e] > w) {
        		dist[s][e] = w;	
        	}
        	
        	if(dist[e][s] > w) {
            	dist[e][s] = w;	
        	}
        }
        
        for(int k=1; k<=N; k++) {
        	for(int i=1; i<=N; i++) {
        		for(int j=1; j<=N; j++) {
        			
        			if(i==j) continue;
        			
        			dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
        		}
        	}
        }
        
        
        for(int i=2; i<=N; i++) {
        	if(dist[1][i] <= K) {
        		answer++;
        	}
        }
        
        return answer;
    }
}
