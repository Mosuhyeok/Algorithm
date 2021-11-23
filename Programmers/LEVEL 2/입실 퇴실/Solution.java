import java.util.*;
class Solution {
	
	public static void main(String [] args) {
    	int enter[] = {1,4,2,3};
    	int leave[] = {2,1,3,4};
    	
    	int a[] = solution(enter, leave);
    	
    	for(int i=0; i<a.length; i++) {
    		System.out.println(a[i]);
    	}
	}
    public static int[] solution(int[] enter, int[] leave) {
        int[] answer = new int [enter.length];
        
        ArrayList<Integer> old_list = new ArrayList<>();
        ArrayList<Integer> new_list = new ArrayList<>();
        
        
        old_list.add(enter[0]);
        int idx = 1;
        
        for(int i=0; i<leave.length; i++) {
        	int leave_person = leave[i];
        	int cnt = 0;
        	
        	if(!old_list.contains(leave_person)) {
        		
        		while(idx < leave.length) {
        			int enter_person = enter[idx];
        			new_list.add(enter_person);
        			cnt++;
        			
        			if(enter_person == leave_person) {
        				idx++;
        				break;
        			}
        			idx++;
        		}
        		
        		
        	}
        	
        	
        	ArrayList<Integer>mix_list = new ArrayList<>();
        	
        	int old_size = old_list.size();
        	for(int a : old_list) {
        		mix_list.add(a);
        	}
        	
        	for(int a: new_list) {
        		mix_list.add(a);
        	}
        	
        	for(int j=0; j<mix_list.size(); j++) {
        		
        		if(j <old_size) {
        			answer[mix_list.get(j)-1] +=cnt;
        		}
        		else {
        			answer[mix_list.get(j)-1]+=mix_list.size()-1;
        		}
        	}
        	
        	
        	for(int j=0; j<mix_list.size(); j++) {
        		if(mix_list.get(j) ==leave_person) {
        			mix_list.remove(j);
        		}
        	}
        	
        	old_list.clear();
        	
        	for(int a: mix_list) {
        		old_list.add(a);
        	}
        	new_list.clear();
        }
        
        
        return answer;
    }
}