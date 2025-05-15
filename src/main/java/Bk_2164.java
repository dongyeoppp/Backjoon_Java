import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Bk_2164 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue<Integer> que = new LinkedList<>();
        for (int i = 1 ; i <= n; i++){
            que.add(i);
        }
        // 한 장의 카드가 남을 때까지 반복문 수행
        while(que.size() > 1){
            que.poll();
            que.add(que.poll());
        }
        System.out.println(que.poll());
    }
}
