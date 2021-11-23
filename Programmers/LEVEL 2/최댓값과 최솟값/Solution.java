import java.util.*;
class Solution {
	public static void main(String [] args) {
    	
	}
    public static int solution(int []A, int []B)
    {
        int answer = 0;

        
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        
        int start = 0;
        int end = B.length-1;
        
        
        
        
        while(start<end) {
        	answer+=(A[start++] * B[end--]);
        }
        
        
        return answer;
    }
}
