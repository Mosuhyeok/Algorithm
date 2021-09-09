import java.util.*;
class Solution {

	static int ans;
	static int answer = 0;
	static String [] str = {"A","E","I","O","U"};
	static String [] arr;
	static boolean flag = false;
    public static void main(String [] args) {
    	
    	int s = solution("AAAE");
    	
    	System.out.println(s);

    }
    public static int solution(String word) {
        ans = 0;
        arr = new String[5];
        dfs(0,word);
        return answer;
    }
    
    public static void dfs(int level, String word) {
    	
  
    	if(level == word.length()) {
    		StringBuilder sb = new StringBuilder();
    		
    		for(int i=0; i<level; i++) {
    			sb.append(arr[i]);
    		}
    		if(word.equals(sb.toString())) {
    			answer = ans;
    			return ;
    		}
    		if(level ==5) {
    			return ;
    		}
    	}
    	
    	if(level == 5) {
    		return;
    	}
    	
    	
    	
    	for(int i=0; i<5; i++) {
    		arr[level] = str[i];
    		ans++;
    		if(!flag) {
    	  		dfs(level+1,word); 
    		}
    	}
    	
    	
    }
}
