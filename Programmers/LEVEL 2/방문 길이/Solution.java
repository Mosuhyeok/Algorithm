import java.util.*;
class Solution {
	public static void main(String [] args) {
    	
		int a = solution("UDU");
		
		System.out.println(a);
	}
    public static int solution(String dirs) {
        int answer = 0;
        int dx [] = {0,0,1,-1};
        int dy [] = {1,-1,0,0};
        
        Map<String,Integer>map = new HashMap<>();
        
        int x = 0;
        int y = 0;
        
        for(int i=0; i<dirs.length(); i++) {
        	char ch = dirs.charAt(i);
        	
        	int nx = x;
        	int ny = y;
        	if(ch=='R') {
        		nx = x+dx[0];
        		ny = y+dy[0];
        	}
        	if(ch=='L') {
        		nx = x+dx[1];
        		ny = y+dy[1];
        	}
        	if(ch=='U') {
        		nx = x+dx[2];
        		ny = y+dy[2];
        	}
        	if(ch=='D') {
        		nx = x+dx[3];
        		ny = y+dy[3];
        	}
        	
        	if(isRange(nx,ny)) {
        		
        		String tmp = nx+""+ny+""+x+""+y+"";
        		String reverse = x+""+y+""+nx+""+ny+"";
        		
        		map.put(reverse, 1);
        		map.put(tmp, 1);
        		x = nx;
        		y = ny;
        		
        		
        	}
        	
        	
        }
        
        answer = map.size()/2;
        return answer;
    }
    public static boolean isRange(int x, int y) {
    	if(Math.abs(x) <=5 && Math.abs(y) <= 5) {
    		return true;
    	}
    	return false;
    }
}
