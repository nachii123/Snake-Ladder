package com.example.snakladeraccio;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {


    private ArrayList<Pair<Integer,Integer>> positionCoordinates;
    ArrayList<Integer> snakeLaderPosition;

    public Board(){

        positionCoordinates = new ArrayList<>();
        populatePositionCoordinates();
        populateSnakeLader();
    }

    private void populatePositionCoordinates(){
        positionCoordinates.add(new Pair<>(0,0)); // dummy
        for(int i=0;i< SnakeLader.height;i++){
            for (int j = 0; j < SnakeLader.width; j++) {
                // x coordinates
                // y coordinates
                int xcord =0;
                if(i%2==0){
                    xcord = j*SnakeLader.tileSize + SnakeLader.tileSize/2;

                }else{
                    xcord = SnakeLader.tileSize*SnakeLader.height - (j*SnakeLader.tileSize) - SnakeLader.tileSize/2;
                }

                int ycord = SnakeLader.tileSize*SnakeLader.height - (i*SnakeLader.tileSize) - SnakeLader.tileSize/2;
                positionCoordinates.add(new Pair<>(xcord,ycord));
            }
        }
    }

    private  void populateSnakeLader(){
        snakeLaderPosition = new ArrayList<>();
        for(int i=0;i<101;i++){
            snakeLaderPosition.add(i);
        }
        snakeLaderPosition.set(2,38);
        snakeLaderPosition.set(4,14);
        snakeLaderPosition.set(9,31);
        snakeLaderPosition.set(33,85);
        snakeLaderPosition.set(51,11);
        snakeLaderPosition.set(56,15);
        snakeLaderPosition.set(62,57);
        snakeLaderPosition.set(92,53);
        snakeLaderPosition.set(98,8);
        snakeLaderPosition.set(52,88);
        snakeLaderPosition.set(80,99);

    }
      public  int getNewPosition(int currentPosition){
        if(currentPosition>0 && currentPosition<=100){
            return snakeLaderPosition.get(currentPosition);
        }
        return -1;
      }
    int getXCordinate(int position){
        if(position>=1 && position<=100){
            return positionCoordinates.get(position).getKey();
        }
        return -1;
    }

    int getYCordinate(int position){
        if(position>=1 && position<=100){
            return positionCoordinates.get(position).getValue();
        }
        return -1;
    }

    public static void main(String[] args){
        Board board = new Board();
        for(int i=0;i<board.positionCoordinates.size();i++){
            System.out.println(i+"$ x : "+board.positionCoordinates.get(i).getKey()+" y : "+board.positionCoordinates.get(i).getValue());
        }
    }
}
