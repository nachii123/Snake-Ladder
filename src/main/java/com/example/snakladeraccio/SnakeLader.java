package com.example.snakladeraccio;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLader extends Application {

    public static final int tileSize =40, width=10, height=10;
    public static  final  int buttonLine = height*tileSize+50, infoLine = buttonLine - 20;

    private static Dice dice = new Dice();
    boolean gameStarted = false, playerOneTurn = false, playerTwoTurn = false;


    private Player playerOne,playerTwo;
  private Pane createContent(){
      Pane root = new Pane();
      root.setPrefSize(width*tileSize, height*tileSize +100);

      for(int i=0;i<height;i++){
          for(int j=0;j<width;j++){
              Tile tile = new Tile(tileSize);
              tile.setTranslateX(j*tileSize);
              tile.setTranslateY(i*tileSize);
              root.getChildren().add(tile);
          }
      }
      Image img = new Image("C:\\Users\\NACHIKET\\IdeaProjects\\SnakLaderAccio\\src\\main\\Snake4.jpeg");
      ImageView board = new ImageView();
      board.setImage(img);
      board.setFitHeight(height*tileSize);
      board.setFitWidth(width*tileSize);

      //Buttons
      Button playerOneButton = new Button("Player One");
      Button playerTwoButton = new Button("Player Two");
      Button startButton = new Button("Start");

      playerOneButton.setTranslateY(buttonLine);
      playerOneButton.setTranslateX(20);
      playerOneButton.setDisable(true);
      playerTwoButton.setTranslateY(buttonLine);
      playerTwoButton.setTranslateX(300);
      playerTwoButton.setDisable(true);
      startButton.setTranslateY(buttonLine);
      startButton.setTranslateX(160);


      //label display
      Label playerOneLabel = new Label("");
      Label playerTwoLabel = new Label("");
      Label dicelabel = new Label("Your dice");

      playerOneLabel.setTranslateY(infoLine);
      playerOneLabel.setTranslateX(20);
      playerTwoLabel.setTranslateY(infoLine);
      playerTwoLabel.setTranslateX(300-10);
      dicelabel.setTranslateY(infoLine);
      dicelabel.setTranslateX(160);

      playerOne= new Player(tileSize-10, Color.BLACK," Amit");
      playerTwo = new Player(tileSize-10, Color.WHITE,"Nachiket");

      //Player Action
      playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
              if (gameStarted) {
                  if (playerOneTurn) {
                      int diceValue = dice.getRolledDiceValue();
                      dicelabel.setText("Dice value :"+ diceValue);
                       playerOne.movePlayer(diceValue);

                       //Wining Condition
                      if(playerOne.isWinner()){
                          dicelabel.setText("Winner is "+ playerOne.getName());
                          playerOneTurn= false;
                          playerOneButton.setDisable(true);
                          playerOneLabel.setText("");
                          playerTwoButton.setDisable(true);
                          playerTwoLabel.setText("");

                          startButton.setDisable(false);
                          startButton.setText("Restart");
                          gameStarted = false;
                      }else {


                          playerOneTurn = false;
                          playerOneButton.setDisable(true);
                          playerOneLabel.setText("");

                          playerTwoTurn = true;
                          playerTwoButton.setDisable(false);
                          playerTwoLabel.setText("Your Turn " + playerTwo.getName());
                      }
                  }
              }  }
      });
     playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent actionEvent) {

            if(gameStarted){
                if(playerTwoTurn){
                    int diceValue = dice.getRolledDiceValue();
                    dicelabel.setText("Dice value :"+diceValue);
                    playerTwo.movePlayer(diceValue);
                    // Winning condition
                    if(playerTwo.isWinner()){
                        dicelabel.setText("Winner is "+playerTwo.getName());
                        playerOneTurn = false;
                        playerOneButton.setDisable(true);
                        playerOneLabel.setText("");
                        playerTwoTurn = true;
                        playerTwoButton.setDisable(true);
                        playerTwoLabel.setText("");

                        startButton.setDisable(false);
                        startButton.setText("Restart");
                    }else{
                    playerOneTurn = true;
                    playerOneButton.setDisable(false);
                    playerOneLabel.setText("Yur Turn "+ playerOne.getName());
                    playerTwoTurn = false;
                    playerTwoButton.setDisable(true);
                    playerTwoLabel.setText("");
                }
            }
            }
         }
     });
     startButton.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent actionEvent) {
             gameStarted = true;
             dicelabel.setText("Game Started");
             startButton.setDisable(true);
             playerOneTurn = true;
             playerOneLabel.setText("Your Turn "+playerOne.getName());
             playerOneButton.setDisable(false);
             playerOne.startPostition();
             playerTwoTurn = false;
             playerTwoLabel.setText("");
             playerTwoButton.setDisable(true);
             playerTwo.startPostition();


         }
     });
      root.getChildren().addAll(board,playerOneButton,playerTwoButton,startButton,playerOneLabel,playerTwoLabel,dicelabel,playerOne.getCoin(),playerTwo.getCoin());


      return root;
  }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SnakeLader.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}