import java.io.*;
import java.util.*;
class Solution {
    public static int solution(int n) {
        int answer = n;

        int x = n-1;

        while(x>=1){
            int tmp = n%x;
            if(tmp==1){
                answer = Math.min(answer,x);
            }
            x--;
        }


        return answer;
    }
}