import java.util.*;
class Solution {
	
	static int map[][];
	static int answer[];
	public static void main(String [] args) {
    	
		int rows = 6;
		int columns = 6;
		
		int [][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};
		
		int [] a = solution(rows, columns, queries);

    }
    public static int[] solution(int rows, int columns, int[][] queries) {
        answer = new int[queries.length];
        map = new int [rows+1][columns+1];
        
        for(int i=1; i<=rows;i++) {
        	for(int j=1; j<=columns; j++) {
        		map[i][j] = (i-1)*(columns)+j;
        	}
        }
        
        for(int i=0; i<queries.length; i++) {
        	int start_x = queries[i][0];
        	int start_y = queries[i][1];
        	
        	int end_x = queries[i][2];
        	int end_y = queries[i][3];
        	
        	rotate(start_x,start_y,end_x,end_y,rows,columns,i);
        }
        return answer;
    }
    public static void rotate(int start_x, int start_y, int end_x, int end_y, int r, int c, int idx) {
    	
    	int min = Integer.MAX_VALUE;
    	int copy_map[][] = new int[r+1][c+1];
    	
    	for(int i=1; i<=r; i++) {
    		for(int j=1; j<=c; j++) {
    			copy_map[i][j] = map[i][j];
    		}
    	}
    	
    	for(int i=end_y; i>start_y; i--) {
    		copy_map[start_x][i] = map[start_x][i-1];
    		min = Math.min(min, copy_map[start_x][i]);
    	}
    	
    	for(int i=end_x; i>start_x; i--) {
    		copy_map[i][end_y] = map[i-1][end_y];
    		min = Math.min(min, copy_map[i][end_y]);
    	}
    	
    	for(int i=start_y; i<end_y; i++) {
    		copy_map[end_x][i] = map[end_x][i+1];
    		min = Math.min(min, copy_map[end_x][i]);
    	}
    	for(int i=start_x; i< end_x; i++) {
    		copy_map[i][start_y] = map[i+1][start_y];
    		min = Math.min(min, copy_map[i][start_y]);
    	}
    	
    	
    	for(int i=1; i<=r; i++) {
    		for(int j=1; j<=c; j++) {
    			map[i][j] = copy_map[i][j];
    		}
    	}
    	
    	answer[idx] = min;
    }
}