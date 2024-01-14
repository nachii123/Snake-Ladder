package com.example.snakladeraccio;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class Player {

    Circle coin;

    int currentPosition;

    String name;

    static Board gameBoard = new Board();
    public Player(int tileSize, Color coinCOlor,String playerName){
        coin = new Circle(tileSize/2);
        coin.setFill(coinCOlor);
        currentPosition = 0;
        movePlayer(1);
        name = playerName;
    }

    public void movePlayer(int diceValue){
        if(currentPosition+diceValue<=100){
            currentPosition+=diceValue;


            TranslateTransition secondMove = null, firstMove = translateAnimation(diceValue);
//            translateAnimation(diceValue);



            int newPosition = gameBoard.getNewPosition(currentPosition);
            if(newPosition!=currentPosition && newPosition!=-1){
                currentPosition = newPosition;
                secondMove  = translateAnimation(currentPosition);
            }
            if(secondMove == null){
                firstMove.play();
            }else{
                SequentialTransition sequentialTransition = new SequentialTransition(firstMove,new PauseTransition(Duration.millis(100)),secondMove);
                sequentialTransition.play();
            }
        }
//        int x =  gameBoard.getXCordinate(currentPosition);
//        int y = gameBoard.getYCordinate(currentPosition);
//
//        coin.setTranslateX(x);
//        coin.setTranslateY(y);

    }
    public TranslateTransition translateAnimation(int dicevalues){
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000),coin);
        animate.setToX(gameBoard.getXCordinate(currentPosition));
        animate.setToY(gameBoard.getYCordinate(currentPosition));
        animate.setAutoReverse(false);
        return animate;
    }
    public  void startPostition(){
        currentPosition =0;
        movePlayer(1);
    }
    public boolean isWinner(){
        if(currentPosition == 100){
            return true;
        }
        return false;
    }


    public Circle getCoin() {
        return coin;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public String getName() {
        return name;
    }
}
