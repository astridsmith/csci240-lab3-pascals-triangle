import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("---Pascal's Triangle Solver---\nChoose an option: 1) Solve for the sum of the nth row 2) Benchmark performance of recursive vs iterative solving\n>");
        Scanner userInput = new Scanner(System.in);
        String progChoice = userInput.nextLine();

        switch (progChoice){
            case "1":
                System.out.println("[?] What row would you like to solve for?\n>");
                int row = Integer.parseInt(userInput.nextLine());
                int sum = Triangle.getRowSum(row);
                System.out.println(sum);
                break;
            case "2":
                System.out.println("[?] What method would you like to benchmark?\n (R)ecursive, (I)terative, or (B)oth\n>");
                String method = userInput.nextLine().trim().toUpperCase();
                System.out.println("[?] What row would you like to solve for?\n>");
                int benchmarkRow = Integer.parseInt(userInput.nextLine());
                System.out.println("[?] Enter the number of trials:\n>");
                int trials = Integer.parseInt(userInput.nextLine());
                if (method.equals("R")) {
                    long time = Triangle.benchmark("recursion", trials, benchmarkRow);
                    System.out.println(time);
                } else if (method.equals("I")) {
                    long time = Triangle.benchmark("iterative", trials, benchmarkRow);
                    System.out.println(time);
                } else if (method.equals("B")) {
                    long timeRecursive = Triangle.benchmark("recursion", trials, benchmarkRow);
                    long timeIterative = Triangle.benchmark("iterative", trials, benchmarkRow);
                    System.out.println("Recursive: " + timeRecursive + " nanoseconds");
                    System.out.println("Iterative: " + timeIterative + " nanoseconds");
                    System.out.println("Total time of both methods: " + timeRecursive + timeIterative);
                }
                break;
        }
    }
}