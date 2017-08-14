package com.company;

import com.company.objects.Game;
import com.company.objects.Player;
import com.company.objects.Square;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Square destinationSquare = new Square(7,7);
    static Player p1 = new Player();
    static Player p2 = new Player();

    public static void main(String[] args) {
	// write your code here
        Game newGame = new Game();
        newGame.sayHellow();
        newGame.rules();

        getPlayerInput();
        System.out.println("Input is correct calculating shortest path for both players");
        boolean[][] visited = new boolean[8][8];
        List<Square> result1 =  p1.findShortestPath(destinationSquare,visited);

        boolean[][] visited1 = new boolean[8][8];
        List<Square> result2 =  p2.findShortestPath(destinationSquare,visited1);

        newGame.compareResult(p1,p2,result1,result2,destinationSquare);
    }

    private static void getPlayerInput() {
        boolean p1Inputval=false,p2Inputval=false;

        Scanner sc=new Scanner(System.in);
        String numbers1=null, numbers2=null;

        while(!p1Inputval){
            System.out.println("Player 1 : Enter a 2 comma seperated numbers eg. 2,2");
            numbers1 = sc.next();
            if (numbers1 != null && numbers1 != "" ){
                p1Inputval = p1.setSourceSquare(numbers1);
                if (p1Inputval){
                    if (p1.getSourceSquare().compareSquare(destinationSquare)){
                        System.out.println("you are placing your Knight at destination .please try again ");
                        p1.clearSourceSquare();
                        p1Inputval = false;
                    }else{
                        if (!p1Inputval){
                            System.out.println("Input is invalid.please try again");
                        }
                    }
                }else{
                    System.out.println("Input is invalid.please try again");
                }


            }else{
                System.out.println("Input is Can not be null or empty.please try again");
            }

        }

        while(!p2Inputval){
            System.out.println("Player 2 : Enter a 2 comma seperated numbers eg. 2,2");
            numbers2=sc.next();
            if (numbers2 != null && numbers2 != "" ) {
                if (!numbers1.equalsIgnoreCase(numbers2)) {
                    p2Inputval = p2.setSourceSquare(numbers2);
                    if (p2Inputval) {
                        if (p2.getSourceSquare().compareSquare(destinationSquare)){
                            System.out.println("you are placing your Knight at destination .please try again ");
                            p2.clearSourceSquare();
                            p2Inputval = false;
                        }else{
                            if (!p2Inputval) {
                                System.out.println("Input is invalid.please try again");
                            }
                        }
                    }else{
                        System.out.println("Input is invalid.please try again");
                    }
                } else {
                    System.out.println("This square is already taken by player one .please try again");
                }
            }else{
                System.out.println("Input is Can not be null or empty.please try again");
            }
        }
    }


}
