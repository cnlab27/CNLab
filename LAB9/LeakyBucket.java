import java.util.Scanner;

public class LeakyBucket {
    public static void main(String[] args) throws InterruptedException {
        int n, incoming, outgoing, store = 0, bucketsize;
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter bucket size:");
        bucketsize = scan.nextInt();
         System.out.println("\noutgoing rate:");
        outgoing = scan.nextInt();
        System.out.println("\nincoming size:");
        incoming = scan.nextInt();
         System.out.println("\nnumber of inputs cycles:");
        n = scan.nextInt();
        

        while (n != 0) {
            System.out.println("Incoming size is " + incoming);

            if (incoming <= (bucketsize - store)) {
                store += incoming;
                System.out.println("Bucket buffer size is " + store + " out of " + bucketsize);
            } else {
                System.out.println("Packet loss: " + (incoming - (bucketsize - store)));
                store = bucketsize;
                System.out.println("Bucket buffer size is " + store + " out of " + bucketsize);
            }

            store -= outgoing;
            if (store < 0)
                store = 0;

            System.out.println("After outgoing: " + store + " packets left out of " + bucketsize + " in buffer");
            System.out.println("----------------------------------------");

            n--;
            Thread.sleep(2000);
        }

        scan.close();
    }
}

