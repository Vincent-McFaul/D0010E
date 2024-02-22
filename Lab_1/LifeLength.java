import java.util.Scanner;

public class LifeLength {
    public static void main(String[] args) {
        //the funny program
        int n = 6;

        switch (n) {
            case 1:
                task1print();
                break;
            case 2:
                task2print();
                break;
            case 3:
                task3print();
                break;
            case 4:
                task4print();
                break;
            case 6:
                task6print();
                break;
            default:
                break;
        }
    }

    //task 1 -------------------------------------------------------
    public static int f1(int a0) {
        if (a0 == 1) {
            return 1;
        }
        if (a0 % 2 == 0) {
            return a0 / 2;   
        }
        else return ((3 * a0) + 1);
    }

    public static void task1print() {
        while (true) {   
            int a0 = 0;
            Scanner userInputScanner = new Scanner(System.in);

            try {
                System.out.println("Skriv in ett heltal större än noll för startvärde: ");
                a0 = userInputScanner.nextInt(); 

                //terminates program
                if (a0 == 0) {
                    userInputScanner.close();
                    break;
                }

                //checks for negative
                if (a0 < 0) {
                    System.out.println("Fel: Ej ett heltal > 0");
                }
                else {
                    System.out.println("Collatz conjecture med heltalet " + a0 + " ger :");
                    System.out.println(f1(a0));
                }
            }

            //triggers if input not int
            catch (Exception e) {
                System.out.println("Fel: Ej ett heltal > 0");
            }
        } 
    }

    //task 2 -------------------------------------------------------
    public static int f2(int a0) {
        int a2 = f1(f1(a0));
        return a2;
    }

    public static int f4(int a0) {
        int a4 = f2(f2(a0));
        return a4;
    }

    public static int f8(int a0) {
        int a8 = f4(f4(a0));
        return a8;
    }

    public static int f16(int a0) {
        int a16 = f8(f8(a0));
        return a16;
    }

    public static int f32(int a0) {
        int a32 = f16(f16(a0));
        return a32;
    }

    public static void task2print() {
        while (true) {   
            int a0 = 0;
            Scanner userInputScanner = new Scanner(System.in);

            try {
                System.out.println("Skriv in ett heltal större än noll för startvärde: ");
                a0 = userInputScanner.nextInt(); 

                //terminates program
                if (a0 == 0) {
                    userInputScanner.close();
                    break;
                } 

                //checks for negative
                if (a0 < 0) {
                    System.out.println("Fel: Ej ett heltal > 0");
                }
                else {
                    System.out.println("Collatz conjecture med heltalet " + a0 + " ger :");
                    System.out.print(
                    "f1: " + f1(a0)
                    + "    f2: " + f2(a0)
                    + "    f4: " + f4(a0)
                    + "    f8: " + f8(a0)
                    + "    f16: " + f16(a0)
                    + "    f32: " + f32(a0)
                    + "\n");
                }
            }

            //triggers if input not int
            catch (Exception e) {
                System.out.println("Fel: Ej ett heltal > 0");
            }
        }
    }

    //task 3 -------------------------------------------------------
    public static int iterateF(int a0, int n) {
        int nStepa0 = 0;
        int counter = 0;
        while (counter < n) {
            a0 = f1(a0);
            nStepa0 = a0;
            counter = counter + 1;
        }
        return nStepa0;
    }

    public static void task3print() {
        while (true) {   
            int a0 = 0;
            int n = 0;
            Scanner userInputScanner = new Scanner(System.in);

            try {
                System.out.println("Skriv in ett heltal större än noll för startvärde: ");
                a0 = userInputScanner.nextInt(); 

                //terminates program
                if (a0 == 0) {
                    userInputScanner.close();
                    break;
                }
                
                System.out.println("Skriv in ett heltal större än noll för antal steg: ");
                n = userInputScanner.nextInt(); 

                //checks for negative
                if ((a0 < 0) || (n < 0)) {
                    System.out.println("Fel: Ej ett heltal > 0");
                }
                else {
                    System.out.println("Collatz conjecture med heltalet " + a0 + " ger :");
                    System.out.print("F"+n+": " + iterateF(a0, n) + "\n");
                }
            }

            //triggers if input not int
            catch (Exception e) {
                System.out.println("Fel: Ej ett heltal > 0");
            }
        } 
    }

    //task 4 -------------------------------------------------------
    public static int iterLifeLength(int a0) {
        int stepsUntilOne = 0;
        while (a0 > 1) {
            a0 = f1(a0);
            stepsUntilOne = stepsUntilOne + 1;
        }
        return stepsUntilOne;
    }

    public static String intsToString(int x, int y) {
        return ("Livslängden av " + x + " är " + y + ".");
    }

    public static void task4print() {
        for (int i = 1; i <= 15; i++) {
            System.out.println(intsToString(i, iterLifeLength(i)));
            }
    }

    //task 5 -------------------------------------------------------
    //see paper

    //task 6 -------------------------------------------------------
    public static int recLifeLength(int x) {
        if (x == 1) {
            return 0;
        }
        else {
            return (1 + recLifeLength(f1(x)));
        }
    }

    public static void task6print() {
        System.out.println("iterativ 4:     rekursiv 6:");
        for (int i = 1; i <= 15; i++) {
            System.out.println(iterLifeLength(i) + "                  " + recLifeLength(i));
            }
    }

    //task 7 -------------------------------------------------------
    //see paper
}