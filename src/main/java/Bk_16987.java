import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백트래킹을 사용하여 풀이
public class Bk_16987 {
    static int[][] egg;
    static int n;
    static boolean[] visited;
    static int result;
    static boolean check;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input1 = br.readLine();
        n = Integer.parseInt(input1);
        egg = new int[n][2];
        visited = new boolean[n];
        result = 0;
        check = false;
        // egg 배열에 계란의 [내구도, 무게]를 순서대로 넣음
        for (int i =0; i < n ; i++){
            String input2 = br.readLine();
            String[] arr1 =  input2.split(" ");
            egg[i][0] = Integer.parseInt(arr1[0]);
            egg[i][1] = Integer.parseInt(arr1[1]);
        }
        dfs(0,0);
        System.out.println(result);
    }
    static void dfs(int start, int cnt){
        // 계란 치는 과정 종료, 가장 많이 깬 계란의 개수 result에 저장
        if (start == n){
            if (result < cnt){
                result = cnt;
            }
            return;
        }
        // 손에 쥔 계란이 깨지지 않았을 때
        if (!visited[start]){
            check = false;
            for (int j = 0 ; j <n; j++) {
                // 손에 쥔 계란을 제외한 나머지 계란 중 안깨진 계란을 내려침
                if (start != j && !visited[j]) {
                    egg[start][0] -= egg[j][1];
                    egg[j][0] -= egg[start][1];
                    // 손에 쥔 계란, 내려친 계란 모두 깨진 경우
                    if (egg[start][0] <= 0 && egg[j][0] <= 0) {
                        visited[start] = true;
                        visited[j] = true;
                        dfs(start + 1, cnt + 2);
                        visited[start] = false;
                        visited[j] = false;
                    // 손에 쥔 계란만 깨진 경우
                    } else if (egg[start][0] <= 0) {
                        visited[start] = true;
                        dfs(start + 1, cnt + 1);
                        visited[start] = false;
                    // 내려 친 계란만 깨진 경우
                    } else if (egg[j][0] <= 0) {
                        visited[j] = true;
                        dfs(start + 1, cnt + 1);
                        visited[j] = false;
                    // 아무것도 깨지지 않은 경우
                    } else {
                        dfs(start + 1, cnt);
                    }
                    egg[start][0] += egg[j][1];
                    egg[j][0] += egg[start][1];
                    check = true;
                }
            }
            // 손에든 계란을 제외하고 다 깨진 경우, 다음 계란 선택
            if (!check){
                dfs(start+1,cnt);
            }
        // 손에 쥐려고 한 계란이 깨진 경우 다음 계란 선택
        }else{
            dfs(start+1,cnt);
        }
    }
}
