package client;


import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class FXMLDocumentBase extends AnchorPane {


    
    
    protected final Button button;
    protected final TextField textField;
    protected final TextArea textArea;

    public FXMLDocumentBase() {

        button = new Button();
        textField = new TextField();
        textArea = new TextArea();

        setId("AnchorPane");
        setPrefHeight(319.0);
        setPrefWidth(510.0);

        button.setLayoutX(396.0);
        button.setLayoutY(248.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(48.0);
        button.setPrefWidth(94.0);
        button.setText("Button");

        textField.setLayoutX(14.0);
        textField.setLayoutY(240.0);
        textField.setPrefHeight(64.0);
        textField.setPrefWidth(377.0);
        textField.setPromptText("Type your message");

        textArea.setLayoutX(14.0);
        textArea.setPrefHeight(236.0);
        textArea.setPrefWidth(485.0);

        getChildren().add(button);
        getChildren().add(textField);
        getChildren().add(textArea);

    }
}
