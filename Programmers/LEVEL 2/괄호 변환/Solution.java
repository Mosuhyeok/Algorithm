import java.util.*;

public class Solution {


    public static void main(String[] args) {
        String p = "()))((()";
        String s= solution(p);
    }

    public static String solution(String p) {
        String answer = "";
        String str = p;

        while (true){
            if(isCorrectString(str)) break;
            str = solve(str);
        }
        return answer;
    }
    public static String solve(String str){
        // 1. 입력이 빈 문자열인 경우
        if(str.length() ==0) return "";
        else{
            // 2
            Node uu = balanceString(str,0,true);
            String u = uu.str;

            //3
            Node vv = balanceString(str,uu.idx+1, false);
            String v = vv.str;

            if(isCorrectString(u)){
                String result = solve(v);
                return u+result;
            }
            else{
                StringBuilder tmp = new StringBuilder();
                // 4-1
                tmp.append("(");
                // 4-2
                String result = solve(v);
                tmp.append(result);
                // 4-3
                tmp.append(")");
                // 4-4
                String removeU = u.substring(1,u.length()-1);
                for(int i=0; i<removeU.length(); i++){
                    if(removeU.charAt(i) == '(') tmp.append(")");
                    else tmp.append("(");
                }
                return tmp.toString();
            }
        }
    }
    public static boolean isCorrectString(String str){
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) == '('){
                stack.push('(');
            }
            else{
                if(stack.isEmpty()) return false;
                stack.pop();
            }
        }
        if(stack.isEmpty()) return true;
        return false;
    }
    public static Node balanceString(String str, int idx, boolean flag){
        int left = 0;
        int right = 0;
        StringBuilder tmp = new StringBuilder();

        if(flag){       // u를 구하는 과정
            for(int i=0; i<str.length(); i++){
                if(str.charAt(i) =='('){
                    left++;
                    tmp.append("(");
                }
                else{
                    right++;
                    tmp.append(")");
                }
                if(left ==right){
                    return new Node(tmp.toString(),i);
                }
            }
        }
        else{       // w를 구하는 과정
            return new Node(str.substring(idx,str.length()),0);
        }
        return new Node("if this sentence come logic error",10000);
    }
}
class Node{
    String str;
    int idx;

    public Node(String str, int idx) {
        this.str = str;
        this.idx = idx;
    }
}