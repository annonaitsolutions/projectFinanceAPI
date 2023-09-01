package annona.utility;

import java.util.Date;
import java.util.Random;

public class KeyGenerator {

    public static String generateKey(String prefix, String type) {

        String uuid = prefix + "-" + type + "-" + "xxxxxx";
        long d = new Date().getTime();
        Random random = new Random();

        for (int i = 0; i < (uuid.length()-prefix.length()-4); i++) {
            char c = uuid.charAt(i + prefix.length() + 4);
            int r = (int) ((d + random.nextDouble() * 16) % 16);

            if (c == 'x') {
//                uuid += Integer.toHexString(r);
                uuid = uuid.substring(0, i + prefix.length() + 4) + Integer.toHexString(r) + uuid.substring(i + prefix.length() + 4 + 1);
            } else if (c == 'y') {
//                uuid += Integer.toHexString((r & 0x3) | 0x8);

                uuid = uuid.substring(0, i + prefix.length() + 4) + Integer.toHexString((r & 0x3) | 0x8) + uuid.substring(i + prefix.length() + 4 + 1);
            } else {
//                uuid += c;
                uuid = uuid.substring(0, i + prefix.length() + 4) + c + uuid.substring(i + prefix.length() + 4 + 1);
            }
            d = (long) Math.floor(d / 16);
        }

        return uuid;
    }

    public static String generateTransactionKey() {

        long d1 = new Date().getTime();
        String uuid1 = "xxxxxx";
        Random random = new Random();

        for (int i = 0; i < uuid1.length(); i++) {
            char c1 = uuid1.charAt(i);
            int r1 = (int) ((d1 + random.nextDouble() * 16) % 16);

            if (c1 == 'x') {
                uuid1 = uuid1.substring(0, i) + Integer.toHexString(r1) + uuid1.substring(i + 1);
            } else if (c1 == 'y') {
                uuid1 = uuid1.substring(0, i) + Integer.toHexString((r1 & 0x3) | 0x8) + uuid1.substring(i + 1);
            }
            d1 = (long) Math.floor(d1 / 16);
        }

        return uuid1;

    }


}
