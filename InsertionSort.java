public class InsertionSort {

  public static void main(String[] args) {
      int[] data = {5, 2, 9, 1, 5, 6};

      insertionSort(data);

      for (int num : data) {
          System.out.print(num + " ");
      }
  }

  public static void insertionSort(int[] arr) {
    for(int i = 1;i < arr.length; i++){
        int current = arr[i];
        int j = i-1;
        while(j >=0 &&  arr[j] > current){
          arr[j+1] = arr[j];
          j--;
        }
        arr[j+1] = current;
    }
  }
}