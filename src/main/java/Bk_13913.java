import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bk_13913 {
    static int[] dp = new int[100001];
    static int[] prev = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] arr = input.split(" ");
        int n = Integer.parseInt(arr[0]);
        int k = Integer.parseInt(arr[1]);
        for (int i = 0; i < 100001; i++) {
            dp[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }
        bfs(n,k);
        System.out.println(dp[k]);
        List<Integer> list = new ArrayList<>();
        int answer = k;
        while(prev[answer] != -2){
            list.add(answer);
            answer = prev[answer];
        }
        list.add(n);
        Collections.reverse(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i != list.size() - 1) {
                System.out.print(" ");
            }
        }
    }

    static void bfs(int n, int k){
        Queue<Integer> que = new LinkedList<>();
        que.add(n);
        dp[n] = 0;
        prev[n] = -2;
        while (!que.isEmpty()){
            int cur = que.poll();
            if (cur == k){
                return;
            }

            // (cur*2)로 가는 경우
            if (cur*2 >= 0 && cur*2 < 100001){
                if (dp[cur*2] > dp[cur] + 1) {
                    dp[cur*2] = dp[cur] + 1;
                    que.add(cur*2);
                    prev[cur*2] = cur;
                }
            }
            // (cur+1)로 가는 경우
            if (cur+1 >= 0 && cur+1 < 100001){
                if (dp[cur+1] > dp[cur] + 1) {
                    dp[cur+1] = dp[cur] + 1;
                    que.add(cur+1);
                    prev[cur+1] = cur;
                }
            }
            // (cur-1)로 가는 경우
            if (cur-1 >= 0 && cur-1 < 100001){
                if (dp[cur-1] > dp[cur] + 1) {
                    dp[cur-1] = dp[cur] + 1;
                    que.add(cur-1);
                    prev[cur-1] = cur;
                }
            }
        }
    }
}

