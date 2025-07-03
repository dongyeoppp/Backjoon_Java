import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Bk_1941 {
    private static int[][] arr;
    private static int[] dx = {1,-1,0,0};
    private static int[] dy = {0,0,1,-1};
    private static int result;
    private static boolean[] bfs_visited;
    private static Deque<Integer> que;
    private static int[] answer;
    private static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[5][5];
        que = new ArrayDeque<>();
        answer = new int[7];
        visited = new boolean[25];
        for(int i =0; i<5; i++){
            String input = br.readLine();
            for(int j = 0; j<5; j++){
                // 임도연파는 0으로, 이다솜파는 1로 표시
                if(input.charAt(j) == 'Y'){
                    arr[i][j] = 0;
                    continue;
                }
                arr[i][j] = 1;
            }
        }
        backtracking(0,0);
        System.out.println(result);
    }
    // 7명의 여학생을 구하는 경우의 수
    private static void backtracking(int member, int y_member){
        // 임도연파 학생이 3명 이상일 경우 재귀 종료
        if (y_member > 3){
            return;
        }
        // 재귀 종료 조건 만족
        if (member == 7){
            // 7명의 학생이 모두 인접할 경우 체크
            if(bfs()){
                result++;
            }
            return;
        }
        for(int i =0;i<25;i++){
            // 중복 방지
            if(!visited[i]){
                // 비내침 차순 체크 (중복되는 수열이 나오지 않도록 함)
                if (member> 0 && answer[member-1] > i){
                    continue;
                }
                answer[member] = i;
                member++;
                visited[i] = true;
                // 해당 여학생의 행 값을 x, 열 값을 y
                int x = i / 5;
                int y = i % 5;
                if (arr[x][y] == 0){
                    y_member++;
                }
                backtracking(member,y_member);
                member--;
                visited[i] = false;
                if (arr[x][y] == 0){
                    y_member--;
                }
            }
        }
    }
    // 7명의 여학생이 인접한지 bfs를 통해 체크
    private static boolean bfs(){
        int cnt = 0;
        bfs_visited = new boolean[25];
        // 큐 에는 처음 값만 넣고 bfs 시작
        for(int num: answer){
            que.add(num);
            bfs_visited[num] = true;
            cnt++;
            break;
        }
        while(!que.isEmpty()){
            int removed = que.poll();
            for(int i =0; i<4; i++){
                int x = dx[i] + removed / 5;
                int y = dy[i] + removed % 5;
                // x는 행, y는 열 값, check는 여학생 번호
                int check = x * 5 + y;
                // visited를 통해 해당 수열이 맞는지 체크, bfs_visited를 통해 큐에 넣었던 수가 아닌지 체크
                if (0<=x && x<5 && 0<= y && y <5 && visited[check] && !bfs_visited[check]){
                    que.add(check);
                    bfs_visited[check] = true;
                    cnt++;
                }
            }
        }
        // 7명 모두가 인접해 있다면 true 반환
        if (cnt == 7){
            return true;
        }
        return false;
    }
}
