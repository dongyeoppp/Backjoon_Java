import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bk_14500 {
    private static int result,n,m;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = {1,-1,0,0};
    private static int[] dy = {0,0,1,-1};
    public static void main (String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        map = new int[n][m];
        for(int i = 0; i<n ; i++){
            String[] input1 = br.readLine().split(" ");
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(input1[j]);
            }
        }
        result = 0;
        // visited 배열을 for문 안에서 계속 갱신했을 때 시간 초과가 발생하여 밖으로 빼주었다.
        visited = new boolean[n][m];
        for(int i = 0; i<n;i++){
            for(int j =0; j<m; j++){
                visited[i][j] = true;
                // 백트래킹을 통해 ㅜ를 제외한 테트로미노를 확인했다.
                back(i,j,0,0);
                visited[i][j] = false;
                plus(i,j);
            }
        }
        System.out.println(result);
    }
    private static void back(int row, int col, int answer,int cnt){
        if (cnt == 4){
            if (answer > result){
                result = answer;
            }
            return;
        }
        answer += map[row][col];
        for(int i=0; i<4; i++){
            int x = row+dx[i];
            int y = col+dy[i];
            if(0<= x && x<n && 0<= y && y<m && !visited[x][y]){
                visited[x][y] = true;
                back(x,y,answer,cnt+1);
                visited[x][y] = false;
            }
        }
    }
    // "ㅜ"는 dfs로 확인할 수 없으므로 따로 로직을 추가
    private static void plus(int row, int col){
        int cnt = 0;
        int mini = 1001;
        int answer = map[row][col];
        for(int i =0; i<4; i++){
            int x = row+dx[i];
            int y = col+dy[i];
            if(0<= x && x<n && 0<= y && y<m){
                cnt++;
                answer += map[x][y];
                if(mini > map[x][y]){
                    mini = map[x][y];
                }
            }
        }
        if (cnt ==3){
            if(result < answer){
                result = answer;
            }
        }
        if (cnt == 4){
            answer -= mini;
            if (result < answer){
                result = answer;
            }
        }
    }
}
