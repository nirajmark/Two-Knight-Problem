package com.company.objects;

import java.util.*;

public class Player {

    Square sourceSquare;
    int sourceXCoordinate, sourceYCoordinate;

    public Square getSourceSquare() {
        return sourceSquare;
    }

    public void clearSourceSquare(){
        sourceSquare.yCoordinate=-1;
        sourceSquare.xCoordinate=-1;
    }

    public boolean setSourceSquare(String numbers) {
        if (isInputValid(numbers)){
            this.sourceSquare = new Square(sourceXCoordinate,sourceYCoordinate);
            return true;
        }else{
            return false;
        }
    }

    public boolean isInputValid(String numbers){
        String[] numbersArray = numbers.split(",");
        if (numbersArray.length==2){
            try{
                sourceXCoordinate = Integer.valueOf(numbersArray[0].trim());
                if (sourceXCoordinate<0 || sourceXCoordinate>7){
                    System.out.println("Both number should be in range 0-7");
                    return false;
                }
            }catch (Exception e){
                return false;
            }
            try{
                sourceYCoordinate = Integer.valueOf(numbersArray[1].trim());
                if (sourceYCoordinate<0 || sourceYCoordinate>7){
                    System.out.println("Both number should be in range 0-7");
                    return false;
                }
            }catch (Exception e){
                return false;
            }
        }else{
            return false;
        }
        return true;
    }


    public List<Square> findShortestPath(Square destinationSquare, boolean[][] visited){

        Queue<Square> squareQueue = new LinkedList<>();
        sourceSquare.dist=0;
        squareQueue.add(sourceSquare);

        while(!squareQueue.isEmpty()){
            Square currSquare=squareQueue.poll();
            if (currSquare.compareSquare(destinationSquare)){
                currSquare.lastSquares.add(new Square(7,7));
                return currSquare.lastSquares;
            }

            if (!visited[currSquare.getxCoordinate()][currSquare.getyCoordinate()]){
                visited[currSquare.getxCoordinate()][currSquare.getyCoordinate()]=true;
                int distance = currSquare.getDist();
                List<Square> adjSquare = currSquare.getAdjucencyListForSquare();
                for (Square square: adjSquare){
                    square.dist=distance+1;
                    List<Square> lastSquareList = currSquare.lastSquares;
                    if (!lastSquareList.contains(currSquare)){
                        lastSquareList.add(currSquare);
                    }
                    square.lastSquares.addAll(lastSquareList);
                    squareQueue.add(square);
                }
            }
        }
        return null;
    }
}
