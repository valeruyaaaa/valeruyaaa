import java.util.ArrayList;
import java.util.List;

public class NumberRange  {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 5, 7, 8, 10};

        List<String> ranges = getNumberRanges(numbers);

        System.out.println("Диапазоны чисел: ");
        for (String range : ranges) {
            System.out.println(range);
        }
    }

    public static List<String> getNumberRanges(int[] numbers) {
        List<String> ranges = new ArrayList<>();
        if (numbers.length == 0) {
            return ranges;
        }

        int start = numbers[0];
        int end = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] == end + 1) {
                end = numbers[i];
            } else {
                if (start == end) {
                    ranges.add(String.valueOf(start));
                } else {
                    ranges.add(start + "->" + end);
                }

                start = numbers[i];
                end = numbers[i];
            }
        }

        if (start == end) {
            ranges.add(String.valueOf(start));
        } else {
            ranges.add(start + "->" + end);
        }

        return ranges;
    }
}


