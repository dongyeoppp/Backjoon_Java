import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Stack;

public class Bk_9446 {
    public static int[] arr;
    public static int n;
    public static boolean[] visited;
    public static StringBuilder sb = new StringBuilder();
    public static int result;
    public static void bfs(int num){
        Deque<Integer> deque = new ArrayDeque<>();
        // 1->2->3->2 이럴 경우 2부터 3까지의 학생은 팀을 결성할 수 있다.
        // 그러므로 큐에서 나온 값들을 스택에 저장하며 bfs를 수행한다.
        Stack<Integer> stk = new Stack<>();
        deque.add(num);
        stk.add(num);
        visited[num] = true;
        int cnt = 0;
        while(!deque.isEmpty()){
            int removed = deque.poll();
            // 방문한적 없는 경우
            if (!visited[arr[removed]]){
                deque.add(arr[removed]);
                stk.add(arr[removed]);
                visited[arr[removed]] = true;
            }
            // 사이클이 끝났을 경우 사이클을 이루는 학생들의 수는 cnt
            // cnt 수 만큼의 학생 수를 result에서 빼준다.
            else{
                while(!stk.isEmpty()){
                    cnt++;
                    if (stk.pop() == arr[removed]) {
                        result -= cnt;
                        return;
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int i = 0;i<t;i++){
            n = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split(" ");
            arr = new int[n];
            visited = new boolean[n];
            // result에서 팀을 결성할 때마다 팀을 결성한 학생 수를 뺀다.
            result = n;
            for(int j = 0; j<n; j++){
                arr[j] = Integer.parseInt(input[j])-1;
                // 자기 자신을 선택한 경우를 먼저 체크
                if (j == arr[j]){
                    visited[j] = true;
                    result --;
                }
            }
            for(int j = 0; j<n; j++){
                if (!visited[j]){
                    bfs(j);
                }
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb.toString().trim());
    }
}
