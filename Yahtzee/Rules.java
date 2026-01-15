package Yahtzee;

import java.util.function.Function;

public class Rules {
    private final Function<Integer[], Integer> func;
    public final String name;

    public Rules(String name, Function<Integer[], Integer> func) {
        this.func = func;
        this.name = name;
    }


    public Integer score(Integer[] nums){
        return this.func.apply(nums);
    }
}
