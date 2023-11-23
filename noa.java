public class noa {
    public static void main(String[] args){
        int[] arr = new int[3];
        try {
            arr[0] = 10;
            arr[1] = 20;
            arr[2] = 30;
            arr[3] = 40;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("배열 오류발생.");
        }
        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }
    }
}
