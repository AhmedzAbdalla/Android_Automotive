package mynotepad_2;

import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;

public abstract class FXMLDocumentBase extends AnchorPane {

    protected final MenuBar menuBar;
    protected final Menu menu;
    protected final MenuItem menuItem;
    protected final MenuItem menuItem0;
    protected final MenuItem menuItem1;
    protected final MenuItem menuItem2;
    protected final MenuItem menuItem3;
    protected final MenuItem menuItem4;
    protected final Menu menu0;
    protected final MenuItem menuItem5;
    protected final MenuItem menuItem6;
    protected final MenuItem menuItem7;
    protected final MenuItem menuItem8;
    protected final MenuItem menuItem9;
    protected final Menu menu1;
    protected final MenuItem menuItem10;
    protected final TextArea MyTextArea;
    protected final DialogPane dialogPane;

    public FXMLDocumentBase() {

        menuBar = new MenuBar();
        menu = new Menu();
        menuItem = new MenuItem();
        menuItem0 = new MenuItem();
        menuItem1 = new MenuItem();
        menuItem2 = new MenuItem();
        menuItem3 = new MenuItem();
        menuItem4 = new MenuItem();
        menu0 = new Menu();
        menuItem5 = new MenuItem();
        menuItem6 = new MenuItem();
        menuItem7 = new MenuItem();
        menuItem8 = new MenuItem();
        menuItem9 = new MenuItem();
        menu1 = new Menu();
        menuItem10 = new MenuItem();
        MyTextArea = new TextArea();
        dialogPane = new DialogPane();

        setId("AnchorPane");
        setPrefHeight(411.0);
        setPrefWidth(607.0);

        menuBar.setLayoutX(7.0);
        menuBar.setLayoutY(6.0);
        menuBar.setPrefHeight(32.0);
        menuBar.setPrefWidth(600.0);

        menu.setMnemonicParsing(false);
        menu.setText("File");

        menuItem.setMnemonicParsing(false);
        menuItem.setOnAction(this::createNewFile);
        menuItem.setText("New");
        menuItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));

        menuItem0.setMnemonicParsing(false);
        menuItem0.setOnAction(this::openFile);
        menuItem0.setText("Open");
        menuItem0.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));

        menuItem1.setMnemonicParsing(false);
        menuItem1.setOnAction(this::openFileHigh);
        menuItem1.setText("Open (High)");
        menuItem1.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));

        menuItem2.setMnemonicParsing(false);
        menuItem2.setOnAction(this::saveFile);
        menuItem2.setText("Save");
        menuItem2.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));

        menuItem3.setMnemonicParsing(false);
        menuItem3.setOnAction(this::saveFileHigh);
        menuItem3.setText("Save (High)");
        menuItem3.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));

        menuItem4.setMnemonicParsing(false);
        menuItem4.setOnAction(this::exit);
        menuItem4.setText("Exit");
        menuItem4.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));

        menu0.setMnemonicParsing(false);
        menu0.setText("Edit");

        menuItem5.setMnemonicParsing(false);
        menuItem5.setOnAction(this::cutHandle);
        menuItem5.setText("Cut");

        menuItem6.setMnemonicParsing(false);
        menuItem6.setOnAction(this::copyHandle);
        menuItem6.setText("Copy");

        menuItem7.setMnemonicParsing(false);
        menuItem7.setOnAction(this::pasteHandle);
        menuItem7.setText("Paste");

        menuItem8.setMnemonicParsing(false);
        menuItem8.setOnAction(this::deleteHandle);
        menuItem8.setText("Delete");

        menuItem9.setMnemonicParsing(false);
        menuItem9.setOnAction(this::selectAllHandle);
        menuItem9.setText("Select All");

        menu1.setMnemonicParsing(false);
        menu1.setText("Help");

        menuItem10.setMnemonicParsing(false);
        menuItem10.setOnAction(this::showAbout);
        menuItem10.setText("About");

        MyTextArea.setLayoutX(7.0);
        MyTextArea.setLayoutY(38.0);
        MyTextArea.setPrefHeight(374.0);
        MyTextArea.setPrefWidth(600.0);

        dialogPane.setContentText("vdvdv");
        dialogPane.setHeaderText("dcvdvd");
        dialogPane.setLayoutX(111.0);
        dialogPane.setLayoutY(173.0);
        dialogPane.setVisible(false);
        dialogPane.getButtonTypes().addAll(ButtonType.CLOSE);

        menu.getItems().add(menuItem);
        menu.getItems().add(menuItem0);
        menu.getItems().add(menuItem1);
        menu.getItems().add(menuItem2);
        menu.getItems().add(menuItem3);
        menu.getItems().add(menuItem4);
        menuBar.getMenus().add(menu);
        menu0.getItems().add(menuItem5);
        menu0.getItems().add(menuItem6);
        menu0.getItems().add(menuItem7);
        menu0.getItems().add(menuItem8);
        menu0.getItems().add(menuItem9);
        menuBar.getMenus().add(menu0);
        menu1.getItems().add(menuItem10);
        menuBar.getMenus().add(menu1);
        getChildren().add(menuBar);
        getChildren().add(MyTextArea);
        getChildren().add(dialogPane);

    }

    protected abstract void createNewFile(javafx.event.ActionEvent actionEvent);

    protected abstract void openFile(javafx.event.ActionEvent actionEvent);

    protected abstract void openFileHigh(javafx.event.ActionEvent actionEvent);

    protected abstract void saveFile(javafx.event.ActionEvent actionEvent);

    protected abstract void saveFileHigh(javafx.event.ActionEvent actionEvent);

    protected abstract void exit(javafx.event.ActionEvent actionEvent);

    protected abstract void cutHandle(javafx.event.ActionEvent actionEvent);

    protected abstract void copyHandle(javafx.event.ActionEvent actionEvent);

    protected abstract void pasteHandle(javafx.event.ActionEvent actionEvent);

    protected abstract void deleteHandle(javafx.event.ActionEvent actionEvent);

    protected abstract void selectAllHandle(javafx.event.ActionEvent actionEvent);

    protected abstract void showAbout(javafx.event.ActionEvent actionEvent);

}
