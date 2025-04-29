import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Bk_3273 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String input1 = br.readLine();
        String input2 = br.readLine();
        int n = Integer.parseInt(input);
        int x = Integer.parseInt(input2);
        String[] arr = input1.split(" ");
        int[] nums = new int[n];
        for(int i=0;i<n;i++){
            nums[i] = Integer.parseInt(arr[i]);
        }
        // 배열 정렬
        Arrays.sort(nums);
        // 투 포인터 사용
        int start = 0;
        int end = n-1;
        int result = 0;
        while (start<end){
            int num1 = nums[start];
            int num2 = nums[end];
            // 조건을 만족하는 경우
            if (num1+num2 == x){
                result+=1;
                start++;
                end--;
            }
            // x보다 두 개의 합이 클 경우
            else if(num1+num2 > x){
                end--;
            }
            // x보다 두 개의 합이 작을 경우
            else{
                start++;
            }
        }
        System.out.println(result);
    }
}
