import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bk_15649 {
    static int n ;
    static int m;
    static int[] arr1;
    static boolean[] visited;
    // StringBuilder를 사용하여 출려문을 한번에 출력
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] arr = input.split(" ");

        n = Integer.parseInt(arr[0]);
        m = Integer.parseInt(arr[1]);

        // 기본적으로 int 배열에는 각 인덱스가 0으로 채원진다.
        // boolean 배열에는 각 인덱스가 false로 채우진다.
        arr1 = new int[n+1];
        visited = new boolean[n+1];

        dfs(0);
        System.out.print(sb);
    }
    // dfs를 사용한 백트래킹 활용
    // count는 visited에서 true의 개수
    private static void dfs(int count){
        // 종료 조건
        if (count == m){
            for(int num: arr1){
                if (num == 0) {
                    break;
                }
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i =1; i<=n; i++){
            if (!visited[i]){
                visited[i] = true;
                // arr1의 인덱스 값을 계속 수정해 주며 백트래킹 실행
                arr1[count] = i;
                dfs(count+1);
                visited[i] = false;
            }
        }
        return;
    }
}
