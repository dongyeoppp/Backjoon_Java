import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Bk_13335 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = br.readLine().split(" ");
        int n = Integer.parseInt(input1[0]);
        int w = Integer.parseInt(input1[1]);
        int l = Integer.parseInt(input1[2]);
        String[] input2 = br.readLine().split(" ");
        // 트럭 리스트의 인덱스
        int check = 0;
        // 다리 위의 트럭 무게 합
        int weight = 0;
        // 시간
        int count = 0;
        int answer = 0;
        // deque에 트럭 무게를 기록
        Deque<Integer> deque = new ArrayDeque<>();
        while(true){
            // 다리에 올라갈 트럭이 남았을 경우
            if (check<n){
                answer = Integer.parseInt(input2[check]);
            }
            // 다리 위로 올라갈 트럭이 없을 경우
            else{
                answer = 0;
            }
            // 다리의 길이 만큼 트럭이 올라갔을 경우
            if (deque.size() == w){
                int removed = deque.poll();
                weight -= removed;
            }
            // 최대하중을 고려하여 트럭의 무게 대신 0을 넣으며 시간을 체크
            if (weight+answer > l){
                deque.add(0);
                count++;
                continue;
            }
            deque.add(answer);
            weight+= answer;
            check++;
            count++;
            // 종료 조건
            if(weight == 0){
                break;
            }
        }
        System.out.println(count);
    }
}
