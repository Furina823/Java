package utility;

public class ReArrangeArray {

    public ReArrangeArray(){}

    public static String[] rearrangeArray(String[] array, String target) {
        // Check if the target string is already in the array
        boolean found = false;
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(target)) {
                found = true;
                index = i;
                break;
            }
        }

        // Create a new array with target as the first element if not found
        String[] result;
        if (found) {
            // Target string is in the array, rearrange as before
            result = new String[array.length];
            result[0] = array[index];
            System.arraycopy(array, 0, result, 1, index);
            System.arraycopy(array, index + 1, result, index + 1, array.length - index - 1);
        } else {
            // Target string is not in the array, create a new array with target as the first element
            result = new String[array.length + 1];
            result[0] = target;
            System.arraycopy(array, 0, result, 1, array.length);
        }

        return result;
    }

}
