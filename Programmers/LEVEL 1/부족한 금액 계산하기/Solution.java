import java.util.*;
class Solution {




    public static void main(String [] args) {
    	

    }
    public static long solution(int price, int money, int count) {
        long answer = -1;

        
        int tmp = 0;
        
        for(int i=1; i<=count; i++) {
        	tmp+=price*i;
        }
        
        if(money-tmp>=0) {
        	return 0;
        }
        else {
        	return tmp-money;
        }
    }
}
