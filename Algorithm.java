import java.util.Arrays;

/**
 * BasicSort & BasicSearch — A complete reference implementation.
 *
 * ┌─────────────────────────────────────────────────────────┐
 * │                  SORTING ALGORITHMS                     │
 * ├──────────────────┬──────────┬──────────┬───────────────-┤
 * │ Algorithm        │ Best     │ Average  │ Worst   Space  │
 * ├──────────────────┼──────────┼──────────┼───────────────-┤
 * │ Bubble Sort      │ O(n)     │ O(n²)    │ O(n²)   O(1)  │
 * │ Selection Sort   │ O(n²)    │ O(n²)    │ O(n²)   O(1)  │
 * │ Insertion Sort   │ O(n)     │ O(n²)    │ O(n²)   O(1)  │
 * │ Merge Sort       │ O(nlogn) │ O(nlogn) │ O(nlogn) O(n) │
 * │ Quick Sort       │ O(nlogn) │ O(nlogn) │ O(n²)   O(logn│
 * ├─────────────────────────────────────────────────────────┤
 * │                  SEARCHING ALGORITHMS                   │
 * ├──────────────────┬──────────┬──────────┬───────────────-┤
 * │ Algorithm        │ Best     │ Average  │ Worst   Space  │
 * ├──────────────────┼──────────┼──────────┼───────────────-┤
 * │ Linear Search    │ O(1)     │ O(n)     │ O(n)    O(1)  │
 * │ Binary Search    │ O(1)     │ O(logn)  │ O(logn) O(1)  │
 * │ Binary Search(R) │ O(1)     │ O(logn)  │ O(logn) O(logn│
 * └─────────────────────────────────────────────────────────┘
 */
public class Algorithm {

    // ═══════════════════════════════════════════════════════
    //  SORTING ALGORITHMS
    // ═══════════════════════════════════════════════════════

    // ───────────────────────────────────────────────────────
    //  Bubble Sort
    // ───────────────────────────────────────────────────────

    /**
     * Sorts an array using the Bubble Sort algorithm.
     *
     * Repeatedly compares adjacent elements and swaps them if they are
     * in the wrong order. After each pass, the largest unsorted element
     * "bubbles up" to its correct position at the end.
     *
     * The outer loop (i) shrinks the unsorted region from right to left.
     * The inner loop (j) does one full pass through the unsorted region.
     *
     * Example:
     *   [5, 3, 8, 1]
     *   Pass 1: [3, 5, 1, 8]   → 8 is in place
     *   Pass 2: [3, 1, 5, 8]   → 5 is in place
     *   Pass 3: [1, 3, 5, 8]   ✅
     *
     * Time:  O(n²) average/worst | O(n) best (already sorted w/ flag)
     * Space: O(1) — in-place
     *
     * @param array The integer array to sort (modified in place).
     */
    public void bubbleSort(int[] array) {
        // i tracks the boundary of the unsorted region.
        // After each pass, the last element of the unsorted region is sorted.
        for (int i = array.length - 1; i >= 0; i--) {

            // Walk through the unsorted region and bubble the largest element up.
            for (int j = 0; j < i; j++) {

                // If the current element is greater than the next, swap them.
                if (array[j] > array[j + 1]) {
                    swapped(array, j, j + 1);
                }
            }
        }
    }

    // ───────────────────────────────────────────────────────
    //  Selection Sort
    // ───────────────────────────────────────────────────────

    /**
     * Sorts an array using the Selection Sort algorithm.
     *
     * Divides the array into a sorted (left) and unsorted (right) region.
     * In each pass, finds the MINIMUM element in the unsorted region
     * and swaps it into the correct position at the start of that region.
     *
     * Example:
     *   [5, 3, 8, 1]
     *   Pass 1: min=1 → swap with index 0 → [1, 3, 8, 5]
     *   Pass 2: min=3 → already in place  → [1, 3, 8, 5]
     *   Pass 3: min=5 → swap with index 2 → [1, 3, 5, 8] ✅
     *
     * Time:  O(n²) always — always scans the full unsorted region
     * Space: O(1) — in-place
     *
     * @param array The integer array to sort (modified in place).
     */
    public void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {

            // Assume the first element of the unsorted region is the minimum.
            int minIndex = i;

            // Scan the unsorted region to find the actual minimum.
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            // Only swap if a smaller element was actually found.
            if (i != minIndex) {
                swapped(array, i, minIndex);
            }
        }
    }

    // ───────────────────────────────────────────────────────
    //  Insertion Sort
    // ───────────────────────────────────────────────────────

    /**
     * Sorts an array using the Insertion Sort algorithm.
     *
     * Builds a sorted region on the left one element at a time.
     * For each new element, it is shifted left until it is in the
     * correct position within the already-sorted region.
     *
     * Think of it like sorting playing cards in your hand:
     * pick one card and slide it left until it's in the right spot.
     *
     * Example:
     *   [5, 3, 8, 1]
     *   i=1: temp=3 → shift 5 right → [5, 5, 8, 1] → insert → [3, 5, 8, 1]
     *   i=2: temp=8 → 8 > 5, no shift  → [3, 5, 8, 1]
     *   i=3: temp=1 → shift 8,5,3      → [1, 3, 5, 8] ✅
     *
     * Time:  O(n²) average/worst | O(n) best (already sorted)
     * Space: O(1) — in-place
     *
     * @param array The integer array to sort (modified in place).
     */
    public void insertionSort(int[] array) {
        // Start from index 1; index 0 is trivially "sorted" already.
        for (int i = 1; i < array.length; i++) {

            // Save the element to be inserted into the sorted region.
            int temp = array[i];
            int j = i - 1;

            // Shift elements of the sorted region to the right
            // until we find the correct position for temp.
            while (j != -1 && array[j] > temp) {
                array[j + 1] = array[j]; // shift element right
                j--;
            }

            // Place temp in its correct sorted position.
            array[j + 1] = temp;
        }
    }

    // ───────────────────────────────────────────────────────
    //  Merge Sort
    // ───────────────────────────────────────────────────────

    /**
     * Sorts an array using the Merge Sort algorithm (Divide & Conquer).
     *
     * Recursively splits the array in half until each sub-array has
     * one element (which is trivially sorted), then merges them back
     * together in sorted order using the merge() helper.
     *
     * Example:
     *   [38, 27, 43, 3]
     *   Split  → [38, 27]      [43, 3]
     *   Split  → [38] [27]     [43] [3]
     *   Merge  → [27, 38]      [3, 43]
     *   Merge  → [3, 27, 38, 43] ✅
     *
     * Time:  O(n log n) always
     * Space: O(n) — new arrays are created at each merge step
     *
     * @param array The integer array to sort.
     * @return A new sorted integer array.
     */
    public int[] mergeSort(int[] array) {
        // Base case: a single element is already sorted.
        if (array.length == 1) return array;

        // Find the midpoint and split into two halves.
        int midIndex = array.length / 2;
        int[] left  = mergeSort(Arrays.copyOfRange(array, 0, midIndex));
        int[] right = mergeSort(Arrays.copyOfRange(array, midIndex, array.length));

        // Merge the two sorted halves and return the result.
        return merge(left, right);
    }

    /**
     * Merges two sorted arrays into one sorted array.
     *
     * Uses two pointers (i, j) to walk through both arrays simultaneously,
     * always picking the smaller of the two current elements.
     * Any remaining elements (when one array is exhausted) are copied directly.
     *
     * Time:  O(n) — each element is visited exactly once
     * Space: O(n) — a new combined array is allocated
     *
     * @param array1 First sorted array.
     * @param array2 Second sorted array.
     * @return A new merged and sorted array.
     */
    public int[] merge(int[] array1, int[] array2) {
        int[] combined = new int[array1.length + array2.length];
        int index = 0; // pointer for combined
        int i = 0;     // pointer for array1
        int j = 0;     // pointer for array2

        // Compare front elements of both arrays; take the smaller one.
        while (i < array1.length && j < array2.length) {
            if (array1[i] < array2[j]) {
                combined[index++] = array1[i++];
            } else {
                combined[index++] = array2[j++];
            }
        }

        // Copy any remaining elements from array1 (if any).
        while (i < array1.length) {
            combined[index++] = array1[i++];
        }

        // Copy any remaining elements from array2 (if any).
        while (j < array2.length) {
            combined[index++] = array2[j++];
        }

        return combined;
    }

    // ───────────────────────────────────────────────────────
    //  Quick Sort
    // ───────────────────────────────────────────────────────

    /**
     * Public entry point for Quick Sort.
     * Delegates to the recursive helper with full array bounds.
     *
     * Time:  O(n log n) average | O(n²) worst (sorted array + bad pivot)
     * Space: O(log n) — recursive call stack
     *
     * @param array The integer array to sort (modified in place).
     */
    public void quickSort(int[] array) {
        quickSortHelper(array, 0, array.length - 1);
    }

    /**
     * Recursive Quick Sort helper that sorts the sub-array array[left..right].
     *
     * 1. Partition the sub-array around a pivot → pivot lands in final position.
     * 2. Recursively sort the left part  (elements smaller than pivot).
     * 3. Recursively sort the right part (elements larger  than pivot).
     *
     * @param array The array being sorted.
     * @param left  Start index of the sub-array.
     * @param right End index of the sub-array.
     */
    public void quickSortHelper(int[] array, int left, int right) {
        // Base case: sub-array of size 0 or 1 is already sorted.
        if (left < right) {
            int pivotIndex = pivot(array, left, right);

            // Recursively sort everything to the left of the pivot.
            quickSortHelper(array, left, pivotIndex - 1);

            // Recursively sort everything to the right of the pivot.
            quickSortHelper(array, pivotIndex + 1, right);
        }
    }

    /**
     * Partitions the sub-array around a pivot element and returns the pivot's final index.
     *
     * Uses the first element (array[pivotIndex]) as the pivot.
     * Walks through the sub-array: whenever an element smaller than the pivot
     * is found, it is swapped into the "less than" region, which grows from left to right.
     * Finally, the pivot itself is placed between the two regions.
     *
     * Example:
     *   [3, 6, 8, 2, 1]  pivot = 3 (index 0)
     *   i=1: 6 > 3 → skip
     *   i=2: 8 > 3 → skip
     *   i=3: 2 < 3 → swapIndex=1, swap(1,3) → [3, 2, 8, 6, 1]
     *   i=4: 1 < 3 → swapIndex=2, swap(2,4) → [3, 2, 1, 6, 8]
     *   Place pivot: swap(0,2)               → [1, 2, 3, 6, 8]
     *                              pivot ↑ at index 2 ✅
     *
     * @param array      The array being partitioned.
     * @param pivotIndex Start of the sub-array (pivot element).
     * @param endIndex   End of the sub-array.
     * @return The final index of the pivot after partitioning.
     */
    public int pivot(int[] array, int pivotIndex, int endIndex) {
        // swappedIndex tracks the boundary of the "less than pivot" region.
        int swappedIndex = pivotIndex;

        for (int i = pivotIndex + 1; i <= endIndex; i++) {
            // If this element belongs on the left side (< pivot), expand the region.
            if (array[pivotIndex] > array[i]) {
                swappedIndex++;
                swapped(array, swappedIndex, i);
            }
        }

        // Place the pivot at the boundary between the two regions.
        swapped(array, pivotIndex, swappedIndex);

        return swappedIndex; // Return the pivot's final sorted position.
    }

    // ───────────────────────────────────────────────────────
    //  Shared Utility
    // ───────────────────────────────────────────────────────

    /**
     * Swaps two elements in an array by their indices.
     *
     * @param array  The array containing the elements.
     * @param fIndex Index of the first element.
     * @param sIndex Index of the second element.
     */
    public void swapped(int[] array, int fIndex, int sIndex) {
        int temp      = array[fIndex];
        array[fIndex] = array[sIndex];
        array[sIndex] = temp;
    }


    // ═══════════════════════════════════════════════════════
    //  SEARCHING ALGORITHMS
    // ═══════════════════════════════════════════════════════

    // ───────────────────────────────────────────────────────
    //  Linear Search
    // ───────────────────────────────────────────────────────

    /**
     * Searches for a target value using Linear Search.
     *
     * Scans every element from left to right until the target is found
     * or the entire array has been checked. Works on UNSORTED arrays.
     *
     * Example:
     *   array = [5, 3, 8, 1, 9],  target = 8
     *   Check 5 → no
     *   Check 3 → no
     *   Check 8 → YES ✅ return index 2
     *
     * Time:  O(n) — may need to check every element
     * Space: O(1)
     *
     * @param array  The array to search (can be unsorted).
     * @param target The value to find.
     * @return The index of the target if found, -1 otherwise.
     */
    public int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i; // Target found — return its index.
            }
        }
        return -1; // Target not found.
    }

    // ───────────────────────────────────────────────────────
    //  Binary Search (Iterative)
    // ───────────────────────────────────────────────────────

    /**
     * Searches for a target value using Binary Search (iterative version).
     *
     * Requires a SORTED array. Repeatedly halves the search space:
     *   - If middle element == target → found.
     *   - If middle element  < target → search right half.
     *   - If middle element  > target → search left half.
     *
     * Example:
     *   array = [1, 3, 5, 8, 9],  target = 8
     *   left=0, right=4 → mid=2 → array[2]=5  < 8 → search right
     *   left=3, right=4 → mid=3 → array[3]=8 == 8 → found ✅ return 3
     *
     * Time:  O(log n) — halves the search space each step
     * Space: O(1)     — no recursion, just pointers
     *
     * @param array  A SORTED integer array to search.
     * @param target The value to find.
     * @return The index of the target if found, -1 otherwise.
     */
    public int binarySearch(int[] array, int target) {
        int left  = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // Avoids integer overflow vs (left+right)/2

            if (array[mid] == target) {
                return mid;              // Target found.
            } else if (array[mid] < target) {
                left = mid + 1;          // Target is in the right half.
            } else {
                right = mid - 1;         // Target is in the left half.
            }
        }

        return -1; // Target not found.
    }

    // ───────────────────────────────────────────────────────
    //  Binary Search (Recursive)
    // ───────────────────────────────────────────────────────

    /**
     * Public entry point for Recursive Binary Search.
     * Delegates to the recursive helper with full array bounds.
     *
     * @param array  A SORTED integer array to search.
     * @param target The value to find.
     * @return The index of the target if found, -1 otherwise.
     */
    public int binarySearchRecursive(int[] array, int target) {
        return binarySearchHelper(array, target, 0, array.length - 1);
    }

    /**
     * Recursive Binary Search helper.
     *
     * Same logic as the iterative version but implemented with recursion.
     * Each call narrows the search window until the target is found or
     * the window is empty (left > right).
     *
     * Time:  O(log n)
     * Space: O(log n) — recursive call stack depth
     *
     * @param array  The sorted array.
     * @param target The value to find.
     * @param left   Start of the current search window.
     * @param right  End of the current search window.
     * @return The index of the target if found, -1 otherwise.
     */
    private int binarySearchHelper(int[] array, int target, int left, int right) {
        // Base case: search window is empty — target not found.
        if (left > right) return -1;

        int mid = left + (right - left) / 2;

        if (array[mid] == target) {
            return mid;                                            // Found.
        } else if (array[mid] < target) {
            return binarySearchHelper(array, target, mid + 1, right); // Search right.
        } else {
            return binarySearchHelper(array, target, left, mid - 1);  // Search left.
        }
    }
}
