package Yahtzee;

import java.util.ArrayList;

public class util {
    public static Integer[] choices(String prompt, Integer[] roll){
        String[] parts = prompt.split(" ");
        ArrayList<Integer> res = new ArrayList<>();
        for(String s : parts){
            try{
                int idx = Integer.parseInt(s) - 1;
                if(idx >= 0 && idx < roll.length){
                    res.add(roll[idx]);
                }
            }catch(Exception e){
                //ignore invalid inputs
            }
        }
        return res.toArray(new Integer[0]);
    }

    public static Rules[] apply(Rules[] rules, Boolean[] used, Integer[] rolls){
        ArrayList<Rules> res = new ArrayList<>();
        for(int i = 0; i < rules.length; i++){
            if(!used[i]){
                res.add(rules[i]);
            }
        }
        res.removeIf(r -> r.score(rolls) == -1);
        return res.toArray(new Rules[0]);
    }
}
