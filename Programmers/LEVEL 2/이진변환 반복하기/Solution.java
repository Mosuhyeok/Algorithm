import java.util.*;
class Solution {
	public static void main(String [] args) {
    	
		
		int a[] = solution("01110");
		
		System.out.println(a[0]+" "+a[1]);
	}
    public static int[] solution(String s) {
        int[] answer = new int[2];
        
        int cnt = 0;
        int remove = 0;
        
        
        while(s.length() != 1) {
        	cnt++;
        	
        	// 1. x의 모든 0을 제거한다
        	String tmp = s.replace("0", "");
        	
        	// 제거된 0의 갯수
        	remove +=(s.length() - tmp.length());
        	
        	
        	// 2. 바뀐 문자열의 길이를 (int) 2진법으로 표현된 것으로 바꾼다
        	int len = tmp.length();
        	
        	s = Integer.toBinaryString(len);
        }
        
        System.out.println(cnt+" "+remove);
        answer[0] = cnt;
        answer[1] = remove;
        return answer;
    }
    
}
