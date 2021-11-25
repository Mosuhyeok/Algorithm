import java.util.*;
public class Solution {

    public static void main(String[] args) {

    }
    public static boolean solution(String s) {
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push('(');
            }
            else{
                if(stack.isEmpty()){
                    return false;
                }
                stack.pop();
            }
        }
        if(stack.isEmpty()) return true;
        return false;
    }
}
