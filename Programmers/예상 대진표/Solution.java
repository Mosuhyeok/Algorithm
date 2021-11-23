import java.util.*;

public class Solution {


    public static void main(String[] args) {
        int s= solution(8,4,5);
        System.out.println(s);
    }
    public static int solution(int n, int a, int b)
    {
        int answer = 1;

        if(a>b){
            int tmp = a;
            a= b;
            b= tmp;
        }

        loop:
        while (true){
            int cnt =0;
            for(int i=0; i<n/2; i++){
                int x= cnt+1;
                int y = cnt+2;

                if(x==a && y==b) break loop;
                cnt+=2;
            }
            n/=2;

            a=(a+1)/2;
            b=(b+1)/2;
            answer++;
        }
        return answer;
    }
}
