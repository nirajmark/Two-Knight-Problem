package com.company.objects;

import java.util.ArrayList;
import java.util.List;

public class Square {
    int xCoordinate, yCoordinate,dist;
    List<Square> lastSquares= new ArrayList<>();

    int[] xDist = {2,2,-2,-2,1,1,-1,-1};
    int[] yDist = {1,-1,1,-1,2,-2,2,-2};

    public Square(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public Square(int xCoordinate, int yCoordinate, int dist) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.dist = dist;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public List<Square> getAdjucencyListForSquare(){
        List<Square> adjList = new ArrayList<Square>();
        for (int i =0 ;i<xDist.length; i++){
            if (xCoordinate+xDist[i]>0 && yCoordinate+yDist[i]>0 && xCoordinate+xDist[i]<8 && yCoordinate+yDist[i]<8){
                adjList.add(new Square(xCoordinate+xDist[i],yCoordinate+yDist[i]));
            }
        }
        return adjList;
    }

    public boolean compareSquare(Square square2){
        if (xCoordinate == square2.xCoordinate && yCoordinate == square2.yCoordinate){
            return true;
        }
        return false;
    }

    public String getPrintableString(){
        return "("+xCoordinate+","+yCoordinate+")";
    }
}
