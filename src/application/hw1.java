package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;



public class hw1 extends Application {
    private CheckBox eggSandwich, chickenSandwich, bagel, potatoSalad;
    private RadioButton blackTea, greenTea, coffee, orangeJuice;
    private Label title, eatTitle, drinkTitle, billTitle;
    private Button orderButton, cancelButton, confirmButton;
    private HBox foodAndDrinkHBox, billBox, buttonHBox;
    private ToggleGroup drinkToggleGroup;
    private TextArea billText;
    private double totalPrice = 0.00;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(30));

        /* Title Label */
        title = new Label("Joe's Deli");
        title.setFont(new Font("Arial", 20));
        border.setAlignment(title, Pos.BASELINE_CENTER);
        border.setTop(title);

        /* Food and Drink HBox */
        foodAndDrinkHBox = new HBox();
        foodAndDrinkHBox.setAlignment(Pos.TOP_LEFT);
        foodAndDrinkHBox.setSpacing(55);

        /* Eat Check box Pane */
        eatTitle = new Label("Food:");
        eatTitle.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        eggSandwich = new CheckBox("Egg Sandwich");
        chickenSandwich = new CheckBox("Chicken Sandwich");
        bagel = new CheckBox("Bagel");
        potatoSalad = new CheckBox("Potato Salad");

        VBox eatVBox = new VBox();
        VBox.setMargin(eggSandwich, new Insets(15, 0, 15, 0));
        VBox.setMargin(chickenSandwich, new Insets(15, 0, 15, 0));
        VBox.setMargin(bagel, new Insets(15, 0, 15, 0));
        VBox.setMargin(potatoSalad, new Insets(15, 0, 15, 0));
        eatVBox.getChildren().addAll(eatTitle, eggSandwich, chickenSandwich, bagel, potatoSalad);

        /* Drink radio group button Pane */
        drinkTitle = new Label("Drink:");
        drinkTitle.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        blackTea = new RadioButton("Black Tea");
        greenTea = new RadioButton("Green Tea");
        coffee = new RadioButton("Coffee");
        orangeJuice = new RadioButton("Orange Juice");
        drinkToggleGroup = new ToggleGroup();

        blackTea.setToggleGroup(drinkToggleGroup);
        greenTea.setToggleGroup(drinkToggleGroup);
        coffee.setToggleGroup(drinkToggleGroup);
        orangeJuice.setToggleGroup(drinkToggleGroup);

        VBox drinkVBox = new VBox();
        VBox.setMargin(blackTea, new Insets(15, 0, 15, 0));
        VBox.setMargin(greenTea, new Insets(15, 0, 15, 0));
        VBox.setMargin(coffee, new Insets(15, 0, 15, 0));
        VBox.setMargin(orangeJuice, new Insets(15, 0, 15, 0));
        drinkVBox.getChildren().addAll(drinkTitle, blackTea, greenTea, coffee, orangeJuice);

        foodAndDrinkHBox.getChildren().addAll(eatVBox, drinkVBox);

        /* Final Bill Box */
        billBox = new HBox();
        billText = new TextArea();
        billText.setEditable(false);
        billTitle = new Label("Bill");
        billTitle.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        billText.setPrefWidth(275);
        billText.setPrefHeight(250);

        VBox centeredBillBox = new VBox();
        centeredBillBox.setAlignment(Pos.TOP_CENTER);
        centeredBillBox.getChildren().addAll(billTitle, billText);

        billBox.getChildren().addAll(centeredBillBox);
        billBox.setAlignment(Pos.TOP_CENTER);
        HBox.setMargin(centeredBillBox, new Insets(4, 0, 20, 0));

        /* Button HBox */
        buttonHBox = new HBox();
        orderButton = new Button("Order");
        cancelButton = new Button("Cancel");
        confirmButton = new Button("Confirm");

        orderButton.setPrefSize(120, 55);
        cancelButton.setPrefSize(120, 55);
        confirmButton.setPrefSize(120, 55);

        // Initialize buttons before setting action event handlers
        orderButton.setOnAction(event -> displayOrder());
        cancelButton.setOnAction(event -> clearSelection());
        confirmButton.setOnAction(event -> confirmOrder());

        buttonHBox.getChildren().addAll(orderButton, cancelButton, confirmButton);
        HBox.setMargin(orderButton, new Insets(50, 20, 50, 50));
        HBox.setMargin(cancelButton, new Insets(50, 20, 50, 20));
        HBox.setMargin(confirmButton, new Insets(50, 50, 50, 20));

        // Set layout for the entire UI
        VBox leftVBox = new VBox();
        leftVBox.getChildren().addAll(foodAndDrinkHBox);
        leftVBox.setSpacing(20);

        HBox centerHBox = new HBox();
        centerHBox.getChildren().addAll(billBox);
        centerHBox.setSpacing(20);

        border.setLeft(leftVBox);
        border.setCenter(centerHBox);
        border.setBottom(buttonHBox);

        Scene scene = new Scene(border, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to display the selected items in the billTextArea
    private void displayOrder() {
        StringBuilder orderDetails = new StringBuilder("Order Details:\n");
        orderDetails.append("=============\n");
        // Check food items
        if (eggSandwich.isSelected()) {
            orderDetails.append("Egg Sandwich - $7.99\n");
            totalPrice += 7.99;
        }
        if (bagel.isSelected()) {
            orderDetails.append("Bagel - $2.50\n");
            totalPrice += 2.50;
        }
        if (potatoSalad.isSelected()) {
            orderDetails.append("Potato Salad - $4.49\n");
            totalPrice += 4.49;
        }
        if (chickenSandwich.isSelected()) {
            orderDetails.append("Chicken Sandwich - $9.99\n");
            totalPrice += 9.99;
        }

        // Check drink items
        RadioButton selectedDrink = (RadioButton) drinkToggleGroup.getSelectedToggle();
        if (selectedDrink != null) {
            orderDetails.append(selectedDrink.getText()).append("\n");
            switch (selectedDrink.getText()) {

                case "Coffee":
                    totalPrice += 1.99;
                    break;
                case "Green Tea":
                    totalPrice += 0.99;
                    break;
                case "Black Tea":
                    totalPrice += 1.25;
                    break;
                case "Orange Juice":
                    totalPrice += 2.25;
                    break;
            }

        }

        // Display order details in the billTextArea
        billText.setText(orderDetails.toString());
        billText.appendText("\n=============");
        billText.appendText("\nTotal Amount: $" + String.format("%.2f", totalPrice));

    }

    public void clearSelection() {
        eggSandwich.setSelected(false);
        bagel.setSelected(false);
        potatoSalad.setSelected(false);
        chickenSandwich.setSelected(false);
        drinkToggleGroup.selectToggle(null);
        totalPrice = 0.00;

        billText.clear();

    }

    public void confirmOrder() {
        double salesTaxAmnt = 0.07;
        double afterSales = totalPrice + (totalPrice * .07);
        billText.appendText("\nSales Tax: %" + String.format("%.2f", salesTaxAmnt));
        billText.appendText("\nTotal Amount after Sales Tax: $" + String.format("%.2f", afterSales));
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
