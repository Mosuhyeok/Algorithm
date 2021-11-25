import java.util.*;
public class Solution {

    public static void main(String[] args) {

        int [][] arr1 = {{1,4},{3,2},{4,1}};
        int [][] arr2 = {{3,3},{3,3}};

        int ans[][] = solution(arr1,arr2);

        for(int i=0; i<ans.length; i++){
            for(int j=0; j<ans[0].length; j++){
                System.out.print(ans[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];

        for(int i=0; i<arr1.length; i++){
            int sum =0;
            for(int j=0; j<arr2[i].length; j++){
                for(int k=0; k<arr1[i].length; k++){
                    sum+=arr1[i][k]*arr2[k][j];
                }
                answer[i][j] = sum;
                sum = 0;
            }
        }
        return answer;
    }
}
