import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Bk_13549 {
    public static int result;
    public static void bfs(int n, int k, int cnt){
        Deque<int[]> deque = new ArrayDeque<>();
        boolean[] visited = new boolean[100001];
        deque.add(new int[] {n,cnt});
        visited[n] = true;
        while(!deque.isEmpty()){
            int[] removed =deque.poll();
            int here = removed[0];
            int count = removed[1];
            // 동생 위치에 도착했을 경우
            if (here == k){
                result = count;
                return;
            }
            // 순간이동으로 이동 했을 경우
            if (here*2 <=100000 && !visited[here*2]){
                deque.add(new int[] {here*2,count});
                visited[here*2] = true;
            }
            // 걸어서 이동했을 경우 -1
            if (0<= here-1 && !visited[here-1]){
                deque.add(new int[] {here-1,count+1});
                visited[here-1] = true;
            }
            // 걸어서 이동했을 경우 +1
            if (here+1 <= 100000 && !visited[here+1]){
                deque.add(new int[] {here+1,count+1});
                visited[here+1] = true;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);
        bfs(n,k,0);
        System.out.println(result);
    }
}
