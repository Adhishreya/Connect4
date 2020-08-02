package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
private Controller c;
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader q = new FXMLLoader(getClass().getResource("game.fxml"));
        GridPane w=q.load();
        Scene e=new Scene(w);
        c=q.getController();
       c.playg();
        primaryStage.setTitle("Connect4");
        primaryStage.setScene(e);
        primaryStage.show();
        primaryStage.setResizable(false);
        MenuBar ge=bar();
       ge.prefWidthProperty().bind(primaryStage.widthProperty());
        Pane pa= (Pane) w.getChildren().get(0);
        pa.getChildren().add(ge);
    }


    public  MenuBar bar()
    {   // Controller jlk=new Controller();
        Menu me=new Menu("File");
        Menu mew=new Menu("Help");
        MenuItem ne=new MenuItem("New Game");
        MenuItem reset=new MenuItem("Reset Game");
        ne.setOnAction(event -> {
            c.resetGame();
        });
        reset.setOnAction(event -> {c.resetGame();});
        SeparatorMenuItem sepa =new SeparatorMenuItem();
        MenuItem exi=new MenuItem("Exit");
        me.getItems().addAll(ne,reset,sepa,exi);
        MenuBar mbar=new MenuBar();
        mbar.getMenus().addAll(me,mew);
        MenuItem ab=new MenuItem("About Connect4");
        ab.setOnAction(event -> {
            Alert abt=new Alert(Alert.AlertType.INFORMATION);
            abt.setHeaderText("About Game");
            abt.setTitle("How to Play:");
            abt.setContentText("Connect Four is a two-player connection game in which the players first choose a color and then take turns dropping colored discs from the top into a seven-column, six-row vertically suspended grid. The pieces fall straight down, occupying the next available space within the column. The objective of the game is to be the first to form a horizontal, vertical, or diagonal line of four of one's own discs. Connect Four is a solved game. The first player can always win by playing the right moves.");
            abt.show();
        });

        MenuItem dev=new MenuItem("About Developer");
        dev.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                devel();
            }
        });
        mew.getItems().addAll(ab,dev);
        exi.setOnAction(event -> { exits();  });
        return mbar;
    }

    private void exits() {Platform.exit();
        System.exit(0);
    }



    private void SetNewGame() {



    }

    private void devel() {
        Alert cre=new Alert(Alert.AlertType.INFORMATION);
        cre.setHeaderText("###");
        cre.setTitle("About Developer");
        cre.setContentText(" Developed in 2018");
        cre.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
