package main;

import java.util.ArrayList;
import java.util.Random;

public class SRandom {

    static ArrayList<Integer> nextRandoms=new ArrayList<>();
    static Random r=new Random();

    public static void add(int random){
        nextRandoms.add(random);
    }

    public static int nextRandom(int max){
        if(nextRandoms.size()>0){
            return nextRandoms.remove(0) % max;
        }
        return r.nextInt(max);
    }
}
