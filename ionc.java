import java.util.ArrayList;
import java.util.Iterator;
public class ionc {
    public static void main(String[] args){
        ArrayList<Integer> arr_list = new ArrayList<Integer>();
        arr_list.add(10);
        arr_list.add(20);
        arr_list.add(30);

        for(int i = 0; i<arr_list.size(); i++){
            System.out.println(arr_list.get(i));
        }

        Iterator<Integer> ir = arr_list.iterator();
        while(ir.hasNext()){
            System.out.println(ir.next());
        }
    }
}
