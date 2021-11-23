import java.util.ArrayList;
import java.util.HashMap;

public class Solution {




    public static void main(String[] args) {

        String record[] = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};

        String [] a = solution(record);
    }
    public static String[] solution(String[] record) {
        HashMap<String,String>map = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        for(int i=0; i<record.length; i++){
            String tmp [] =  record[i].split(" ");
            if(tmp[0].equals("Leave")) continue;

            String userId = tmp[1];
            String nickname = tmp[2];

            map.put(userId,nickname);
        }

        for(int i=0; i<record.length; i++){
            String tmp [] =  record[i].split(" ");

            String order = tmp[0];
            String userId = tmp[1];


            if(order.equals("Enter")){
                list.add(map.get(userId)+"님이 들어왔습니다.");
            }
            if(order.equals("Leave")){
                list.add(map.get(userId)+"님이 나갔습니다.");
            }
        }

        String [] answer = new String[list.size()];

        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
}
