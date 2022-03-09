import java.io.*;
import java.util.*;
class Solution {
    public static int[] solution(int n, long left, long right) {
        int[] answer = {};
        ArrayList<Long> list = new ArrayList<>();

        long tmp = 1;
        long before = (left-1)/n+1;
        for(long i=left; i<=right; i++){

            long step = (i/n)+1;    // i번째 단계
            long value = (i%n);

            if(step!=before){
                tmp = 1;
            }


            if(value >= step){
                list.add(value+1);
            }
            else{
                list.add(step);
            }
        }
        answer = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            long  a = list.get(i);
            answer[i] = (int)a;
        }
        return answer;
    }

}