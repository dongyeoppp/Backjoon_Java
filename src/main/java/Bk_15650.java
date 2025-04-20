import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bk_15650 {
    static int n;
    static int m;
    static int[] arr1;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] arr = input.split(" ");
        n = Integer.parseInt(arr[0]);
        m = Integer.parseInt(arr[1]);
        arr1 = new int[n];
        visited = new boolean[n+1];
        dfs(0);
        System.out.print(sb);
    }

    private static void dfs(int count){
        if (count == m){
            for(int num: arr1){
                if (num == 0){
                    break;
                }
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i =1 ; i <=n ; i++){
            if (!visited[i]){
                visited[i] = true;
                arr1[count] = i;
                dfs(count+1);
                // 위 코드는 모두 N과 M (1) 과 동일하다.
                // 백트래킹 하는 과정에서 전에 i 값은 false로 초기화 하지 않으면서 중복을 제거했다.
                for (int j = i+1 ; j <=n;j++) {
                    visited[j] = false;
                }
            }
        }

    }
}
