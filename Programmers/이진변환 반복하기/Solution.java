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
        	
        	// 1. x�� ��� 0�� �����Ѵ�
        	String tmp = s.replace("0", "");
        	
        	// ���ŵ� 0�� ����
        	remove +=(s.length() - tmp.length());
        	
        	
        	// 2. �ٲ� ���ڿ��� ���̸� (int) 2�������� ǥ���� ������ �ٲ۴�
        	int len = tmp.length();
        	
        	s = Integer.toBinaryString(len);
        }
        
        System.out.println(cnt+" "+remove);
        answer[0] = cnt;
        answer[1] = remove;
        return answer;
    }
    
}
