import java.util.*;

import javax.print.attribute.standard.DateTimeAtProcessing;

import java.io.*;
public class Main {
	public static void main(String [] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n,d,k,c;
		int max_depth = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());	// 초밥수
		d = Integer.parseInt(st.nextToken());	// 초밥 종류
		k = Integer.parseInt(st.nextToken());	// 연속먹는수
		c = Integer.parseInt(st.nextToken());	// 쿠폰번호
		
		int max = 0;
		
		int arr [] = new int[n];
		
		
		for(int i=0; i<n; i++) {
			arr[i]= Integer.parseInt(br.readLine()); 
		}
		
		int right = k;
		
		ArrayList<Integer>list = new ArrayList<>();
		
		for(int i=0; i<k; i++) {
			list.add(arr[i]);
		}
		int visited [] = new int[d+1];
		int cnt = 0;
		
		for(int i=0; i<k; i++) {
			if(visited[list.get(i)] == 0) {
				cnt++;
			}
			visited[list.get(i)]++;
		}
		
		if(visited[c] ==0) {
			max = Math.max(max, cnt+1);
		}
		else {
			max = Math.max(max, cnt);				
		}
		
		
		// i 는 left의 위치
		for(int i=1; i<n; i++) {
			int remove_num = list.remove(0);
			int add_num = arr[(i+k-1)%n];
			list.add(add_num);
			
			visited[remove_num]--;
			if(visited[remove_num]==0) cnt--;
			
			
			if(visited[add_num] ==0) cnt++;
			visited[add_num]++;
			
			if(visited[c]==0) {
				max = Math.max(max, cnt+1);
			}
			else max = Math.max(max, cnt);
		}
		
		System.out.println(max);
	}
}


