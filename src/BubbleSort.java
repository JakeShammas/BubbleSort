import java.io.*;
import java.util.*;

public class BubbleSort {

    // Generate an array of random integers between 0 and 100
    public static int[] createRandomArray(int arrayLength) {
        int[] array = new int[arrayLength];
        Random rand = new Random();
        for (int i = 0; i < arrayLength; i++) {
            array[i] = rand.nextInt(101); // Random number between 0-100
        }
        return array;
    }

    // Write an array to a file
    public static void writeArrayToFile(int[] array, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int num : array) {
                writer.write(num + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Read integers from a file into an array
    public static int[] readFileToArray(String filename) {
        List<Integer> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return new int[0];
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    // Implement Bubble Sort
    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    // Main method to handle user input
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1 - Generate random array and store in file");
            System.out.println("2 - Read array from file, sort, and save");
            System.out.println("3 - Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter array length: ");
                    int length = scanner.nextInt();
                    int[] randomArray = createRandomArray(length);
                    writeArrayToFile(randomArray, "random_numbers.txt");
                    System.out.println("Array saved to random_numbers.txt");
                    break;
                case 2:
                    int[] array = readFileToArray("random_numbers.txt");
                    if (array.length > 0) {
                        bubbleSort(array);
                        writeArrayToFile(array, "sorted_numbers.txt");
                        System.out.println("Sorted array saved to sorted_numbers.txt");
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
