package app.helper;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.time.LocalDate;
import javafx.beans.property.SimpleDoubleProperty;

public class Payment {
	
	private final SimpleIntegerProperty paymentNbr;
	
	private final SimpleDoubleProperty interest;
	
	private final SimpleDoubleProperty principle;
	
	private final SimpleDoubleProperty balance;
	
	private final SimpleDoubleProperty payment;

	private final SimpleDoubleProperty extraPayment;
	
	private final SimpleObjectProperty<LocalDate> dueDate;
	
	public Payment(int paymentNbr,double interest,double principle, double balance,double payment,double extraPayment,LocalDate dueDate) {
		
		this.paymentNbr = new SimpleIntegerProperty(paymentNbr);
		this.interest = new SimpleDoubleProperty(interest);
		this.principle = new SimpleDoubleProperty(principle);
		this.payment = new SimpleDoubleProperty(payment);
		this.extraPayment = new SimpleDoubleProperty(extraPayment);
		this.dueDate = new SimpleObjectProperty<LocalDate>(dueDate);
		
		if (balance <= 0) {
			this.balance = new SimpleDoubleProperty(balance);
		} else {
			this.balance = new SimpleDoubleProperty(balance);
		}
		
	}
	
	public double getInterest() {
		return interest.get();
	}
	
	public double getPrinciple() {
		return principle.get();
	}
	
	
	public double getBalance() {
		return balance.get();
	}
	
	public int getPaymentNbr() {
		return paymentNbr.get();
	}
	public double getPayment() {
		return payment.get();
	}
	public double getExtraPayment() {
		return extraPayment.get();
	}
	
	public String getDueDate() {
		return dueDate.get().toString();
	}
}
