package Yahtzee;

import javax.swing.JOptionPane;

public class CPU extends Player{
    public CPU(String name, Rules[] rules) {
        super(name, rules);
    }

    @Override
    public void Turn(Rules[] rules, Game game) {
        //Roll Phase
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
            if(rolls >= 3){
                roll = false;
            }
        }

        //Choose Rule Phase
        Rules[] avaliable = util.apply(rules, this.usedRules, game.getRoll());

        //Simple AI: Choose highest scoring rule
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

    public int getScore(){
        return this.score;
    }
    
}
