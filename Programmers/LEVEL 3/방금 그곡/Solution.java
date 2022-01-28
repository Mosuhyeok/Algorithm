import java.util.*;
public class Solution {


    public static String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        
        ArrayList<Node>list = new ArrayList<>();
        m = change(m);
        
        for(int i=0; i<musicinfos.length; i++) {
        	StringBuilder sb = new StringBuilder();
        	String[] tmp = musicinfos[i].split(",");
        	int startHour = Integer.parseInt(tmp[0].split(":")[0]);
        	int startMin = Integer.parseInt(tmp[0].split(":")[1]);
        	int endHour = Integer.parseInt(tmp[1].split(":")[0]);
        	int endMin = Integer.parseInt(tmp[1].split(":")[1]);
        	int time = (endHour-startHour)*60 + endMin-startMin;
        	String title = tmp[2];
        	String music = change(tmp[3]);
        	Node node = new Node(time,title,music,i+1);
        	
        	if(music.length() == time) sb.append(music);
        	if(music.length() > time) sb.append(music.substring(0,time));
        	if(music.length() < time) {
        		sb.append(music);
        		int idx = 0;
        		while(sb.length() != time) {
        			sb.append(music.charAt(idx));
        			idx++;
        			if(idx == music.length()) idx =0;
        		}
        	}
        	if(sb.toString().contains(m)) list.add(node);
        }
        
        if(list.size()==0) return answer;
        Collections.sort(list);
        return list.get(0).title;
    }


public static void main(String [] args) {
	String [] t = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
	String s = solution("ABC", t);
	System.out.println(s);
}
public static String change(String str) {
	
	ArrayList<String>list = new ArrayList<>();
	for(int i=0; i<str.length(); i++) {
		if(str.charAt(i)=='#') {
			list.remove(list.size()-1);
			if(str.charAt(i-1)=='C') list.add("c");
			if(str.charAt(i-1)=='D') list.add("d");
			if(str.charAt(i-1)=='F') list.add("f");
			if(str.charAt(i-1)=='G') list.add("g");
			if(str.charAt(i-1)=='A') list.add("a");
		}
		else list.add(Character.toString(str.charAt(i)));
	}
	
	StringBuilder sb = new StringBuilder();
	for(int i=0; i<list.size(); i++) {
		sb.append(list.get(i));
	}
	return sb.toString();
}
}
class Node implements Comparable<Node>{
	int time;
	String title;
	String music;
	int idx;
	public Node(int time, String title, String music, int idx) {
		this.time = time;
		this.title = title;
		this.music = music;
		this.idx = idx;
	}
	@Override
	public int compareTo(Node o) {
		if(this.time ==o.time) return this.idx - o.idx;
		return o.time - this.time;
	}
}
