import java.util.*;

public class Solution {


    public static void main(String[] args) {

       long [] nunmbers = {2,7};


        long [] a = solution(nunmbers);
    }
    public static long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for(int i=0; i< numbers.length; i++){
            Long originNumber = numbers[i];
            String result = Long.toBinaryString(originNumber);
            if(originNumber%2==0){
                answer[i] = originNumber+1;
            }
            else{
                int zeroPos = result.lastIndexOf('0');
                StringBuilder sb = new StringBuilder();

                if(zeroPos == -1){  // lastIndexOf 해당 문자가 없으면 -1 리턴
                    // 0이 없을때
                    sb.append("10");
                    sb.append(result.substring(1,result.length()));
                }
                else{
                    // 0이 있을때, 0의 위치 전 까진 다 더해줌
                    sb.append(result.substring(0,zeroPos));
                    sb.append("10");
                    sb.append(result.substring(zeroPos+2,result.length()));
                }
                answer[i] = Long.parseLong(sb.toString(),2);
            }
        }


        return answer;
    }
}
