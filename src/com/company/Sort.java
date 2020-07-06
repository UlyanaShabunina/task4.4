package com.company;

import java.util.ArrayList;

public class Sort {
    public static ArrayList<SortState> sort(int[] arr) {
        ArrayList<SortState> lol = new ArrayList<>();
        for (int min = 0; min < arr.length - 1; min++) {
            SortState kek = new SortState();
            kek.setI(min);
            kek.setK(0);
            kek.setStage(arr.clone());
            lol.add(kek);
            int least = min;
            for (int k = min + 1; k < arr.length; k++) {
                kek=new SortState();
                if (arr[k] < arr[least]) {
                    least = k;
                }
                kek.setK(k);
                kek.setI(min);
                kek.setStage(arr.clone());
                lol.add(kek);
                if (k==arr.length-1){
                    kek=new SortState();
                    kek.setI(min);
                    kek.setK(k);
                    kek.setStage(arr.clone());
                }
            }
            int tmp = arr[min];
            arr[min] = arr[least];
            arr[least] = tmp;
            kek.setStage(arr.clone());
            lol.add(kek);
        }
        return lol;
    }
}
