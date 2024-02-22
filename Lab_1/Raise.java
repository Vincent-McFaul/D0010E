public class Raise {

    //global call counters
    static int counterOne = 0;
    static int counterHalf = 0;
    public static void main(String[] args) {

        //task 8 & 9
        for(int i = 1; i <= 10000; i++) {
            counterOne = 0;
            counterHalf = 0;

            System.out.println("k=" + i);
            System.out.println("One:  " + recRaiseOne(1.000001, i) + "   recusion steps: " + counterOne);
            System.out.println("Half: " + recRaiseHalf(1.000001, i) + "   recusion steps: " + counterHalf);
        }
        
        //testing, ignore this
        /*
        for(int i = 0; i <= 1000; i+=10) {
            counterOne = 0;
            //counterHalf = 0;

            //System.out.println("k=" + i);
            //System.out.println("One:  " + recRaiseOne(1.0001, i) + "   recusion steps: " + counterOne);
            //System.out.println("Half: " + recRaiseHalf(1.0001, i) + "   recusion steps: " + counterHalf + "\n");

            System.out.println(recRaiseOne(1.0005, i) + "  k="+i+"   steps=" + counterOne);

        }
        */

    }

    public static double recRaiseOne(double x, int k) {
        counterOne = counterOne + 1;
        if (k == 0) {
            return 1.0;
        } 
        else {
            return (x * recRaiseOne(x, k-1));
        }
    }

    public static double recRaiseHalf(double x, int k) {
        counterHalf = counterHalf + 1;
        double xOriginal = x;
        int kHalf = (k / 2); //int floors it by default

        if (k == 0) {
            return 1.0;
        }
        if (k % 2 == 0) {
            x = recRaiseHalf(xOriginal, kHalf); //x is raised by k/2 somehow
            return (x * x); //squares it
        }
        else {
            x = recRaiseHalf(xOriginal, kHalf); //sends it to the nether
            return (x * x * xOriginal);
        }
    }
}