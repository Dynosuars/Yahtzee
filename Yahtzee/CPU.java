package Yahtzee;

import javax.swing.JOptionPane;

public class CPU extends Player{
    public CPU(String name, Rules[] rules) {
        super(name, rules);
    }

    /**
     * I hope this is lowkey kinda tuff because it's kinda AI
     * @param rules<Rules>, the rule sets
     * @param game<Game>, the game object
     */
    @Override
    public void Turn(Rules[] rules, Game game) {
        int rolls = 0;
        boolean roll = true;
        Integer[] keep = new Integer[0];

        while(roll){
            game.roll();
            Integer[] current = game.getRoll();


            //Determine which to keep
            for(int i : current){
                boolean toKeep = false;
                for(int k : keep){
                    if(i == k){
                        toKeep = true;
                        break;
                    }
                }
                if(!toKeep){
                    Rules[] avaliable = util.apply(rules, this.usedRules, current);
                    for(Rules r : avaliable){
                        Integer[] testRoll = new Integer[current.length];
                        for(int j = 0; j < current.length; j++){
                            testRoll[j] = keep.length > j ? keep[j] : i;
                        }
                        if(r.score(testRoll) > r.score(keep)){
                            toKeep = true;
                            break;
                        }
                    }
                }
                if(toKeep){
                    Integer[] newKeep = new Integer[keep.length + 1];
                    System.arraycopy(keep, 0, newKeep, 0, keep.length);
                    newKeep[keep.length] = i;
                    keep = newKeep;
                }
            }
            JOptionPane.showMessageDialog(null, this.name + " rolled:\n" + String.join(" ", java.util.Arrays.stream(current).map(Object::toString).toArray(String[]::new)), this.name + "'s roll", JOptionPane.INFORMATION_MESSAGE);

            rolls++;
            if(rolls >= 2){
                roll = false;
            }
        }

        Rules[] avaliable = util.apply(rules, this.usedRules, game.getRoll());

        if(avaliable.length == 0){
            String output = this.name + " has no available rules to apply!\nCurrent Roll:\n";
            for(Integer i : game.getRoll()){
                output += i + " ";
            }

            for(int i = 0; i < this.usedRules.length; i++){
                if(!this.usedRules[i]){
                    this.usedRules[i] = true;
                    output += "\n" + this.name + " has been forced to skip the rule: " + rules[i].name + " As a punishment";
                    JOptionPane.showMessageDialog(null, output, "Skill issue",JOptionPane.INFORMATION_MESSAGE);
                    break;
                };
            }
            return;
        }
        
        int bestIdx = 0;
        int bestScore = -1;
        for(int i = 0; i < avaliable.length; i++){
            int score = avaliable[i].score(game.getRoll());
            if(score > bestScore){
                bestScore = score;
                bestIdx = i;
            }
        }

        JOptionPane.showMessageDialog(null, this.name + " chose to apply the rule: " + avaliable[bestIdx].name + " for " + avaliable[bestIdx].score(game.getRoll()) + " points.", this.name + "'s decision", JOptionPane.INFORMATION_MESSAGE);

        this.score += avaliable[bestIdx].score(game.getRoll());
        this.usedRules[bestIdx] = true;
    }

    
}
