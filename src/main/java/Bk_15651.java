import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bk_15651 {
    static int n;
    static int m;
    static int[] arr1;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] arr = input.split(" ");
        n = Integer.parseInt(arr[0]);
        m = Integer.parseInt(arr[1]);
        arr1 = new int[n];
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
        // visited가 필요없어졌으므로 visited를 삭제하고 중복을 허용했습니다.
        for(int i =1 ; i <=n ; i++){
                arr1[count] = i;
                dfs(count+1);
        }

    }
}
