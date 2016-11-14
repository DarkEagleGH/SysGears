import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long value;
        System.out.print("Input integer value > 0: ");
        while (sc.hasNext()) {
            if (sc.hasNextLong()) {
                value = sc.nextLong();
                if (value > 0) {
                    System.out.println("sqrt_b(" + value + "): " + sqrt_b(value));
                } else {
                    System.out.println("Wrong input");
                }
                sc.nextLine();
            } else {
                System.out.println("Wrong input");
                sc.nextLine();
            }
            System.out.print("Input integer value > 0: ");
        }
    }

//   Babylonian (Hero) method
    public static long sqrt_b(long value) {
        if (value <= 0) return 0;
        long res = value;
        long div = value / 2;

        while (true) {
            if (res > div) {
                res = div;
            } else {
                return res;
            }
            div = (value / div + div) / 2;
        }
    }
}
