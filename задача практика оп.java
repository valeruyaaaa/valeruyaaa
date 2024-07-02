import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Calculator {
    private static final String HISTORY_FILE = "history.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> history = loadHistory();

        System.out.println("Введите уравнение (или 'history' для просмотра истории):");
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("history")) {
                printHistory(history);
            } else {
                try {
                    double result = evaluate(input);
                    System.out.println("Результат: " + result);
                    saveToHistory(input + " = " + result, history);
                } catch (Exception e) {
                    System.out.println("Ошибка при вычислении: " + e.getMessage());
                }
            }
            System.out.println("Введите следующее уравнение (или 'history' для просмотра истории):");
        }
        scanner.close();
    }

    private static double evaluate(String expression) throws Exception {
     
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
        expression = expression.replaceAll("//", "/"); 
        expression = expression.replaceAll("\\|(.+?)\\|", "Math.abs($1)"); 
        return (double) engine.eval(expression);
    }

    private static void saveToHistory(String record, List<String> history) {
        history.add(record);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORY_FILE, true))) {
            writer.write(record);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении истории: " + e.getMessage());
        }
    }

    private static List<String> loadHistory() {
        List<String> history = new ArrayList<>();
        try {
            if (Files.exists(Paths.get(HISTORY_FILE))) {
                history = Files.readAllLines(Paths.get(HISTORY_FILE));
            }
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке истории: " + e.getMessage());
        }
        return history;
    }

    private static void printHistory(List<String> history) {
        if (history.isEmpty()) {
            System.out.println("История пуста.");
        } else {
            System.out.println("История вычислений:");
            for (String record : history) {
                System.out.println(record);
            }
        }
    }
}


