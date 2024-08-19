import java.util.Optional;

import static java.util.Arrays.stream;

// O(1) mem, 0(N) time
public class YaCalculateString {
    public static Optional<String> calculate(String expression) {
        int result = 0;
        int currentBlockValue = 1;
        int currentValue = 0;

        for (char c : expression.toCharArray()) {
            if (c == '+' || c == '*') {
                if (c == '+') {
                    currentBlockValue *= currentValue;
                    result += currentBlockValue;
                    currentBlockValue = 1;
                } else {
                    currentBlockValue *= currentValue;
                }
                currentValue = 0;
            } else {
                int digit = c - '0';
                currentValue = currentValue * 10 + digit;
            }
        }
        currentBlockValue *= currentValue;
        result += currentBlockValue;

        return Optional.of(String.valueOf(result));
    }

    public int calculateWithMoreTime(String exp) {
        return stream(exp.split("\\+"))
                .map(it -> stream(it.split("\\*"))
                        .mapToInt(Integer::parseInt)
                        .reduce(1, (a, b) -> a * b))
                .mapToInt(it -> it)
                .sum();
    }

    public static void main(String[] args) {

        String[] testCases = {
                "",
                "23",
                "22+3",
                "1*3+6*2+2",
                "12+35"
        };

        for (String testCase : testCases) {
            System.out.println("Case: " + testCase + " ");

            if (calculate(testCase).isPresent()) {
                System.out.println("Answer: " + calculate(testCase).get());
            } else {
                System.out.println("An error occurred");
            }
        }
    }
}
