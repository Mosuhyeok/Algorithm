import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    static int answer = 0;
    static boolean visited [];
    static int arr[];
    static boolean sosu[] = new boolean[3001];
    public static void main(String[] args) {
        int tmp [] = {1,2,7,6,4};
        int s = solution(tmp);
        System.out.println(s);
    }
    public static int solution(int[] nums) {
        visited = new boolean[nums.length];
        arr = new int[3];
        setSosu();
        dfs(0,0,nums);
        return answer;
    }
    public static void dfs(int level, int now, int[] nums){
        if(level ==3){

            int sum = 0;
            for(int i=0; i<3; i++){
                sum+=arr[i];
            }

            System.out.println(sum);
            if(sosu[sum]){
                answer++;
            }
            return ;
        }

        for(int i=now; i<nums.length; i++){

            if(visited[i]) continue;

            visited[i] = true;
            arr[level] = nums[i];
            dfs(level+1,i+1,nums);
            visited[i] = false;
        }
    }
    public static void setSosu(){
        Arrays.fill(sosu,true);

        sosu[0] = false;
        sosu[1] = false;

        for(int i=2; i<= 3000; i++){
            for(int j=i*i ; j<=3000; j+=i){
                sosu[j] = false;
            }
        }
    }
}
