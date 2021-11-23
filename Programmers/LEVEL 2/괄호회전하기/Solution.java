import java.util.*;
class Solution {
	static Stack<Integer>stack = new Stack<>(); 
	public static void main(String [] args) {
    	
		String s = "}}}";
		
		int a = solution(s);
		
		System.out.println(a);
	}
    public static int solution(String s) {
        int answer = 0;
        
        
        int len = s.length()-1;
        
        getStack(s);
        if(isPossible()) {
        	answer++;
        }
        
        for(int i=1; i<len; i++) {
        	s = moveString(s);
        	getStack(s);
        	if(isPossible()) {
        		answer++;
        	}
        }
        
        
        
        return answer;
    }
    public static String moveString(String s) {
    	
    	String str = s.charAt(1)+s.substring(2,s.length())+s.charAt(0);
    	return str;
    }
    public static void getStack(String s) {
    	
    	stack.clear();
    	// ( { [  -1,-2,-3
        // ) } ]   1, 2, 3	
        for(int i=0; i<s.length(); i++) {
        	
        	char ch = s.charAt(i);
        	
        	if(ch=='(') stack.add(-1);
        	if(ch=='{') stack.add(-2);
        	if(ch=='[') stack.add(-3);
        	
        	if(ch==')') stack.add(1);
        	if(ch=='}') stack.add(2);
        	if(ch==']') stack.add(3);
        }
    }
    public static boolean isPossible() {
    	
    	Stack<Integer>tmp = new Stack<>();
    	
    	if(stack.peek() < 0) return false;
    	
    	while(!stack.isEmpty()) {
    		int num = stack.pop();
    		
    		// 1. 뺀 번호가 양수일 경우 ) } ]
    		
    		if(num >0) {
    			tmp.add(num);
    		}
    		
    		// 2. 뺀 번호가 음수인데 해당하는 짝이 없을경우
    		
    		if(num <0 && tmp.isEmpty()) {
    			return false;
    		}
    		
    		if(num <0 && !tmp.isEmpty()) {
    			int op = tmp.pop();
    			
    			if(Math.abs(op) != Math.abs(num)) return false;
    		}
    	}
    	if(!tmp.isEmpty()) {
    		return false;
    	}
    	return true;
    }
}