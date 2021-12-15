package com.mycompany.dictionary;

import dictionary.domain.Dictionary;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {


    public static void main(String[] args) {
        launch("--title=Dictionary");
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        Dictionary dictionary = new Dictionary();
        System.out.println(dictionary.randomValue());
        
        
        //adding title
        Parameters params = getParameters();
        String title = params.getNamed().get("title");
        
        //main layout
        BorderPane layout = new BorderPane();
        layout.setPrefSize(300, 300);
        
        //label menu
        GridPane wordMenu = createGridPane();
        GridPane practiceMenu = createGridPane();

        //layout for practice and enter words
        HBox buttonMenu = new HBox();
        buttonMenu.setPadding(new Insets(20,20,20,20));
        buttonMenu.setSpacing(10);
        
        //buttons
        Button buttonEnterWord = createButton("Enter new words");
        Button buttonPractice = createButton("Practice");
        Button buttonAdd = createButton("Add the word pair");
        Button buttonCheck = createButton("Check");
        
        //labels
        Label labelTranslation = new Label();  
        Label labelCheck = new Label();
        Label labelIncorrectValue = new Label();
        
        //textField
        TextField textWord = createTextField(); 
        TextField textTranslation = createTextField();
        TextField textPractice = createTextField();
        
        //adding stuff to GridPane
        wordMenu.add(createLabel("Word"), 0, 0);
        wordMenu.add(textWord, 0, 1);
        wordMenu.add(createLabel("Translation"), 0, 2);
        wordMenu.add(textTranslation, 0, 3);
        wordMenu.add(buttonAdd, 0, 4);
        
        practiceMenu.add(labelTranslation, 0, 0);
        practiceMenu.add(textPractice, 0, 1);
        practiceMenu.add(buttonCheck, 0, 2);
        practiceMenu.add(labelCheck, 0, 3);
        
        // adding buttons to HBox
        buttonMenu.getChildren().addAll(buttonEnterWord, buttonPractice);
        
        //button actions
        buttonEnterWord.setOnAction(event -> {
            layout.setCenter(wordMenu);
        });
        
        buttonPractice.setOnAction(event -> {
            layout.setCenter(practiceMenu);
            labelIncorrectValue.setText(dictionary.randomValue());
            labelTranslation.setText("Translate the word: " + "'" + labelIncorrectValue.getText() + "'");
            
        });
        
        buttonAdd.setOnAction(event -> {
            dictionary.add(textWord.getText(), textTranslation.getText());
            textWord.setText("");
            textTranslation.setText("");
        });
        
        buttonCheck.setOnAction(event -> {
            if (dictionary.checkIfIn(labelIncorrectValue.getText(), textPractice.getText())) {
                labelIncorrectValue.setText(dictionary.randomValue());
                labelTranslation.setText("Translate the word: " + "'" + labelIncorrectValue.getText() + "'");
                labelCheck.setText("Correct");
            } else {

               labelCheck.setText("Incorrect! The translation of the word " + labelIncorrectValue.getText() + " is '" + dictionary.getValue(labelIncorrectValue.getText()) + "'");
                
            }
        });
        
        //adding layouts to main layout
        layout.setTop(buttonMenu);
        layout.setCenter(wordMenu);
        
        //setting scene
        Scene scene = new Scene(layout);
        
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        
        
    }
    
    public Button createButton(String name) {
        return new Button(name);
    }
    
    public Label createLabel(String name) {
        return new Label(name);
    }
    
    public TextField createTextField() {
        return new TextField();
    }
    
    public GridPane createGridPane() {
        GridPane test = new GridPane();
        test.setAlignment(Pos.CENTER);
        test.setVgap(10);
        test.setHgap(10);
        return test;
    }
}