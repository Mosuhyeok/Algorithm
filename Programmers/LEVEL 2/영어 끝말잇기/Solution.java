import java.util.*;
class Solution {
	public static void main(String [] args) {
    	
		int n = 2;
		
		String [] words = {"land","dream","mom","mom","ror"};
		int a[] = solution(n, words);
		
		System.out.println(a[0]+" "+a[1]);
	}
    public static int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        
        char before_last = words[0].charAt(words[0].length()-1);
        
        ArrayList<String>list = new ArrayList<>();
        
        list.add(words[0]);
        int idx = n+1;
        int cnt[] = new int[n+1];
        
        cnt[1]++;
        
        
        
        boolean flag = true;
        for(int i=1; i<words.length; i++){
        	
        	
        	idx++;
        	if(idx>n) {
        		idx-=n;
        	}
        	cnt[idx]++;
        	String str = words[i];
        	
        	if(str.length() ==1) {
        		flag = false;
        		break;
        	}
        	if(list.contains(str)) {
        		flag = false;
        		break;
        	}
        	
        	char now_first = str.charAt(0);
        	char now_last = str.charAt(str.length()-1);
        	
        	if(before_last == now_first) {
        		before_last = now_last;
        		list.add(str);
        	}
        	else {
        		flag = false;
        		break;
        	}
        }
        
        if(flag) {
        	return answer;
        }
        else {
        	answer[0] = idx;
        	answer[1] = cnt[idx];
        }
        return answer;
    }
}
