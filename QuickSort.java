public class QuickSort {

    public static void main(String[] args) {
        int[] data = {5, 2, 9, 1, 5, 6};

        QuickSort sorter = new QuickSort();
        sorter.quickSort(data, 0, data.length - 1);


        for (int num : data) {
            System.out.print(num + " ");
        }
    }


    public void quickSort(int[] array, int left, int right) {
        if (left >= right) return;
        int partitionIndex = partition(array, left, right);
        quickSort(array, left, partitionIndex - 1);
        quickSort(array, partitionIndex + 1, right);
    }

    public int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int leftIndex = left;
        int rightIndex = right - 1;

        while (true) {
            while (leftIndex < right && array[leftIndex] <= pivot) {
                leftIndex++;
            }

            while (rightIndex >= left && array[rightIndex] > pivot) {
                rightIndex--;
            }

            if (leftIndex > rightIndex) break;

            swap(array, leftIndex, rightIndex);
        }

        swap(array, leftIndex, right);
        return leftIndex;
    }

    public void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}