import java.math.BigInteger;
import java.util.*;

class RSA {
    public static void main(String args[]) {
        Scanner ip = new Scanner(System.in);
        int p, q, n, e = 2, j;
        int d = 1, len;
        int t1, t2;
        int pt[] = new int[100];
        int ct[] = new int[100];
        int rt[] = new int[100];
        int temp[] = new int[100];
        String msg;

        System.out.println("Enter the two prime numbers:");
        p = ip.nextInt();
        q = ip.nextInt();

        System.out.println("Enter the message to be sent:");
        msg = ip.next();
        len = msg.length();

        n = p * q;
        t1 = p - 1;
        t2 = q - 1;

        System.out.println("\n___________");
        System.out.println("Sender Side:");

        // Find e such that gcd(e, φ(n)) = 1
        while (gcd(e, t1 * t2) != 1) {
            e++;
        }

        System.out.println("Public Key(e) = " + e);
        System.out.println("____________");

        for (j = 0; j < len; j++) {
            char ch = msg.charAt(j);
            if (Character.isUpperCase(ch)) {
                pt[j] = ch - 64; // A=1, B=2, ...
            } else if (Character.isLowerCase(ch)) {
                pt[j] = ch - 96; // a=1, b=2, ...
            } else {
                pt[j] = ch; // for other characters, use raw ASCII
            }

            System.out.println("Plain Text = " + pt[j]);

            // Encrypt using BigInteger
            BigInteger base = BigInteger.valueOf(pt[j]);
            BigInteger encrypted = base.modPow(BigInteger.valueOf(e), BigInteger.valueOf(n));
            ct[j] = encrypted.intValue();

            System.out.println("Cipher Text = " + ct[j]);
        }

        System.out.println("\nTransmitted Encrypted Message:");
        for (j = 0; j < len; j++) {
            if (Character.isUpperCase(msg.charAt(j))) {
                temp[j] = ct[j] + 64;
            } else if (Character.isLowerCase(msg.charAt(j))) {
                temp[j] = ct[j] + 96;
            } else {
                temp[j] = ct[j]; // no offset for non-letters
            }
            System.out.print((char) temp[j]);
        }

        System.out.println("\n\n-------------");
        System.out.println("Receiver Side:");

        // Find d such that (d * e) % φ(n) == 1
        while ((d * e) % (t1 * t2) != 1) {
            d++;
        }

        System.out.println("Private key(d) = " + d);
        System.out.println("--------------");

        for (j = 0; j < len; j++) {
            BigInteger cipher = BigInteger.valueOf(ct[j]);
            BigInteger decrypted = cipher.modPow(BigInteger.valueOf(d), BigInteger.valueOf(n));
            rt[j] = decrypted.intValue();
        }

        System.out.println("\nDecrypted Message:");
        for (j = 0; j < len; j++) {
            char originalChar;
            if (Character.isUpperCase(msg.charAt(j))) {
                originalChar = (char) (rt[j] + 64);
            } else if (Character.isLowerCase(msg.charAt(j))) {
                originalChar = (char) (rt[j] + 96);
            } else {
                originalChar = (char) rt[j];
            }
            System.out.print(originalChar);
        }

        System.out.println("\n------------");
        ip.close();
    }

    // Helper to calculate GCD
    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}

