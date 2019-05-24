package app.helper;

import static org.junit.Assert.*;
import org.junit.Test;
import java.time.LocalDate;

public class LoanTest {

	@Test
	public void TotalPayments_Test() {
		Loan loan = new Loan(18000,5,4,0,200,LocalDate.now());
		int expectedTotalPayments = 32;
		int actualTotalPayments = loan.getTotalPayments();
	
		assertEquals(expectedTotalPayments,actualTotalPayments);
	}
	@Test
	public void TotalInterest_Test() {
		Loan loan = new Loan(18000,5,4,0,200,LocalDate.now());
		double expectedTotalInterest = 1237.08;
		double actualTotalInterest = loan.getTotalInterest();
		
		assertEquals(Double.compare(expectedTotalInterest,actualTotalInterest),0);
	}
	
	@Test
	public void PrintPayments_Test1() {
		Loan loan = new Loan(18000,5,1,0,200,LocalDate.now());
		
		System.out.println("\n\nPayment Test 1:\n");
		for (Payment payment : loan.getLoanPayments()) {
			
			System.out.printf("Payment #%d, Due Date: %s, Payment: %.2f, Extra Payment: %.2f, Interest: %.2f, Principle: %.2f, Balance: %.2f\n\n", 
					payment.getPaymentNbr(),payment.getDueDate(),payment.getPayment(),payment.getExtraPayment(),payment.getInterest(),payment.getPrinciple(),payment.getBalance());
		}
	}
	
	@Test
	public void PrintPayments_Test2() {
		Loan loan = new Loan(18000,5.45,1,0,0,LocalDate.now());
		
		System.out.println("\n\nPayment Test 2:\n");
		for (Payment payment : loan.getLoanPayments()) {
			System.out.printf("Payment #%d, Due Date: %s, Payment: %.2f, Extra Payment: %.2f, Interest: %.2f, Principle: %.2f, Balance: %.2f\n\n", 
					payment.getPaymentNbr(),payment.getDueDate(),payment.getPayment(),payment.getExtraPayment(),payment.getInterest(),payment.getPrinciple(),payment.getBalance());
		}
	}
	
	@Test
	public void monthlyPayment_Test() {
		Loan loan = new Loan(100000,7.55,20,0,400,LocalDate.now());
		double expectedMonthlyPayment = 808.65;
		double actualMonthlyPayment = loan.getMonthlyPayment();
		
		assertEquals(Double.compare(expectedMonthlyPayment,actualMonthlyPayment),0);
	}
	
	
	
}
