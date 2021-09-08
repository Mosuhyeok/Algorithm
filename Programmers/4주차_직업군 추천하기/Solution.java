import java.util.*;
class Solution {




    public static void main(String [] args) {
    	
    	String [] table = {"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"};
    	String [] languages = {"JAVA", "JAVASCRIPT"};
    	int [] preference = {7,5};
    	
    	String a = solution(table, languages, preference);
    	System.out.println(a);
    }

    public static String solution(String[] table, String[] languages, int[] preference) {
        String answer = "";
        
        ArrayList<Integer>max_idx = new ArrayList<>();
        ArrayList<String>list = new ArrayList<>();
        int max = 0;
      
        for(int i=0; i<table.length; i++) {
        	int tmp_score = 0;
        	String [] info = table[i].split(" ");
        	
        	for(int j=0; j<languages.length; j++) {
        		String language = languages[j];
        		int language_prefernece = preference[j];
        		
        		for(int k=1; k<info.length; k++) {
        			
        			if(info[k].equals(language)) {
        				tmp_score += (5-(k-1)) * language_prefernece;
        			}
        		}
        		
        	}
        	if(max < tmp_score) {
        		max = tmp_score;
        		max_idx.clear();
        		max_idx.add(i);
        	}
        	if(max == tmp_score) {
        		max_idx.add(i);
        	}
        }
        
        for(int i=0; i< max_idx.size(); i++) {
        	int num = max_idx.get(i);
        	
        	list.add(table[num].split(" ")[0]);
        }
        Collections.sort(list);
        return list.get(0);
    }
}
