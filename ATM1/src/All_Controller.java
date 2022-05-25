
import java.awt.event.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template



 */



/**
 *
 * @author Fahim Uz Zaman
 */
public class All_Controller {
    
    @FXML
    private Button login_button;

    @FXML
    private PasswordField password_passwordfield;

    @FXML
    private Label register_label;

    @FXML
    private TextField username_textfield;

    @FXML
    void login(MouseEvent event) {

    }

    @FXML
    void register(MouseEvent event) {

    }
    
    @FXML
    private Label profile_label;
    
    @FXML
    private Button about_button;

    @FXML
    private Button account_balance_button;

    @FXML
    private Button change_pin_button;

    @FXML
    private Button deposit_button;

    @FXML
    private Button fastcash_button;

    @FXML
    private Button help_button;

    @FXML
    private Button logout_button;

    @FXML
    private Button withdraw_button;
    
    @FXML
    void changePin(MouseEvent event) {

        
    }

    @FXML
    void deposit(MouseEvent event) {

    }

    @FXML
    void fastcash(MouseEvent event) {

    }

    @FXML
    void logout(MouseEvent event) {

    }

    @FXML
    void showBalance(MouseEvent event) {

    }

    @FXML
    void withdraw(MouseEvent event) {

    }
    
    @FXML
    private Label balance_label;

    @FXML
    private TextField withdraw_textfield;

    @FXML
    private Label withdraw_warning_label;

    @FXML
    void backtoProfile(MouseEvent event) {

    }

    @FXML
    void withdrawConfirm(MouseEvent event) {

    }
    
    @FXML
    void gotoWithdraw(ActionEvent event) {

    }
    
    @FXML
    private TextField newpin_textfield;

    @FXML
    private TextField oldpin_textfield;

    @FXML
    private TextField retypepin_textfield;

    @FXML
    void pinChange(ActionEvent event) {

    }
    @FXML
    private TextField deposit_amount_textfield;

    @FXML
    private TextField deposit_confirm_textfield;

    @FXML
    void confirmDeposit(ActionEvent event) {

    }

}
