import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bk_6603 {
    private static int k;
    private static int[] arr,answer;
    private static boolean[] visited;
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = -1;
        answer = new int[6];
        // k 값이 0이 될 경우 반복문 종료
        while(k != 0){
            String[] input1 = br.readLine().split(" ");
            k = Integer.parseInt(input1[0]);
            arr = new int[k];
            visited = new boolean[k];
            for(int i=1;i<=k;i++){
                arr[i-1] = Integer.parseInt(input1[i]);
            }
            dfs(0);
            sb.append("\n");
        }
        System.out.println(sb.toString().trim());
    }
    private static void dfs(int cnt){
        // 재귀 종료 조건
        if(cnt == 6){
            for(int num: answer){
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i =0;i<k;i++){
            // 비내림차순이 아닐 경우 방지
            if (cnt > 0 && answer[cnt-1] > arr[i]){
                continue;
            }
            // visited 배열을 사용해 중복 방지
            if(!visited[i]){
                answer[cnt] = arr[i];
                cnt++;
                visited[i] = true;
                dfs(cnt);
                cnt--;
                visited[i] = false;
            }
        }
    }
}
