
package com.mycompany.algorithms;
import java.util.*;   


public class Algorithms {
    public static void main(String[] args) {
        //prints start time of execution
        long startTime = System.currentTimeMillis();
        System.out.println("Start time: " + startTime + " Ms");
        int min = 1000;
        int max = 100000;
        int number_of_elements;
        System.out.print("\nthe minimum number: " + min);
        System.out.print("\nthe maximum number: " + max);
        System.out.println("\nnumber of elements: ");
        Scanner input = new Scanner(System.in);
        number_of_elements = input.nextInt();

        
        int[] bestCase = generateAscendingOrder(min, max, number_of_elements);
        int[] averageCase = generateRandomOrder(min, max, number_of_elements);
        int[] worstCase = generateDescendingOrder(min, max, number_of_elements);

        // Measure and display sorting times for each case
        measureSortingTimes(bestCase, "Best Case");//ascending order
        measureSortingTimes(averageCase, "Average Case");//RandomOrder
        measureSortingTimes(worstCase, "Worst Case");//DescendingOrder
        
        //prints end time of execution
        long endTime = System.currentTimeMillis();
        System.out.println("\nEnd time: " + endTime + " Ms");

        float elapsedTime = ((float) (endTime - startTime));
        System.out.println("Total execution time: " + elapsedTime + " Ms");
        
    }
    
     // Generate arrays for different order cases
    public static int[] generateAscendingOrder(int min, int max, int num) {
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = min + i; // Generating ascending order
        }
        return arr;
    }

    public static int[] generateDescendingOrder(int min, int max, int num) {
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = max - i; // Generating descending order for worst case
        }
        return arr;
    }

     public static int[] generateRandomOrder(int min, int max, int num) {
        int[] arr = new int[num];
        Random rand = new Random();
        for (int i = 0; i < num; i++) {
            arr[i] = rand.nextInt(max - min + 1) + min; // Generating random order average case
        }
        return arr;
    }

    public static void measureSortingTimes(int[] arr, String order) {
        System.out.println("\n" + order + ":");

        // Bubble Sort
        long startTimeBubble = System.currentTimeMillis();
        bubbleSort(arr.clone());
        long endTimeBubble = System.currentTimeMillis();
        float elapsedTimeBubble = ((float) (endTimeBubble - startTimeBubble));
        System.out.println("Bubble Sort - Time taken for " + order + " sorting: " + elapsedTimeBubble + " Ms");

        // Heap Sort
        long startTimeHeap = System.currentTimeMillis();
        heapSort(arr.clone());
        long endTimeHeap = System.currentTimeMillis();
        float elapsedTimeHeap = ((float) (endTimeHeap - startTimeHeap));
        System.out.println("Heap Sort - Time taken for " + order + " sorting: " + elapsedTimeHeap + " Ms");

        // Hybrid Merge Sort
        long startTimeHybrid = System.currentTimeMillis();
        hybridMergeSort(arr.clone(), 0, arr.length - 1);
        long endTimeHybrid = System.currentTimeMillis();
        float elapsedTimeHybrid = ((float) (endTimeHybrid - startTimeHybrid));
        System.out.println("Hybrid Merge Sort - Time taken for " + order + " sorting: " + elapsedTimeHybrid + " Ms");
    }
   
     

   // Bubble Sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Heap Sort
    public static void heapSort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    public static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }
    public static void hybridMergeSort(int[] arr, int left, int right) {
    if (right - left <= 10) {
        bubbleSortPartial(arr, left, right); // For small subarrays, use Bubble Sort
    } else {
        if (left < right) {
            int mid = left + (right - left) / 2;
            hybridMergeSort(arr, left, mid); // Recursively sort the left half
            hybridMergeSort(arr, mid + 1, right); // Recursively sort the right half
            merge(arr, left, mid, right); // Merge the sorted halves
        }
    }
}

public static void bubbleSortPartial(int[] arr, int left, int right) {
    for (int i = left; i <= right; i++) {
        for (int j = left; j <= right - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}

public static void merge(int[] arr, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    int[] L = new int[n1];
    int[] R = new int[n2];
    for (int i = 0; i < n1; ++i) {
        L[i] = arr[left + i];
    }
    for (int j = 0; j < n2; ++j) {
        R[j] = arr[mid + 1 + j];
    }
    int i = 0, j = 0;
    int k = left;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k] = L[i];
            i++;
        } else {
            arr[k] = R[j];
            j++;
        }
        k++;
    }
    while (i < n1) {
        arr[k] = L[i];
        i++;
        k++;
    }
    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }
}


    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
