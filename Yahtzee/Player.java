package Yahtzee;

import javax.swing.JOptionPane;

public class Player {
    public final String name;
    // Polymorphism go brr :shush::deaf_man: 
    protected int score;
    protected final Boolean[] usedRules;

    /**
     * Creates a player object like a true oop dev
     * @param name
     * @param rules
     */
    public Player(String name, Rules[] rules) {
        this.name = name;
        this.score = 0;
        this.usedRules = new Boolean[rules.length];
        for(int i = 0; i < this.usedRules.length; i++){
            this.usedRules[i] = false;
        }
    }
    /**
     * The methods for a player to take turn and this is MUCH easier because I don't have to worry about scope issues
     * @param rule
     * @param game
     */
    public void Turn(Rules[] rule, Game game) {
        boolean roll = true;
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
            output += "\nSelect the die you want to hold, starting with 1, seperated by space. EX: 1 2 3\nScore: " + this.score + "\nRolls used: " + (rolls) + "/3\n(Leave blank to stop rolling)";
            option = JOptionPane.showInputDialog(null, output, this.name + "'s turn", JOptionPane.QUESTION_MESSAGE);
            if(option == null || option.isEmpty()){
                roll = false;
                break;
            }

            keep = util.choices(option, current);
            if(rolls >= 2){
                roll = false;
            }
            rolls++;
        }

        Rules[] avaliable = util.apply(rule, this.usedRules, game.getRoll());
        String[] ruleOptions = new String[avaliable.length];

        if(avaliable.length == 0){
            output = "No available rules to apply!\nCurrent Roll:\n";
            for(Integer i : game.getRoll()){
                output += i + " ";
            }

            for(int i = 0; i < this.usedRules.length; i++){
                if(!this.usedRules[i]){
                    this.usedRules[i] = true;
                    output += "\nYou have been forced to skip the rule: " + rule[i].name + " As a punishment";
                    JOptionPane.showMessageDialog(null, output, "Skill issue",JOptionPane.INFORMATION_MESSAGE);
                    break;
                };
            }
        }else{
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

    }
    /**
     * Gets the current score used to compare
     * @return this.score
     */
    public int getScore(){
        return this.score; 
    }

}
