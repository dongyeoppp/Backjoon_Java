import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Bk_15664 {
    private static int n, m;
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
        answer = new int[m];
        visited = new boolean[n];
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(input2[i]);
        }

        Arrays.sort(arr);
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
        // before 변수를 사용해 중복을 방지한다.
        int before = 0;
        for(int i =0; i<n; i++){
            if (!visited[i] && before != arr[i]){
                // 비내림차순 조건 만족을 위해 값을 비교하여 체크
                if (cnt > 0 && answer[cnt-1] > arr[i]){
                    continue;
                }
                answer[cnt] = arr[i];
                cnt++;
                visited[i] = true;
                dfs(cnt);
                cnt--;
                // dfs 종료 후 before 값을 갱신 함으로써 중복을 방지한다.
                before = answer[cnt];
                visited[i] = false;
            }
        }
    }
}
