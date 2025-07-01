import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Bk_15663 {
    private static int n,m,before;
    private static int[] arr,answer;
    private static boolean[] visited;
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = br.readLine().split(" ");
        n = Integer.parseInt(input1[0]);
        m = Integer.parseInt(input1[1]);
        String[] input2 = br.readLine().split(" ");
        arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(input2[i]);
        }
        Arrays.sort(arr);
        answer = new int[m];
        // visited 배열로 중복 체크
        visited = new boolean[n];
        dfs(0);
        System.out.println(sb.toString().trim());
    }
    private static void dfs(int cnt){
        if (cnt == m){
            for(int num: answer){
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }
        // before 변수를 사용해 수열의 중복을 체크
        before = 0;
        for(int i=0;i<n;i++){
            if (!visited[i] && arr[i] != before){
                answer[cnt] = arr[i];
                cnt++;
                visited[i] = true;
                dfs(cnt);
                // 재귀가 끝났을 때 before 갱신하여 answer의 마지막값을 체크한다.
                before = arr[i];
                cnt--;
                visited[i] = false;
            }
        }
    }
}
