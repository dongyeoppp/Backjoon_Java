import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Bk_1475 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        // arr 배열에 각각의 숫자를 몇 번 사용해야 하는지 체크 / 6,9는 따로 처리함
        int[] arr = new int[10];
        for(int i= 0; i<input.length();i++){
            char str1 = input.charAt(i);
            // char을 int로 바꾸기
            int num = str1 - '0';
            arr[num] += 1;
        }
        int sum1 = 0;
        int result = 0;
        for (int i=0; i<10;i++){
            // 6,9의 경우 따로 처리
            if (i == 6 || i == 9){
                sum1 += arr[i];
            }
            else{
                result = Math.max(result,arr[i]);
            }
        }

        if (sum1 % 2 ==0){
            sum1 = sum1 / 2;
        }
        else{
            sum1 = sum1 / 2 + 1;
        }
        System.out.println(Math.max(result,sum1));
    }
}
