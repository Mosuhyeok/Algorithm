import java.util.*;
class Solution {


    public static void main(String [] args) {
    	
    	int [] works = {4,3,3};
    	long ans = solution(4,works);
    	System.out.println(ans);
    }
    public static long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer>pq = new PriorityQueue<>(Collections.reverseOrder());
       
        for(int i=0; i<works.length; i++) {
        	pq.add(works[i]);
        }
        
        
        
        for(int i=0; i<n; i++) {
        	if(!pq.isEmpty()) {
             	int pop = pq.poll();
            	pop-=1;
            	if(pop!=0) {
            		pq.add(pop);
            	}
        	}
        	else {
        		return 0;
        	}
        }
        
        
        while(!pq.isEmpty()) {
        	long num = pq.poll();
        	num*=num;
        	answer+=num;
        }
        
        
        
        return answer;
    }
}

