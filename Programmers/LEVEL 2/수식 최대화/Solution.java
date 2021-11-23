import java.util.*;

public class Solution {

    static ArrayList<Long> number = new ArrayList<>();
    static ArrayList<String> op = new ArrayList<>();
    static boolean visited [];
    static String arr[];
    static String exp;
    static long answer = 0;
    static String dfsOp[] = {"+","-","*"};
    public static void main(String[] args) {
        String s = "100-200*300-500+20";
        System.out.println(solution(s));
    }
    public static long solution(String expression) {

        visited = new boolean[3];
        arr = new String[3];
        exp = expression;
        String [] t = expression.split("");

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<t.length; i++){
            String str = t[i];

            if(str.equals("*") || str.equals("-") || str.equals("+")){
                number.add(Long.parseLong(sb.toString()));
                sb = new StringBuilder();
                op.add(str);
            }
            else sb.append(str);
        }
        number.add(Long.parseLong(sb.toString()));
        dfs(0);
        return answer;
    }
    public static void dfs(int level){
        if(level ==3){
            answer = Math.max(answer,cal());
            return;
        }

        for(int i=0; i<3; i++){
            if(!visited[i]){
                visited[i] = true;
                arr[level] = dfsOp[i];
                dfs(level+1);
                visited[i] = false;
            }
        }
    }
    public static Long cal(){

        ArrayList<Long> tmpNumber = new ArrayList<>();
        ArrayList<String> tmpOp = new ArrayList<>();

         for(Long num : number){
            tmpNumber.add(num);
        }
        for(String str : op){
            tmpOp.add(str);
        }

        for(int i=0; i<3; i++){
            String operation = arr[i];

            for(int j=0; j<tmpOp.size(); j++){
                if(tmpOp.get(j).equals(operation)){
                    Long numA = tmpNumber.remove(j);
                    Long numB = tmpNumber.remove(j);
                    Long numC = 0L;
                    if(operation.equals("*")){
                        numC = numA*numB;
                    }
                    else if(operation.equals("+")){
                        numC = numA+numB;
                    }
                    else{
                        numC = numA-numB;
                    }
                    tmpNumber.add(j,numC);
                    tmpOp.remove(j);
                    j--;
                }
            }
        }
        return Math.abs(tmpNumber.get(0));
    }

}
