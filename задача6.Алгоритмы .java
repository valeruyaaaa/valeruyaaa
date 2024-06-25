import java.util.Arrays;

public class NextGreaterNumber {
    public static void main(String[] args) {
        int number = 123;
        int result = nextGreaterNumber(number);
        System.out.println("Следующее большее число, которое можно составить: " + result);
    }

    public static int nextGreaterNumber(int number) {
        char[] digits = String.valueOf(number).toCharArray();

        int i = digits.length - 1;
        while (i > 0 && digits[i - 1] >= digits[i]) {
            i--;
        }

        if (i <= 0) {
            return -1;
        }

        int j = digits.length - 1;
        while (digits[j] <= digits[i - 1]) {
            j--;
        }

        char temp = digits[i - 1];
        digits[i - 1] = digits[j];
        digits[j] = temp;

        Arrays.sort(digits, i, digits.length);

        long result = Long.parseLong(new String(digits));
        if (result <= number) {
            return -1;
        }

        return (int)result;
    }
}
