package Yahtzee;

import java.util.Random;


/**
 * The dice to roll with
 */
public class Dice {
    private final long seed;
    private final int side;
    private final Random random;

    /**
     * Creates a DICE object
     * @param side
     * @param seed
     */
    public Dice(int side, int seed){
        this.side = side;
        this.seed = seed;
        this.random = new Random(this.seed);
    }

    /**
     * Creates a DICE object with default seed
     * @param sides
     */
    public Dice(int sides){
        this(sides, 67676767);
    }

    /**
     * rolls the dice
     * @return
     */
    public int roll(){
        return (int) Math.abs(this.random.nextLong(this.seed)) % this.side + 1;
    }

    public void reset(){
        this.random.setSeed(this.random.nextLong(this.seed)); // Ez work
    }



}
