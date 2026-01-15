
import Yahtzee.CPU;
import Yahtzee.Game;
import Yahtzee.Player;
import Yahtzee.Rules;
import javax.swing.JOptionPane;

public class Main {

    
    public static void main(String[] args) {
        Rules[] rules = {new Rules("Ones", (Integer[] ary) ->{
            int sum = 0;
            for(Integer i : ary){
                if(i == 1){
                    sum += i;
                }
            }
            return sum;
        }), new Rules("Twos", (Integer[] ary) ->{
            int sum = 0;
            for(Integer i : ary){
                if(i == 2){
                    sum += i;
                }
            }
            return sum;
        }),
        new Rules("Threes", (Integer[] ary) ->{
            int sum = 0;
            for(Integer i : ary){
                if(i == 3){
                    sum += i;
                }
            }
            return sum;
        }),
        new Rules("Fours", (Integer[] ary) ->{
            int sum = 0;
            for(Integer i : ary){
                if(i == 4){
                    sum += i;
                }
            }
            return sum;
        }),
        new Rules("Fives", (Integer[] ary) ->{
            int sum = 0;
            for(Integer i : ary){
                if(i == 5){
                    sum += i;
                }
            }
            return sum;
        }),
        new Rules("Sixes", (Integer[] ary) ->{
            int sum = 0;
            for(Integer i : ary){
                if(i == 6){
                    sum += i;
                }
            }
            return sum;
        }),
        new Rules("Chance", (Integer[] ary) ->{
            int sum = 0;
            for(Integer i : ary){
                sum += i;
            }
            return sum;
        }),
        new Rules("Small straight", (Integer[] ary) ->{
            Integer[] copied = ary.clone();
            java.util.Arrays.sort(copied);
            int count = 0;

            for (int i = 0; i < copied.length; i++) {
                if(copied[i] == i + 1){
                    count++;
                }
            }

            if(count >= 4){
                return 30;
            }
            return -1;
        }),

        new Rules("Large Straight", (Integer[] ary) ->{
            Integer[] copied = ary.clone();
            java.util.Arrays.sort(copied);
            int count = 0;

            for (int i = 0; i < copied.length; i++) {
                if(copied[i] == i + 1){
                    count++;
                }
            }
            
            if(count >= 5){
                return 40;
            }
            return -1;
        }),

        new Rules("Yahtzee", (Integer[] ary) ->{
            Integer first = ary[0];
            for(Integer num : ary){
                if(!num.equals(first)){
                    return -1;
                }
            }
            return 50;
        }),

        new Rules("Full House", (Integer[] ary) ->{
            boolean hasThree = false;
            boolean hasTwo = false;
            for(int i = 1; i <= 6; i++){
                int count = 0;
                for(Integer num : ary){
                    if(num == i){
                        count++;
                    }
                }
                if(count == 3){
                    hasThree = true;
                }
                if(count == 2){
                    hasTwo = true;
                }
            }
            if(hasThree && hasTwo){
                return 25;
            }
            return -1;
        })
        };


        Game game = new Game(5, 6, rules);
        String title = "Yahtzee! By Dyno 1.0.67";

        JOptionPane.showMessageDialog(null, "Yahtzee made with C# not Java because Java sucks.\nAnd also I could've made this 3D with OpenGL in C++", title, JOptionPane.INFORMATION_MESSAGE);
        String name = JOptionPane.showInputDialog(null, "Enter a name and it better not be DYNO because I am gonna crash out.", title, JOptionPane.QUESTION_MESSAGE);
        while(name.toLowerCase().equals("dyno")){
            name = JOptionPane.showInputDialog(null, "I said not DYNO! Enter a different name. NOW before I CRASHOUT. I WILL PULL UP UR IP ADDRESS WITH UR INTERNET!", "YOU HAVE SUCCESSFULLY RAGE BAITTED ME", JOptionPane.ERROR_MESSAGE);
        }

        Player player = new Player(name, rules);
        CPU cpu = new CPU("Ronan-Dyno", rules);

        // 2 rounds for testing replace with rules.length
        while(game.getRound() < 2){
            player.Turn(rules, game);
            cpu.Turn(rules, game);
            

            game.newRound();

        }

        if(player.getScore() > cpu.getScore())
            JOptionPane.showMessageDialog(null, "Congratulations " + player.name + "! You win with a score of " + player.getScore() + " to " + cpu.getScore() + "!", title, JOptionPane.INFORMATION_MESSAGE);

        JOptionPane.showMessageDialog(null, "Good game", title, JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
