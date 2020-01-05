package HuaWei;
import java.util.*;
public class test {

    public void reOrderArray(int [] array) {
        Queue<Integer> oddInteger = new LinkedList<>();
        Queue<Integer> evenInteger = new LinkedList<>();
        for (int i = 0; i < array.length; i++){
            if (array[i] % 2 == 0){
                evenInteger.add(array[i]);
            }else {
                oddInteger.add(array[i]);
            }
        }
        int i = 0;
        while (!oddInteger.isEmpty()){
            array[i++] = oddInteger.poll();
        }
        while (!evenInteger.isEmpty()){
            array[i++] = evenInteger.poll();
        }
        return;
    }
}
