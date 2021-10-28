import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Solution {
    public static void main(String[] args) {

        int A [] ={5,1,3,7};
        int B [] = {2,2,6,8};

        int std =  solution(A,B);
     }
    public static int solution(int[] A, int[] B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        int a_idx = A.length-1;
        int b_idx = B.length-1;


        while (a_idx >= 0){

            int a_num = A[a_idx];
            int b_num = B[b_idx];

            if(b_num > a_num){
                answer++;
                b_idx--;
                a_idx--;
            }
            else {
                a_idx--;
            }

        }

        return answer;


    }
}