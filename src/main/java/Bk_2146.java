import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Bk_2146 {
    public static int[][] map;
    public static int n;
    public static boolean[][] visited;
    public static int[] dx = {1,-1,0,0};
    public static int[] dy = {0,0,1,-1};
    public static Deque<int[]> deque;
    public static int result;
    // 같은 섬을 체크 bfs
    public static void same_bfs(int row, int col, int cnt){
        deque = new ArrayDeque<>();
        deque.add(new int[] {row,col});
        map[row][col] = cnt;
        while(!deque.isEmpty()){
            int[] removed = deque.poll();
            int re_row = removed[0];
            int re_col = removed[1];
            for(int i = 0; i<4;i++){
                int x = re_row + dx[i];
                int y = re_col + dy[i];
                if (0<=x && x < n && 0<= y && y < n && map[x][y] == -1){
                    deque.add(new int[] {x,y});
                    map[x][y] = cnt;
                }
            }
        }
    }

    // 다른 섬까지의 최단거리 체크 bfs
    public static void lengthCheck_bfs(int row,int col, int count){
        deque = new ArrayDeque<>();
        visited = new boolean[n][n];
        deque.add(new int[] {row,col,count});
        visited[row][col] = true;
        int save = map[row][col];
        while(!deque.isEmpty()){
            int[] removed = deque.poll();
            int re_row = removed[0];
            int re_col = removed[1];
            int re_count = removed[2];
            for(int i=0;i<4;i++){
                int x = re_row+dx[i];
                int y = re_col+dy[i];
                if(0<=x && x < n && 0<=y && y < n && !visited[x][y]){
                    // 바다 일 경우
                    if(map[x][y] == 0){
                        deque.add(new int[] {x,y,re_count+1});
                        visited[x][y] = true;
                    }
                    // 다른 섬에 도착했을 경우 최단거리 갱신 이후 return하여 연산 최소화
                    else if (map[x][y] != save){
                        result = Math.min(re_count,result);
                        return;
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        // 바다인 곳은 0으로, 육지는 -1로 표시
        for(int i =0; i<n;i++){
            String[] input1 = br.readLine().split(" ");
            for (int j = 0;j<n;j++){
                if (input1[j].equals("1")){
                    map[i][j] = -1;
                }
                else{
                    map[i][j] = 0;
                }
            }
        }
        // 같은 육지는 같은 숫자를 가지도록 하여 구분함
        int cnt = 1;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(map[i][j] == -1){
                    same_bfs(i,j,cnt);
                    cnt++;
                }
            }
        }
        result = Integer.MAX_VALUE;
        // 바다와 인접한 육지마다의 최단거리를 구한다.
        for(int i=0; i<n; i++){
            for (int j=0;j<n;j++){
                if (map[i][j] != 0){
                    for(int k=0; k<4;k++){
                        int x = i+dx[k];
                        int y = j+dy[k];
                        // 육지이면서 바다와 인접한 경우 최단거리 구하는 bfs 실행
                        if (0<=x && x <n && 0<= y && y < n && map[x][y] ==0){
                            lengthCheck_bfs(i,j,0);
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(result);
    }
}
