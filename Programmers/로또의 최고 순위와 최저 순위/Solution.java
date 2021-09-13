import java.util.*;
class Solution {

	public static void main(String [] args) {
    	


    }
    public static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        
        boolean [] visited = new boolean[lottos.length];
        
        
        int total_cnt = 0;
        int zero_cnt = 0;
        for(int i=0; i<lottos.length; i++) {
        	
        	if(lottos[i] ==0) {
        		zero_cnt++;
        		continue;
        	}
        	
        	
        	for(int j=0; j<win_nums.length; j++) {
        		if(lottos[i] == win_nums[j]) {
        			visited[i] = true;
        			total_cnt ++;
        		}
   
        	}
        }
        
        int min = total_cnt;
        int max = total_cnt + zero_cnt;
        
        answer[0] = get_result(max);
        answer[1] = get_result(min);
        
        return answer;
    }
    public static int get_result(int num) {
    	if(num ==6) {
    		return 1;
    	}
    	if(num==5) {
    		return 2;
    	}
    	if(num ==4) {
    		return 3;
    	}
    	if(num ==3) {
    		return 4;
    	}
    	if(num ==2) {
    		return 5;
    	}
    	return 6;
    }
}