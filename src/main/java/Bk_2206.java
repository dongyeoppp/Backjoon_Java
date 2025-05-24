import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Bk_2206 {
    public static int[][] map;
    public static int n,m;
    public static int[][][] visited;
    public static int[] dx = {1,-1,0,0};
    public static int[] dy = {0,0,1,-1};
    public static int bfs(){
        Deque<int[]> deque = new ArrayDeque<>();
        visited[0][0][0] = 1;
        deque.add(new int[] {0,0,0});
        while(!deque.isEmpty()){
            int[] removed = deque.poll();
            int row = removed[0];
            int col = removed[1];
            // cnt를 통해 벽을 부수었는지, 안 부셨는지 체크
            int cnt = removed[2];
            // 도착지에 도착한 경우
            if (row == n-1 && col == m-1){
                return visited[row][col][cnt];
            }
            for(int i =0; i<4;i++){
                int x = row+dx[i];
                int y = col+dy[i];
                if (0<=x && x <n && 0<=y && y<m){
                    // 벽이 없는 곳 + 방문하지 않은 곳
                    if (map[x][y] == 0 && visited[x][y][cnt] == 0){
                        visited[x][y][cnt] = visited[row][col][cnt] + 1;
                        deque.add(new int[] {x,y,cnt});
                    }
                    // 벽이 있지만, 벽을 한번도 부순 적이 없는 경우
                    else if (map[x][y] == 1 && cnt == 0){
                        visited[x][y][1] = visited[row][col][cnt] + 1;
                        deque.add(new int[] {x,y,1});
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        map = new int[n][m];
        for(int i = 0; i < n; i++){
            String input1 = br.readLine();
            for (int j = 0;j<m;j++){
                if (input1.charAt(j) == '1'){
                    map[i][j] = 1;
                }
            }
        }
        visited = new int[n][m][2];
        System.out.println(bfs());
    }
}
