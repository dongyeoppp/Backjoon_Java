import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Bk_2573 {
    public static int n,m;
    public static int[][] map;
    public static int[] dx = {1,-1,0,0};
    public static int[] dy = {0,0,1,-1};
    public static boolean[][] visited;
    public static void bfs(int row, int col){
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[] {row,col});
        visited[row][col] = true;
        while(!deque.isEmpty()){
            int[] removed = deque.poll();
            int re_row = removed[0];
            int re_col = removed[1];
            // 인접한 바닷물 칸 체크
            int count = 0;
            for(int i = 0; i<4; i++){
                int x = dx[i] + re_row;
                int y = dy[i] + re_col;
                if (0<= x && x < n && 0<= y && y < m && !visited[x][y]){
                    // 인접하 곳의 바닷물 칸 체크
                    if (map[x][y] == 0){
                        count++;
                    }
                    // 바닷물이 아닐 경우 큐에 넣기
                    if (map[x][y] != 0){
                        deque.add(new int[] {x,y});
                        visited[x][y] = true;
                    }
                }
            }
            // 빙산의 높이가 0 미만이 될 경우 방지
            if (map[re_row][re_col] <= count){
                map[re_row][re_col] = 0;
            }
            else{
                map[re_row][re_col] -= count;
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        map = new int[n][m];
        for(int i =0; i<n;i++){
            String[] input1 = br.readLine().split(" ");
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(input1[j]);
            }
        }
        // 빙산이 두 덩이로 나뉘어졌는지 체크
        boolean check = false;
        // 빙산이 두 덩이로 안 나뉘어지고 다 녹은 경우 체크
        boolean melt = false;
        // 몇년이 흘렀는지 체크
        int year = 0;
        while (!check){
            int cnt = 0;
            visited = new boolean[n][m];
            for(int i =0;i<n;i++){
                for(int j=0;j<m;j++){
                    if(map[i][j] != 0 && !visited[i][j]){
                        // cnt로 bfs를 한번만 돌렸는지 = 한 덩이만 존재하는지 체크
                        cnt++;
                        // 두덩이 이상일 경우 반복문 종료
                        if (cnt >= 2){
                            check = true;
                            break;
                        }
                        bfs(i,j);
                    }
                }
                if (check){
                    break;
                }
            }
            // 빙산이 다 녹은 경우
            if(cnt == 0){
                melt = true;
                break;
            }
            year++;
        }
        // 빙산이 두 덩이로 안 나뉘고 다 녹은 경우 0 출력
        if (melt){
            System.out.println(0);
        }
        else{
            System.out.println(year-1);
        }
    }
}
