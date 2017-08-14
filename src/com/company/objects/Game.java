package com.company.objects;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public void sayHellow(){
        System.out.println("Hello");
    }

    public void rules(){
        System.out.println("Number of players = 2 \nDestination square=(7,7)\nChessboard size = 8*8\nvalid input for placing knight=0-7,0-7");
    }

    public void compareResult(Player p1, Player p2, List<Square> result1, List<Square> result2, Square destinationSquare) {
        boolean endLoop=false;
        int count =1;
        int winningPlayer=0;

        List<Square> conflictedSquareP1=new ArrayList<>();
        List<Square> conflictedSquareP2=new ArrayList<>();
        List<Square> playerMoves=new ArrayList<>();
        while(!endLoop){
//            System.out.println("result1.get(count) = "+result1.get(count).getPrintableString());
//            System.out.println("result2.get(count-1) = "+result2.get(count-1).getPrintableString());
//            System.out.println("result2.get(count) = "+result2.get(count).getPrintableString());
            if (result1.get(count).compareSquare(result2.get(count-1))){
                boolean[][] visited = new boolean[8][8];
                conflictedSquareP1.add(new Square(result2.get(count-1).getxCoordinate(),result2.get(count-1).getyCoordinate()));

                if (!conflictedSquareP1.isEmpty()){
                    for (Square square: conflictedSquareP1){
                        visited[square.getxCoordinate()][square.getyCoordinate()]=true;
                    }
                }
                result1 = p1.findShortestPath(destinationSquare,visited);
//                endLoop=true;
//                compareResult(p1,p2,newResult1,result2,destinationSquare);
                playerMoves=new ArrayList<>();
                count = 1;
                continue;
            }else{
                if (result1.get(count).compareSquare(destinationSquare)){
                    playerMoves.add(destinationSquare);
                    winningPlayer=1;
                    endLoop=true;
                    break;

                }else{
                    playerMoves.add(result1.get(count));
                }


//                System.out.println("Player one "+count+" move = ("+result1.get(count).xCoordinate+","+result1.get(count).yCoordinate+")");
            }

            if (result2.get(count).compareSquare(result1.get(count))){
                boolean[][] visited2 = new boolean[8][8];
                conflictedSquareP2.add(new Square(result1.get(count).getxCoordinate(),result1.get(count).getyCoordinate()));

                if (!conflictedSquareP2.isEmpty()){
                    for (Square square: conflictedSquareP2){
                        visited2[square.getxCoordinate()][square.getyCoordinate()]=true;
                    }
                }

//                visited2[result1.get(count).getxCoordinate()][result1.get(count).getyCoordinate()]=true;
                result2 = p2.findShortestPath(destinationSquare,visited2);
//                endLoop=true;
//                compareResult(p1,p2,result1,newResult2,destinationSquare);
                count= 1;
                playerMoves=new ArrayList<>();
                continue;
            }else{
                if (result2.get(count).compareSquare(destinationSquare)){
                    playerMoves.add(destinationSquare);
                    winningPlayer=2;
                    endLoop=true;
                    break;

                }else{
                    playerMoves.add(result2.get(count));
                }

//                System.out.println("Player two "+count+" move = ("+result2.get(count).xCoordinate+","+result2.get(count).yCoordinate+")");
            }

            count=count+1;
        }

        if (!playerMoves.isEmpty()){
            for (int i=0;i<playerMoves.size();i++){
                if (i%2==0){
                    System.out.println("Player one move = "+playerMoves.get(i).getPrintableString());
                }else {
                    System.out.println("Player two move = "+playerMoves.get(i).getPrintableString());
                }
            }
            System.out.println("Player "+winningPlayer+" Wins!");
        }



        return;
    }
}
