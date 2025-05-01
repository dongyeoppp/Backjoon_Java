import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Bk_1158 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] arr = input.split(" ");
        int n = Integer.parseInt(arr[0]);
        int k = Integer.parseInt(arr[1]);

        // queue를 사용해서 풀이
        Queue<Integer> que = new LinkedList<>();
        for(int i =1; i<=n; i++){
            que.add(i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        while (!que.isEmpty()){
            for (int i = 0; i<k-1; i++){
                que.add(que.poll());
            }
            sb.append(que.poll());
            if (!que.isEmpty()){
                sb.append(", ");
            }
        }
        sb.append(">");
        System.out.println(sb);
    }
}
