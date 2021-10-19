import java.util.ArrayList;

public class Solution {




    public static void main(String[] args) {
        String s = "aa";

        int ans = solution(s);
        System.out.println(ans);
    }
    public static int solution(String s) {
        int answer = 0;
        int min = Integer.MAX_VALUE;

        ArrayList<String> list = new ArrayList<>();
        for(int i=1; i<=s.length()/2; i++){
            StringBuilder sb = new StringBuilder();
            list.clear();
            int cnt = 1;


            for(int j=0; j<s.length(); j+=i){
                if(j+i < s.length()){
                    String tmp = s.substring(j,j+i);
                    list.add(tmp);
                }
                else{
                    list.add(s.substring(j,s.length()));
                }
            }

            String tmp = list.get(0);
            for(int j=1; j<list.size(); j++){
                if(tmp.equals(list.get(j))){
                    cnt++;  // 같은게 몇번 중첩인지
                    continue;
                }

                else{
                    if(cnt >1){
                        sb.append(cnt+tmp);
                    }
                    else{
                        sb.append(tmp);
                    }
                    cnt = 1;
                    tmp = list.get(j);
                }
            }
            if(cnt>1){
                sb.append(cnt+tmp);
            }
            else{
                sb.append(tmp);
            }
            min = Math.min(min,sb.length());
        }


        if(min == Integer.MAX_VALUE) min = 1;
        return min;
    }
}
