import java.util.Arrays;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        final int ARRSIZE = 20;
        Scanner sc = new Scanner(System.in);
        int value;
        int[] arr = fillArray(ARRSIZE);
        System.out.println("Random array: " + (Arrays.toString(arr)));
        Arrays.sort(arr);
        System.out.println("Sorted array: " + (Arrays.toString(arr)));
        System.out.print("Input integer k (1 <= k <= 20): ");
        while (sc.hasNext()) {
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value > 0 && value < ARRSIZE+1) {
                    System.out.println(value+"-element: : " + arr[value-1]);
                } else {
                    System.out.println("Wrong input");
                }
                sc.nextLine();
            } else {
                System.out.println("Wrong input");
                sc.nextLine();
            }
            System.out.print("Input integer k (1 <= k <= 20): ");
        }
    }

    //   Fill an array with random numbers
    public static int[] fillArray(int size) {
        int[] arr = new int[size];
        for (int i=0; i<size; i++){
            arr[i]  = (int)(Math.random()*21);
        }
        return arr;
    }
}
