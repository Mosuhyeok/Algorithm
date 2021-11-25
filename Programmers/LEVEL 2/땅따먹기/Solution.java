import java.util.*;
public class Solution {

    public static void main(String[] args) {

    }
    public static int solution(int[][] land) {
        int answer = 0;

        int dp [][] = new int[land.length][4];

        // 1행 일경우
        if(land.length ==1){
            for(int i=0; i<4; i++){
                answer = Math.max(answer,land[0][i]);
            }
            return answer;
        }

        for(int i=0; i<4; i++){
            dp[0][i] = land[0][i];
        }

        for(int i=1; i<land.length; i++){
            for(int j=0; j<4; j++) {

                if (j == 0) {
                    dp[i][j] = Math.max(dp[i - 1][1], Math.max(dp[i - 1][2], dp[i - 1][3])) + land[i][j];
                }
                if (j == 1) {
                    dp[i][j] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][2], dp[i - 1][3])) + land[i][j];
                }
                if (j == 2) {
                    dp[i][j] = Math.max(dp[i - 1][1], Math.max(dp[i - 1][0], dp[i - 1][3])) + land[i][j];
                }
                if (j == 3) {
                    dp[i][j] = Math.max(dp[i - 1][1], Math.max(dp[i - 1][2], dp[i - 1][0])) + land[i][j];
                }
            }
        }

        for(int i=0; i<4; i++){
            answer = Math.max(answer,dp[land.length-1][i]);
        }
        return answer;
    }
}
