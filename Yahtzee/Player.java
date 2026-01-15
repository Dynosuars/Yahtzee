package Yahtzee;

import javax.swing.JOptionPane;

public class Player {
    public final String name;
    protected int score;
    protected final Boolean[] usedRules;

    public Player(String name, Rules[] rules) {
        this.name = name;
        this.score = 0;
        this.usedRules = new Boolean[rules.length];
        for(int i = 0; i < this.usedRules.length; i++){
            this.usedRules[i] = false;
        }
    }

    public void Turn(Rules[] rule, Game game) {
        boolean roll = true, decide = true;
        Integer[] current, keep = null;
        String option, output;
        int rolls = 0;

        while(roll){
            game.roll();
            current = game.getRoll();
            if(keep != null)
                for(int i = 0; i < keep.length; i++){
                    current[i] = keep[i];
                }

            output = "Current Roll:\n";
            for(Integer i : current){
                output += i + " ";
            }
            output += "\nSelect the dices you want to hold, starting with 1, seperated by space. EX: 1 2 3\nScore: " + this.score + "\nRolls used: " + (rolls + 1) + "/3\n(Leave blank to stop rolling)";
            option = JOptionPane.showInputDialog(null, output, this.name + "'s turn", JOptionPane.QUESTION_MESSAGE);
            if(option == null || option.isEmpty()){
                roll = false;
                break;
            }

            keep = util.choices(option, current);
            rolls++;
            if(rolls >= 3){
                roll = false;
            }

        }

        Rules[] avaliable = util.apply(rule, this.usedRules, game.getRoll());
        String[] ruleOptions = new String[avaliable.length];
        for(int i = 0; i < avaliable.length; i++)
            ruleOptions[i] = avaliable[i].name + " (" + avaliable[i].score(game.getRoll()) + " points)";
        
        output = "Select a rule to apply:\n";
        output += "Current Roll:\n";
        for(Integer i : game.getRoll()){
            output += i + " ";
        }
        output += "\nScore: " + this.score;


        String choice = (String) JOptionPane.showInputDialog(null, output, this.name + "'s decision", JOptionPane.QUESTION_MESSAGE, null, ruleOptions, ruleOptions[0]);
        int index;
        for(index = 0; index < ruleOptions.length; index++){
            if(ruleOptions[index].equals(choice))
                break;
        }

        this.score += avaliable[index].score(game.getRoll());
        this.usedRules[index] = true;

    }

    public int getScore(){
        return this.score;
    }

}
