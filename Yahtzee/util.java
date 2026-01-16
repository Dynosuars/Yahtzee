package Yahtzee;

import java.util.ArrayList;

public class util {
    /**
     *  Converts a space separated string of indices into an array of Integers from the roll
     * @param prompt The space separated string of indices (1-based)
     *  @param roll The current roll of dice
     * @return An array of Integers representing the selected dice
     * Sigma
     */
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

    /**
     * Applies the available rules based on used rules and current rolls
     * @param rules
     * @param used
     * @param rolls
     * @return
     */
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
