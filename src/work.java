import java.util.*;

public class TwoSumFraud {

    public void findTwoSum(int[] arr,int target){

        HashMap<Integer,Integer> map=new HashMap<>();

        for(int i=0;i<arr.length;i++){

            int complement=target-arr[i];

            if(map.containsKey(complement)){

                System.out.println("Pair found: "+arr[i]+" + "+complement);
            }

            map.put(arr[i],i);
        }
    }
}