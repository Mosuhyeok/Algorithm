import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {

    public static void main(String[] args) {

        int d [] = {2,2,3,3};
        int budget = 10;
        int a = solution(d,budget);
    }

    public static int solution(int[] d, int budget) {
        int answer = 0;

        Arrays.sort(d);
        int idx = 0;

        while (idx < d.length && budget - d[idx] >= 0){
            answer++;
            budget-=d[idx++];
        }

        return answer;
    }
}
