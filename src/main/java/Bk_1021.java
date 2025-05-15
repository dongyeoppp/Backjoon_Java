import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Bk_1021 {
    // pollLast와 addFirst 사용하기 위해 ArrayDeque 선언
    private static Deque<Integer> deque = new ArrayDeque<>();
    // count에 연산의 최솟값 저장
    private static int count = 0;
    // 2번 연산 수행 메서드
    private static void left_move(int ans){
        while(deque.peek() != ans){
            deque.add(deque.poll());
            count++;
        }
    }
    // 3번 연산 수행 메서드
    private static void right_move(int ans){
        while(deque.peek() != ans){
            deque.addFirst(deque.pollLast());
            count++;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr =  br.readLine().split(" ");
        int n = Integer.parseInt(arr[0]);
        String[] arr1 = br.readLine().split(" ");
        for(int i = 1; i<=n; i++){
            deque.add(i);
        }
        for (String str : arr1){
            int ans = Integer.parseInt(str);
            int cnt = 1;
            // 현재 뽑아야하는 원소의 위치 찾기
            for(int num : deque){
                if (num == ans){
                    break;
                }
                cnt++;
            }
            // 2,3 번 중 어떤 연산이 더 효율적인지 체크
            if (cnt <= deque.size()/2+1){
                left_move(ans);
            }
            else{
                right_move(ans);
            }
            // 1번 연산 수행
            deque.poll();
        }
        System.out.println(count);
    }
}
