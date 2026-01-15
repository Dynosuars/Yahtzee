package Yahtzee;

public class Game {
    private final Dice[] die;
    private final Integer[] rolls;
    private final Rules[] rules;
    private int round;

    public Game(int size, int sides, Rules[] rules) {
        this.die = new Dice[size];
        this.rolls = new Integer[size];
        this.rules = rules;
        this.round = 0;
        
        for(int i = 0 ; i < size; i++){
            this.die[i] = new Dice(sides, (int)(Math.random() * 123123) + i);
        }
    }

    public void roll(){
        for(int i=0; i < this.die.length; i++){
            this.rolls[i] = this.die[i].roll();
        }
    }

    public Integer[] getRoll(){
        return this.rolls;
    }

    public void newRound(){
        this.round++;
    }

    public int getRound(){
        return this.round;
    }

    

    
    
}
