import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
    static String characterName[] = {"A","C","F","J","M","N","R","T"};
    static String str [] = new String[8];
    static boolean visited [] = new boolean[8];
    static ArrayList<Node> list;
    static int answer;
    public static void main(String[] args) {

        int n = 2;

        String data[] = {"N~F=0", "R~T>2"};

        int ans = solution(n,data);
        System.out.println(ans);
    }
    public static int solution(int n, String[] data) {
        answer = 0;

        list = new ArrayList<>();

        for(int i=0; i<data.length; i++){
            String tmp [] = data[i].split("");
            list.add(new Node(tmp[0],tmp[2],tmp[3],Integer.parseInt(tmp[4])));
        }

        dfs(0);
        return answer;
    }

    public static boolean isPossible() {
        // dfs를 통해 뽑아낸 순열이 해당 조건을 만족하는지 체크

        for (int i = 0; i < list.size(); i++) {
            Node node = list.get(i);

            String a = node.a;
            String b = node.b;
            String op = node.op;
            int gap = node.gap;
            int a_pos = 0;
            int b_pos = 0;
            for(int j=0; j< str.length; j++){
                if(str[j].equals(a)){
                    a_pos = j;
                }
                if(str[j].equals(b)){
                    b_pos = j;
                }
            }

            int between = Math.abs(a_pos-b_pos)-1;


            if(op.equals("=")){
                if(gap != between) return  false;
            }
            if(op.equals("<")){
                if(gap<=between) return false;
            }
            if(op.equals(">")){
                if(gap >= between) return false;
            }
        }
        return true;
    }
    // 순열
    public static void dfs(int level){
        if(level== 8){
            if(isPossible()){
                answer++;
            }
            return;
        }

        for(int i=0; i<8; i++){
            if(visited[i]) continue;

            visited[i] = true;
            str[level] = characterName[i];
            dfs(level+1);
            visited[i] = false;
        }
    }
}
class Node{
    String a,b,op;
    int gap;

    Node(String a, String b, String op, int gap){
        this.a= a;
        this.b=b;
        this.op = op;
        this.gap = gap;
    }
}