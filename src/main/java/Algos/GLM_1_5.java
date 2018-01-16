package Algos;

//class IllegalDeltaLenException extends Exception {
//    void IllegalDeltaLenException(String msg) {
//        super(msg);
//    }
//};

public class GLM_1_5 {

    // If strings are 0 or one edits apart, return true, else false
    static boolean oneAway(String first, String second) throws Exception {

        // Assume: ok to use string comparison

        // Check corner/trivial cases
        if (first == second ) return true;

        int deltaLen = first.length() - second.length();
        if ( Math.abs(deltaLen) > 1 ) return false;

        // We now now that deltaLen is one of [-1,0,1]
        boolean diffDetected = false;
        int idx_first = 0, idx_second = 0;

        while (idx_first < first.length() && idx_second < second.length()) {
            if (first.charAt(idx_first) == second.charAt(idx_second)) {
                idx_first++;
                idx_second++;

                if ((idx_first == first.length() && idx_second != second.length()) ||
                        (idx_second == second.length() && idx_first != first.length())) {
                    if (!diffDetected) return true;
                    else return false;
                }


            } else {
                if (diffDetected) { return false; } // Second difference detected -> answer is 'false'
                else {
                    diffDetected = true;
                    if (deltaLen == 0) {
                        idx_first++;
                        idx_second++;
                    } else if (deltaLen == -1) {
                        idx_second++;
                    } else if (deltaLen == 1) {
                        idx_first++;
                    } else {
                        throw new Exception("deltaLen outside range [-1,0,1]: " + deltaLen);
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String first = "pales", second = "pale";
        try {
            System.out.println("first: " + first + ", second: " +
                    second + ", oneAway: " + oneAway(first, second));
        } catch (Exception e) {
            System.err.println("Exception caught: " + e);
        }
    }

}
