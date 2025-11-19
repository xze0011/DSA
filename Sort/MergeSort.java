public class MergeSort {

    public static void main(String[] args) {
        int[] data = {5, 2, 9, 1, 5, 6};

        MergeSort sorter = new MergeSort();
        sorter.mergeSort(data);

        for (int num : data) {
            System.out.print(num + " ");
        }
    }

    public void mergeSort(int[] array) {
        int[] helper = copy(array);
        mergeSort(array, helper, 0, array.length - 1);
    }

    public void mergeSort(int[] array, int[] helper, int left, int right) {
        if (right - left < 1) return;
        int mid = left + (right - left) / 2;
        mergeSort(array, helper, left, mid);
        mergeSort(array, helper, mid + 1, right);
        merge(array, helper, left, mid, right);
    }

    public void merge(int[] array, int[] helper, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            helper[i] = array[i];
        }

        int leftStart = left;
        int rightStart = mid + 1;

        for (int i = left; i <= right; i++) {
            if (leftStart > mid) {
                array[i] = helper[rightStart++];
            } else if (rightStart > right) {
                array[i] = helper[leftStart++];
            } else if (helper[leftStart] < helper[rightStart]) {
                array[i] = helper[leftStart++];
            } else {
                array[i] = helper[rightStart++];
            }
        }
    }

    public int[] copy(int[] array) {
        int[] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
}