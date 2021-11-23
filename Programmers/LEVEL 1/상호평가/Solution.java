import java.util.*;
class Solution {




    public static void main(String [] args) {
    	
    	int s [][]= {{100,90,98,88,65},{50,45,99,85,77},{47,88,95,80,67},{61,57,100,80,65},{24,90,94,75,65}};
    	
    	String a = solution(s);
    	System.out.println(a);
    }
    public static String solution(int[][] scores) {
        StringBuilder sb = new StringBuilder();
    	String answer = "";
        
        int [] myScore = new int[scores.length];
        int [] avg = new int[scores.length];
        for(int i=0; i<scores.length; i++) {
        	myScore[i] = scores[i][i];
        }

        ArrayList<Integer>list = new ArrayList<>();
        for(int i=0; i<scores.length; i++) {
        	int tmp = 0;
        	list.clear();
        	for(int j=0; j<scores.length; j++) {
        		if(i==j) continue;
        		
        		list.add(scores[j][i]);
        		tmp+=scores[j][i];
        	}
        	
        	Collections.sort(list);
        	if(myScore[i] >= list.get(0) && myScore[i] <= list.get(list.size()-1)) {
        		tmp+=myScore[i];
        		avg[i] = tmp/scores.length;
        	}
        	else {
        		avg[i] = tmp/(scores.length-1);
        
        	}
        }
        
        

        
        for(int i=0; i<avg.length; i++) {
        	
        	int score = avg[i];
        	
        	if(score>=90) {
        		sb.append("A");
        	}
        	else if(score>=80) {
        		sb.append("B");
        	}
        	else if(score>=70) {
        		sb.append("C");
        	}
        	else if(score>=50) {
        		sb.append("D");
        	}
        	else {
        		sb.append("F");
        	}
        	
        }
        
        return sb.toString();
    }
}
