import java.util.*;
import java.io.*;

public class Main_bj_1244_스위치켜고끄기_대전_5반_모수혁 {
	static int n;
	static int studentNum;
	static int arr[];

	public static void main(String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1];
		String [] input = br.readLine().split(" ");
		
		for(int i=0; i<input.length; i++) {
			arr[i+1] = Integer.parseInt(input[i]);
		}
		
		studentNum = Integer.parseInt(br.readLine());
		
		for(int i=0; i<studentNum; i++) {
			input = br.readLine().split(" ");
			
			int gender = Integer.parseInt(input[0]);
			int num = Integer.parseInt(input[1]);
			
			// 남자 일 경우
			if(gender ==1) {
				for(int j=num; j<=n; j+=num) {
					swap(j);
				}
				
			}
			// 여자 인 경우
			else {
				int left = num-1;
				int right = num+1;
				
				swap(num);
				
				while(isRange(left,right) && arr[left]==arr[right]) {
					swap(left);
					swap(right);
					left--;
					right++;
				}
			}
	
			
		}
		
		int cnt = 0;
		for(int i=1; i<=n; i++) {
			if(cnt==20) {
				System.out.println();
				System.out.print(arr[i]+" ");
				cnt =0;
			}
			else {
				System.out.print(arr[i]+" ");
			}
			cnt++;
		}
		
	}
	public static boolean isRange(int x, int y) {
		if(x>=1 && y>=1 && x<=n && y<=n) return true;
		return false;
	}
	public static void swap(int num) {
		if(arr[num]==0) {
			arr[num] =1;

		}
		else arr[num]=0;
	}
}
