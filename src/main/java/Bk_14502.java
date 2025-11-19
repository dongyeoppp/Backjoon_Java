import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Bk_14502 {
    private static int[][] map;
    private static int[] new_wall, zero_arr, virus_arr;
    private static int[] dx = {1,-1,0,0};
    private static int[] dy = {0,0,1,-1};
    private static int zero_count, virus_count,n, m, result;
    private static boolean[][] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = br.readLine().split(" ");
        n = Integer.parseInt(input1[0]);
        m = Integer.parseInt(input1[1]);
        map = new int[n][m];
        zero_count = 0;
        virus_count = 0;
        for(int i =0; i<n; i++){
            String[] input2 = br.readLine().split(" ");
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(input2[j]);
                if (map[i][j] == 0){
                    zero_count++;
                }
                if (map[i][j] == 2){
                    virus_count++;
                }
            }
        }
        // 빈칸에 3개의 벽을 세우는 경우의 수를 모두 구하기 위해 0이 쓰여진 곳의 위치 확인
        // 바이러스 퍼트리기 위한 2의 위치 확인
        // ex)
        // 0 1 2 3
        // 4 5 6 7
        // 8 9 10 11 -> 각 자리의 값을 저장
        zero_arr = new int[zero_count];
        virus_arr = new int[virus_count];
        int zero_cnt = 0;
        int virus_cnt = 0;
        for(int i=0;i <n;i++){
            for(int j =0; j<m;j++){
                if (map[i][j] == 0){
                    zero_arr[zero_cnt] = i * m + j;
                    zero_cnt++;
                }
                if (map[i][j] == 2){
                    virus_arr[virus_cnt] = i*m + j;
                    virus_cnt++;
                }
            }
        }
        new_wall = new int[3];
        result = 0;
        combination(0,0);
        System.out.println(result);
    }
    // 3개의 벽을 세울 위치 모든 조합 확인
    // 각 조합의 안전 구역 크기 확인
    // 안전 구역 크기 최대값 갱신
    private static void combination(int start, int depth){
        if (depth == 3){
            bfs();
            result = Math.max(safe(0),result);
            return;
        }
        for(int i= start; i<zero_count; i++){
            new_wall[depth] = zero_arr[i];
            map[new_wall[depth] /m][new_wall[depth] %m] = 1;
            combination(i+1,depth+1);
            map[new_wall[depth] /m][new_wall[depth] %m] = 0;
        }
    }
    // 바이러스가 퍼진 위치 visited 배열에 저장
    private static void bfs(){
        Deque<int[]> que = new ArrayDeque<>();
        visited = new boolean[n][m];
        for(int virus: virus_arr){
            visited[virus/m][virus%m]= true;
            que.add(new int[] {virus/m,virus%m});
        }
        while(!que.isEmpty()){
            int[] removed = que.poll();
            for(int i =0; i<4;i++){
                int x = removed[0] + dx[i];
                int y = removed[1] + dy[i];
                if(0<=x && x<n && 0<=y && y<m && !visited[x][y] && map[x][y] != 1){
                    visited[x][y] = true;
                    que.add(new int[] {x,y});
                }
            }
        }
    }
    // 안전 영역의 크기 구하기
    private static int safe(int answer){
        for(int i=0;i<n;i++){
            for (int j=0; j<m;j++){
                if (!visited[i][j] && map[i][j] == 0){
                    answer++;
                }
            }
        }
        return answer;
    }
}
