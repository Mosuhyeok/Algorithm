import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {

    public static void main(String[] args) {

        String skill = "CBD";

        String [] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};

        int s = solution(skill,skill_trees);
        System.out.println(s);
     }
    public static int solution(String skill, String[] skill_trees) {
        int answer = 0;

        ArrayList<String> list = new ArrayList<>();

        String [] t = skill.split("");
        for(int i=0; i<skill.length(); i++){
            list.add(t[i]);
        }


        for(int i=0; i< skill_trees.length; i++){
            boolean flag = true;

            int idx = 0;
            t = skill_trees[i].split("");

            for(int j=0; j<t.length; j++){
                String tmp = t[j];

                if(list.contains(tmp)){
                    if(list.get(idx).equals(tmp)){
                        idx++;
                    }
                    else{
                        flag = false;
                        break;
                    }
                }
            }
            if(flag) answer++;
        }
        return answer;
    }
}
