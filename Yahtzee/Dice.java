package Yahtzee;

import java.util.Random;

public class Dice {
    private final long seed;
    private final int side;
    private final Random random;

    public Dice(int side, int seed){
        this.side = side;
        this.seed = seed;
        this.random = new Random(this.seed);
    }

    public Dice(int sides){
        this(sides, 67676767);
    }

    public int roll(){
        return (int) Math.abs(this.random.nextLong(this.seed)) % this.side + 1;
    }



}
