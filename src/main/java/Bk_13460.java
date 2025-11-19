import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bk_13460 {
    private static int result, hole_row, hole_col;
    private static char[][] map;
    private static boolean[][][][] visited;
    private static int[] dx = {0,-1,0,1};
    private static int[] dy = {-1,0,1,0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int red_row = 0;
        int red_col = 0;
        int blue_row = 0;
        int blue_col = 0;
        result = 11;
        map = new char[n][m];
        visited = new boolean[n][m][n][m];
        for(int i=0; i<n; i++){
            String input1 = br.readLine();
            for (int j=0; j<m; j++){
                map[i][j] = input1.charAt(j);
                if (map[i][j] == 'R'){
                    red_row = i;
                    red_col = j;
                }
                if (map[i][j] == 'B'){
                    blue_row = i;
                    blue_col = j;
                }
            }
        }
        dfs(0,red_row,red_col,blue_row,blue_col);
        if (result > 10){
            result = -1;
        }
        System.out.println(result);
    }
    private static void dfs(int cnt, int red_row, int red_col, int blue_row, int blue_col){
        // 10 번 초과 시 실패
        if (cnt > 10){
            return;
        }
        // 파란 공이 구멍에 빠질 경우 실패
        if (map[blue_row][blue_col] == 'O'){
            return;
        }
        // 빨간 공이 구멍에 빠질 경우 최소 횟수 저장
        if (map[red_row][red_col] == 'O'){
            result = Math.min(cnt,result);
            return;
        }
        visited[red_row][red_col][blue_row][blue_col] = true;
        for(int i =0;i<4;i++){
            int red_x = red_row;
            int red_y = red_col;
            int blue_x = blue_row;
            int blue_y = blue_col;
            // 빨간 공 움직이기
            while(true){
                red_x += dx[i];
                red_y += dy[i];
                if (map[red_x][red_y] == '#'){
                    red_x -= dx[i];
                    red_y -= dy[i];
                    break;
                }
                if (map[red_x][red_y] == 'O'){
                    break;
                }
            }
            // 파란 공 움직이기
            while(true){
                blue_x += dx[i];
                blue_y += dy[i];
                if (map[blue_x][blue_y] == '#'){
                    blue_x -= dx[i];
                    blue_y -= dy[i];
                    break;
                }
                if (map[blue_x][blue_y] == 'O'){
                    break;
                }
            }
            // 두 공의 위치가 같을 때
            // 두 공이 모두 구멍에 빠질 경우 실패이므로 자리 조정하지 않아야 함
            if (red_x == blue_x && red_y == blue_y && map[red_x][red_y] != 'O'){
                if (Math.abs(red_x - red_row) + Math.abs(red_y - red_col) > Math.abs(blue_x - blue_row) + Math.abs(blue_y - blue_col)){
                    red_x -= dx[i];
                    red_y -= dy[i];
                }
                else {
                    blue_x -= dx[i];
                    blue_y -= dy[i];
                }
            }
            if (!visited[red_x][red_y][blue_x][blue_y]){
                dfs(cnt+1, red_x,red_y,blue_x,blue_y);
            }
        }
        visited[red_row][red_col][blue_row][blue_col] = false;
    }
}
