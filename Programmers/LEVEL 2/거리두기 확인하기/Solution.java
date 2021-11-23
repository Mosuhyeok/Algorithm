import java.util.*;

public class Solution {

    static ArrayList<Node> people = new ArrayList<>();
    static String copyMap[][] = new String[5][5];
    public static void main(String[] args) {

        String [][] places = {{"OOPOO", "OPOOO", "OOOOO", "OOOOO", "OOOOO"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        // {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}

        int ans [] = solution(places);

        for(int i=0; i<5; i++){
            System.out.println(ans[i]);
        }
     }
    public static int[] solution(String[][] places) {
        int[] answer = new int[5];

        for(int i=0; i< 1; i++){
            people.clear();
            boolean flag = true;
            for(int j=0; j<places[i].length; j++){
                String [] t = places[i][j].split("");
                for(int k=0; k<t.length; k++){
                    copyMap[j][k] = t[k];
                    if(t[k].equals("P")){
                        people.add(new Node(j,k));
                    }
                }
                System.out.println();

                for(int k=0; k< people.size(); k++){
                    for(int l=k+1; l< people.size(); l++){
                        Node a= people.get(k);
                        Node b = people.get(l);


                        int dist = Math.abs(a.x-b.x) + Math.abs(a.y-b.y);

                        if(dist ==1){
                            flag = false;
                            break;
                        }
                        if(dist ==2){
                            if(!check(a,b,places)){
                                flag = false;
                                break;
                            }
                        }
                    }
                    if(!flag) break;
                }
                if(!flag) break;
            }

            if(!flag) answer[i] =0;
            else answer[i] = 1;
        }
        return answer;
    }
    public static boolean check(Node a, Node b, String [][] places){
        int start_x = a.x;
        int start_y= a.y;
        int end_x = b.x;
        int end_y = b.y;


        if(start_x > end_x){
            int tmp = start_x;
            start_x = end_x;
            end_x = tmp;
        }
        if(start_y > end_y){
            int tmp = start_y;
            start_y = end_y;
            end_y = tmp;
        }
        for(int i=start_x; i<=end_x; i++){
            for(int j=start_y; j<=end_y; j++){
                if(copyMap[i][j].equals("O")) return false;
            }
        }
        return true;
    }
}
class Node{
    int x,y;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}