package plj.licona.club.user.utils;

/**
 * @author licona
 */
public class UIDRandom {
    public static String getRandomCode(int length) {
        if (length > 0) {
        String[] data = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
            String ans = "";
            for (int i = 0; i < length; i++) {
                int r = (int) (Math.random() * 61);
                ans += data[r];
            }
            return ans;
        } else {
            return "字符串长度应该大于0";
        }
    }
}
