import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Bk_5430 {
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i =0; i < t ; i++){
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String str = br.readLine();
            String new_str = str.substring(1,str.length()-1);
            String[] arr = new_str.split(",");
            Deque<Integer> deque = new ArrayDeque<>();
            // 오류 발생 여부 체크
            boolean check = true;
            // n이 0일 경우 deque에 이상한 값이 들어갈 수 있으므로 경우를 나눠 처리
            if (n != 0){
                for (String str1: arr){
                    int ans = Integer.parseInt(str1);
                    deque.add(ans);
                }
            }
            // R을 확인하여 방향 체크
            boolean state = true;
            for(char chr: p.toCharArray()){
                if (chr== 'R'){
                   state = !state;
                }
                else if(chr == 'D'){
                    // 에러가 발생할 경우
                    if (deque.isEmpty()){
                        sb.append("error").append("\n");
                        check = false;
                        break;
                    }
                    // 정방향일 경우 앞에서 pop
                    else if (state){
                        deque.poll();
                    }
                    // 반대 방향일 경우 뒤에서 pop
                    else {
                        deque.pollLast();
                    }
                }
            }
            // 에러가 아닐 경우
            if (check){
                sb.append("[");
                // deque가 비어있을 경우
                if (deque.isEmpty()){
                    sb.append("]").append("\n");
                    continue;
                }
                // 정방향일 경우 앞에서부터 pop
                if (state){
                    sb.append(deque.poll());
                    while(!deque.isEmpty()){
                        sb.append(",").append(deque.poll());
                    }
                }
                // 반대 방향일 경우 뒤에서부터 pop
                else{
                    sb.append(deque.pollLast());
                    while(!deque.isEmpty()){
                        sb.append(",").append(deque.pollLast());
                    }
                }

                sb.append("]").append("\n");
            }

        }
        System.out.println(sb.toString().trim());
    }
}
