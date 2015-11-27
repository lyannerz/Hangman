// Lyanna Ho's simple text-based Hangman program

import java.util.*;

public class BetterHangman
{
    public static void main(String[] args){
        Random random = new Random();
        String[] words = {"lyanna", "program", "word", "computer", "height", "width", "ammunition", "github", "living"};
        Scanner in = new Scanner(System.in);

        char[] randomWord = words[random.nextInt(words.length)].toCharArray();
        int limit = 0;
        char[] otherArray = new char[randomWord.length];
        ArrayList<Character> savedLetters = new ArrayList<Character>();

        for(int i=0; i<otherArray.length; i++){
            otherArray[i] = '_';
        }

        while(true){
            for(int i=0;i<otherArray.length;i++)
                System.out.print(otherArray[i] + " ");

            char entered;

            do{
                System.out.println();
                System.out.println();
                gallow(limit);
                
                System.out.println("\nLetters guessed: \n" + savedLetters);                
                System.out.println();
                System.out.println("Enter a letter to guess: ");
                entered = in.nextLine().charAt(0); //only takes first letter of user's input
                entered = Character.toLowerCase(entered);
                
                boolean sameLetter=true;
                
                while(sameLetter==false){
                    for(int i=0; i<savedLetters.size(); i++){
                        if(entered==savedLetters.get(i)){
                            sameLetter = false;
                            System.out.println("Repeated letter. Try again.");
                            //System.out.println(savedLetters.get(i));
                            entered = in.nextLine().charAt(0); //only takes first letter of user's input
                            entered = Character.toLowerCase(entered);
                        }
                        else{
                            sameLetter = false;
                        }
                    } 
                    savedLetters.add(entered);
                    sameLetter=true;
                }

            }while(!Character.isLetter(entered));

            boolean matched = false;

            for(int i=0; i<randomWord.length; i++){
                if(entered==randomWord[i]){
                    otherArray[i] = entered;
                    matched=true;
                }
            }

            if(matched==false){
                limit++;
                int guessesLeft = 6-limit;
                System.out.println("Letter is not in the word. \nYou have " + guessesLeft + " guesses left.");
            }

            if(limit==6){
                gallow(limit);
                System.out.println("Game over. \nThe word was: ");
                for(int i=0;i<randomWord.length;i++)
                    System.out.print(randomWord[i]);

                return;
            }

            if(matched==true){
                boolean isComplete=true;
                for(int i=0;i<randomWord.length;i++){
                    if(randomWord[i]!=otherArray[i])
                        isComplete=false;
                }
                if(isComplete){
                    System.out.println("You win!");
                    for(int i=0;i<randomWord.length;i++)
                        System.out.print(randomWord[i]);
                    return;
                }
            }
        }        

    }

    public static void gallow(int mistakes){
        char[][] gallowArray = {
            {'-','-','-','-','-','-'},
            {'|',' ',' ',' ','|',' '},
            {'|',' ',' ',' ',' ',' '},
            {'|',' ',' ',' ',' ',' '},
            {'|',' ',' ',' ',' ',' '},
            {'|',' ',' ',' ',' ',' '}
        };

        String head = "0";
        char chHead = head.charAt(0);

        String body = "|";
        char chBody = body.charAt(0);

        String left = "/";
        char chLeft = left.charAt(0);

        String right = "\\";
        char chRight = right.charAt(0);

        switch(mistakes){
            case 0: break;
            case 1: gallowArray[2][4] = chHead;
            break;
            case 2: gallowArray[2][4] = chHead;
            gallowArray[3][4] = chBody;
            break;
            case 3: gallowArray[2][4] = chHead;
            gallowArray[3][4] = chBody;
            gallowArray[3][3] = chLeft;
            break;
            case 4: gallowArray[2][4] = chHead;
            gallowArray[3][4] = chBody;
            gallowArray[3][3] = chLeft;
            gallowArray[3][5] = chRight;
            break;
            case 5: gallowArray[2][4] = chHead;
            gallowArray[3][4] = chBody;
            gallowArray[3][3] = chLeft;
            gallowArray[3][5] = chRight;
            gallowArray[4][3] = chLeft;
            break;
            case 6:
            gallowArray[2][4] = chHead;
            gallowArray[3][4] = chBody;
            gallowArray[3][3] = chLeft;
            gallowArray[3][5] = chRight;
            gallowArray[4][3] = chLeft;
            gallowArray[4][5] = chRight;
            break;

        }

        for(int i=0; i<6; i++){
            for(int j=0; j<6; j++){
                System.out.print(gallowArray[i][j]);
            }
            System.out.println();
        }

    }
}
