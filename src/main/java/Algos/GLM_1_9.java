package Algos;

public class GLM_1_9 {

    // Clue: If s2 is a rotation of s1, the concatenation of s1
    // with itself must contain s2
    static boolean isStringRotation(String s1, String s2) {
        int len = s1.length();
        if (len == s2.length() && len > 0 ) {
            String s1s1 = s1 + s1;
            return s1s1.contains(s2);
        }
        return false;
    }

    public static void main(String[] args) {
        String s1 = "waterbottle";
        String s2 = "erbottlewat";
        System.out.println(isStringRotation(s1,s2));
    }

}
