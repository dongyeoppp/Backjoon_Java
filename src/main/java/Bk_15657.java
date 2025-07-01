import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Bk_15657 {
    private static int n,m;
    private static int[] arr,answer;
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = br.readLine().split(" ");
        n = Integer.parseInt(input1[0]);
        m = Integer.parseInt(input1[1]);
        arr = new int[n];
        String[] input2 = br.readLine().split(" ");
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(input2[i]);
        }
        Arrays.sort(arr);
        answer = new int[m];
        dfs(0,0);
        System.out.println(sb.toString().trim());
    }
    private static void dfs(int save, int cnt){
        if (cnt == m){
            for(int num: answer){
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i =0;i<n;i++){
            // 중복되는 수열을 체크
            if (save <= arr[i]){
                answer[cnt] = arr[i];
                cnt++;
                dfs(answer[cnt-1],cnt);
                cnt--;
            }
        }
    }
}















