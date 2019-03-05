package algorithms;

public class BigO {


    public void foo(int[] array) {

        for (int i = 0; i < array.length; i++) {
            // ...
            // O(n)
        }

        for (int i = 0; i < array.length; i++) {
            // ...
            // O(n)
        }

        // O(2n) -> O(n)
    }

    public void bar(int[] array1, int[] array2) {

        for (int i = 0; i < array1.length; i++) {
            // ...
            // O(n)
            for (int j = 0; j < array2.length; j++) {
                // ...
                // O(n)
            }
        }

        // O(n^2)
        // O(n * m)
    }

    public void baz(int[] array) {

        for (int i = 0; i < array.length; i++) {
            // ...
            // O(n)
            for (int j = 0; j < array.length; j++) {
                // ...
                // O(n)
                if (array[i] < array[j]) {
                    // Launch!
                    // O(1)
                }
            }
        }

        // O(n^2)
    }

    public void beep(int[] array) {

        for (int i = 0; i < array.length; i++) {
            // ...
            // O(n)
            for (int j = 0; j < array.length; j++) {
                // ...
                // O(n)
                for (int k = 0; k < 9999999; k++) {
                    // Jump!
                    // O(1)
                }
            }
        }

        // O(n^2)
    }

}