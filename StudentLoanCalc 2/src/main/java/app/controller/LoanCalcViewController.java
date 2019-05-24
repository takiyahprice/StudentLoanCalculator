package app.controller;

import app.StudentCalc;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;



import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import app.helper.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class LoanCalcViewController implements Initializable   {

	private StudentCalc SC = null;
	
	@FXML
	private TextField LoanAmount;
	
	@FXML
	private TextField InterestRate;
	
	@FXML
	private TextField NbrOfYears;
	
	@FXML
	private DatePicker PaymentStartDate;
	
	@FXML
	private TextField ExtraPayment;
	
	@FXML
	private Label lblTotalPayments;
	
	@FXML
	private Label lblTotalInterest;
	
	@FXML
	private TableView<Payment> table;
	
	@FXML
	private TableColumn<Payment,Integer> PaymentNbrCol;
	
	@FXML
	private TableColumn<Payment,Object> DueDateCol;
	
	@FXML
	private TableColumn<Payment,Double> PaymentCol;
	
	@FXML
	private TableColumn<Payment,Double> AdditionalPaymentCol;
	
	@FXML
	private TableColumn<Payment,Double> InterestCol;
	
	@FXML
	private TableColumn<Payment,Double> PrincipleCol;
	
	@FXML
	private TableColumn<Payment,Double> BalanceCol;
	
	private Loan loan = null;
	private ObservableList<Payment> payments;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void setMainApp(StudentCalc sc) {
		this.SC = sc;
	}
	
	/**
	 * btnCalcLoan - Fire this event when the button clicks
	 * 
	 * @version 1.0
	 * @param event
	 */
	@FXML
	private void btnCalcLoan(ActionEvent event) {
		createLoanInstance();
		lblTotalPayments.setText(Integer.toString(loan.getTotalPayments()));
		lblTotalInterest.setText("$"+Double.toString(loan.getTotalInterest()));
		createTable();
		
		/*System.out.println("Amount: " + LoanAmount.getText());
		double dLoanAmount = Double.parseDouble(LoanAmount.getText());
		System.out.println("Amount: " + dLoanAmount);	
		System.out.println("Additional Payment: "+ Double.parseDouble(ExtraPayment.getText()));
		System.out.println(InterestRate.getText());
		System.out.println(NbrOfYears.getText());
		
		lblTotalPayments.setText("123");
		lblTotalInterest.setText("231");
		
		LocalDate localDate = PaymentStartDate.getValue();
		System.out.println("Next month date: "+localDate.plusMonths(1));
		System.out.println(localDate);*/
		
		
	}
	
	private void createLoanInstance() {
		double dLoanAmount = Double.parseDouble(LoanAmount.getText());
		double dInterestRate = Double.parseDouble(InterestRate.getText());
		int iTerm = Integer.parseInt(NbrOfYears.getText());
		double dExtraPayment = Double.parseDouble(ExtraPayment.getText());
		loan = new Loan(dLoanAmount,dInterestRate,iTerm,0,dExtraPayment,PaymentStartDate.getValue());
	}
	
	
	
	private void createTable() {
		payments = FXCollections.observableArrayList(loan.getLoanPayments());
		table.setEditable(false);
		
		DueDateCol.setCellValueFactory(new PropertyValueFactory<Payment,Object>("dueDate"));
		PaymentNbrCol.setCellValueFactory(new PropertyValueFactory<Payment,Integer>("paymentNbr"));
		InterestCol.setCellValueFactory(new PropertyValueFactory<Payment,Double>("interest"));
		PrincipleCol.setCellValueFactory(new PropertyValueFactory<Payment,Double>("principle"));
		BalanceCol.setCellValueFactory(new PropertyValueFactory<Payment,Double>("balance"));
		PaymentCol.setCellValueFactory(new PropertyValueFactory<Payment,Double>("payment"));
		AdditionalPaymentCol.setCellValueFactory(new PropertyValueFactory<Payment,Double>("extraPayment"));
		
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
		
		PrincipleCol.setCellFactory(tc -> new TableCell<Payment, Double>() {

		    @Override
		    protected void updateItem(Double price, boolean empty) {
		        super.updateItem(price, empty);
		        if (empty) {
		            setText(null);
		        } else {
		            setText(currencyFormat.format(price));
		        }
		    }
		});
		
		InterestCol.setCellFactory(tc -> new TableCell<Payment, Double>() {

		    @Override
		    protected void updateItem(Double price, boolean empty) {
		        super.updateItem(price, empty);
		        if (empty) {
		            setText(null);
		        } else {
		            setText(currencyFormat.format(price));
		        }
		    }
		});
		
		BalanceCol.setCellFactory(tc -> new TableCell<Payment, Double>() {

		    @Override
		    protected void updateItem(Double price, boolean empty) {
		        super.updateItem(price, empty);
		        if (empty) {
		            setText(null);
		        } else {
		            setText(currencyFormat.format(price));
		        }
		    }
		});
		
		PaymentCol.setCellFactory(tc -> new TableCell<Payment, Double>() {

		    @Override
		    protected void updateItem(Double price, boolean empty) {
		        super.updateItem(price, empty);
		        if (empty) {
		            setText(null);
		        } else {
		            setText(currencyFormat.format(price));
		        }
		    }
		});
		
		AdditionalPaymentCol.setCellFactory(tc -> new TableCell<Payment, Double>() {

		    @Override
		    protected void updateItem(Double price, boolean empty) {
		        super.updateItem(price, empty);
		        if (empty) {
		            setText(null);
		        } else {
		            setText(currencyFormat.format(price));
		        }
		    }
		});
		
		
		table.setItems(payments);
	}
}
