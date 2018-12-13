package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField display;

    private String tempValue = "";
    private String tempNum = "";
    private Boolean start = true;
    private String operator = "";

    Model model = new Model();

    @FXML
    private void onNumberClick(ActionEvent event) {

        //So that the display is set to blank, as the new number will get appended with the previos one
        if(start) {
            display.setText("");
            start = false;
        }

        tempValue = ((Button)event.getSource()).getText();
        display.setText(display.getText() + tempValue);

    }


    @FXML
    private void onOperatorClick(ActionEvent event) {

        tempValue = ((Button)event.getSource()).getText();

        if(!tempValue.equals("=")) {

            //Clear everything
            if(tempValue.equals("C")){
                tempNum = "";
                operator = "";
                tempValue = "";
                display.setText("0");
                start = true;
                return;
            }

            //For Backspace Character
            if(tempValue.equals("←")) {

                if(display.getText().length() <= 1 && !display.getText().equals("0")) {
                    display.setText("0");
                    start = true;
                    return;
                }
                else if(display.getText().length() > 1){

                    display.setText(display.getText().substring(0, display.getText().length() - 1));
                    start = false;
                    return;
                }
                else
                    return;
            }

            //For Square-Root calculation
            if(tempValue.equals("√") && (!display.getText().contains(".") || !display.getText().contains("-"))) {
                display.setText(model.calculateRoot(display.getText()));
                start = true;
                return;
            }

            //For Decimal Input
            if(tempValue.equals(".")) {

                //Incase only '.' is pressed and no other number before
                if(!operator.isEmpty()) {
                    display.setText("0" + tempValue);
                    start = false;
                    return;
                }
                else if(!start && !display.getText().contains(".")){
                    display.setText(display.getText() + tempValue);
                    start = false;
                    return;
                }
                else if(start && !display.getText().contains(".")){
                    display.setText(display.getText() + tempValue);
                    start = false;
                    return;
                }
                return;
            }

            //For negative number
            if(tempValue.equals("-") && display.getText().equals("0")) {
                display.setText("-");
                start = false;
                return;
            }

            //Incase minus sign for negative number is input multiple times
            if(tempValue.equals("-") && display.getText().equals("-")) {
                start = false;
                return;
            }

            //For trailing calculations like (5 +3 * 11 - 2 / 6)
            if(!operator.isEmpty()) {

                display.setText(model.dataProcess(tempNum, display.getText(), operator));
                tempNum = display.getText();
                operator = tempValue;
                start = true;
                return;
            }

            //For input of other operators
            if(!display.getText().equals("-")) {
                operator = tempValue;
                tempNum = display.getText();
                start = true;
            }
        }
        else {
            //In case the user presses "=", without inputting any numbers to calculate
            if(operator.isEmpty())
                return;

            display.setText(model.dataProcess(tempNum, display.getText(), operator));
            operator = "";
            start = true;
        }
    }

}