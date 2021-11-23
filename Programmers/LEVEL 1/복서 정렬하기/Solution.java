import java.util.*;
class Solution {

	public static void main(String [] args) {
    	
		int [] weights = {50,82,75,120};
		String [] head2head = {"NLWL","WNLL","LWNW","WWLN"};
		
		
		double rate;
		
		rate = (100*3) / 4;
		
		
		int [] a =  solution(weights, head2head);
		
		for(int i=0; i<a.length; i++) {
			System.out.println(a[i]);
		}

    }
    public static int[] solution(int[] weights, String[] head2head) {
        
        ArrayList<Node>list = new ArrayList<>();
        
        for(int i=0; i<weights.length; i++) {
        	list.add(new Node(0,weights[i],0,0,0,i));
        }
        
        for(int i=0; i<head2head.length; i++) {
        	String info[] = head2head[i].split("");
        	
        	Node a = list.get(i);
        	for(int j=0; j<info.length; j++) {
        		String str = info[j];
        		
        		Node b = list.get(j);
        		
        		if(str.equals("N")) {
        			continue;
        		}
        		if(str.equals("L")) {
        			a.lose++;
        		}
        		if(str.equals("W")) {
        			a.win++;
        			
        			if(a.weight < b.weight) {
        				a.upper_win++;
        			}
        		}
        	}
        }
        
        
        for(int i=0; i<list.size(); i++) {
        	Node a = list.get(i);
        	if(a.win+a.lose == 0 ) continue;
        	a.rate = (double)(100*a.win)/(double)(a.win+a.lose);	
        }
        
        
        for(int i=0; i<list.size(); i++) {
        	Node a = list.get(i);
        	
        	System.out.println(a.win+" " + a.lose+" "+a.upper_win+" " + a.weight +" "+a.rate);
        }
        
        
        Collections.sort(list);
        
        int answer [] = new int[list.size()];
        
        for(int i=0; i<list.size(); i++) {
        	answer[i] = list.get(i).number+1;
        }
        
        return answer;
    }
}
class Node implements Comparable<Node>{
	double rate;
	int weight;
	int win;
	int lose;
	int upper_win;
	int number;
	
	Node(double rate, int weight, int win, int lose, int upper_win, int number){
		this.rate = rate;
		this.weight = weight;
		this.win = win;
		this.lose = lose;
		this.upper_win = upper_win;
		this.number = number;
	}
	
	public int compareTo(Node o) {
		if(this.rate != o.rate) {
			return o.rate>this.rate?1:0;
		}
		else {
			if(this.upper_win != o.upper_win) {
				return o.upper_win - this.upper_win;
			}
			else {
				if(this.weight != o.weight) {
					return o.weight - this.weight;
				}
				else {
					return this.number - o.number;
				}
			}
		}
	}
}