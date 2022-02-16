import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int n = Integer.parseInt(br.readLine());
    	
    	
    	int divide = n/5;
    	
    	// 1. 5로만 나눠질떄
    	if(n%5==0) {
    		System.out.println(n/5);
    		return ;
    	}

		n%=5;
		for(int i=divide; i>=1; i--) {
			if(n%3==0) {
				int tmp = i + n/3;
				System.out.println(tmp);
				return ;
			}
			n+=5;
		}
		if(n%3==0) {
    		System.out.println(n/3);
    		return ;
    	}
    	
    	System.out.println(-1);
    }
}

