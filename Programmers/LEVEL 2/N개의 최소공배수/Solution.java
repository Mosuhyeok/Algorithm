import java.util.*;
public class Solution {

    public static void main(String[] args) {
        int arr [] = {2,6,8,14};

        int s=  solution(arr);
        System.out.println(s);
    }

    public static int solution(int[] arr) {

        int ans = arr[0];
        if(arr.length==1){
            return arr[0];
        }

        int gcd = arr[0];

        for(int i=1; i<arr.length; i++){
            gcd = solve(ans,arr[i]);
            ans = (ans*arr[i])/gcd;
        }

        return ans;
    }
    public static int solve(int a, int b){

        int x = Math.max(a,b);
        int y = Math.min(a,b);

        while (y!=0){
            int tmp = x;
            x = y;
            y = tmp%y;
        }
        return x;
    }
}
