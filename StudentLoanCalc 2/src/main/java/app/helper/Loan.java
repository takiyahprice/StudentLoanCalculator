package app.helper;

import java.util.LinkedList;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Loan {
	private double dLoanAmount;
	private double dInterestRate;
	private int iTerm;
	private double dFutureValue;
	private double dExtraPayment;
	private LocalDate startDate;
	private double monthlyPayment;
	private double totalInterest = 0;
	
	private LinkedList<Payment> loanPayments = new LinkedList<Payment>();
	
	public Loan(double dLoanAmount, double dInterestRate, int iTerm, double dFutureValue, double dExtraPayment,LocalDate startDate) {
		this.dLoanAmount = dLoanAmount;
		this.dInterestRate = dInterestRate;
		this.iTerm = iTerm;
		this.dFutureValue = dFutureValue;
		this.dExtraPayment = dExtraPayment;
		this.startDate = startDate;
		
		monthlyPayment = pmt(dInterestRate,iTerm,dLoanAmount,dFutureValue,0);
		
		calculateLoanPayments();
	}
	
	private void calculateLoanPayments() {
		double balance = dLoanAmount;
		int paymentNbr = 0;
		LocalDate dueDate = startDate;
		while (balance>0) {
			paymentNbr++;
			double interestAmount = interest(dInterestRate,balance);
			double principleAmount = principle(monthlyPayment,dExtraPayment,interestAmount);
			balance -= principleAmount;
			
			//<=0
			if (balance <= 0) {
				principleAmount = loanPayments.getLast().getBalance();
				double remainingPayment = (monthlyPayment > interestAmount+principleAmount) ? interestAmount+principleAmount : monthlyPayment;
				double remainingAdditional = (interestAmount+principleAmount)-remainingPayment;
				balance = 0;
				Payment payment = new Payment(paymentNbr,round(interestAmount,2),round(principleAmount,2),balance,remainingPayment,remainingAdditional,dueDate.plusMonths(paymentNbr-1));
				loanPayments.add(payment);
				totalInterest += interestAmount;
				break;
			}
			
			Payment payment = new Payment(paymentNbr,round(interestAmount,2),round(principleAmount,2),round(balance,2),monthlyPayment,dExtraPayment,dueDate.plusMonths(paymentNbr-1));
			loanPayments.add(payment);
			totalInterest += interestAmount;
			
		}
		
	}
	
	public LinkedList<Payment> getLoanPayments() {
		return loanPayments;
	}
	
	public int getTotalPayments() {
		return loanPayments.size();
	}
	
	public double getTotalInterest() {
		return round(totalInterest,2);
	}
	
	public double getMonthlyPayment() {
		return round(monthlyPayment,2);
	}
	
	private double round(double value, int places) {
		BigDecimal bd = new BigDecimal(Double.toString(value));
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	private double pmt(double r, int years, double pv, double fv, int type) {
		r = (r/100)/12;
		years *= 12;
		pv *= -1;
		return (-r * (pv * Math.pow(1 + r, years) + fv) / ((1 + r*type) * (Math.pow(1 + r, years) - 1)));
	}
	private double principle(double monthlyPayment, double extraPayment, double interestPayment) {
		return (monthlyPayment + extraPayment) - interestPayment;
	}
	private double interest(double r, double pv) {
		r = (r/100)/12;
		return r * pv;
	}
	
	
}
