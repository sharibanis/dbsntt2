package dbsntt2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Quote {
	LocalDateTime timeStamp;
	String symbol;
	int sharesTraded;
	double priceTraded;
	String changeDirection;
	double changeAmount;
	
	public Quote(String timestamp, String symbol, int sharesTraded, double priceTraded, 
			String changeDirection, double changeAmount) {
		try {
			this.timeStamp = LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_DATE_TIME);
			this.symbol = symbol;
			this.sharesTraded = sharesTraded;
			this.priceTraded = priceTraded;
			this.changeDirection = changeDirection;
			this.changeAmount = changeAmount;
		} catch (Exception ex) {
			System.out.println("Error creating quote: " + ex.toString());
		}
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getSharesTraded() {
		return sharesTraded;
	}

	public void setSharesTraded(int sharesTraded) {
		this.sharesTraded = sharesTraded;
	}

	public double getPriceTraded() {
		return priceTraded;
	}

	public void setPriceTraded(double priceTraded) {
		this.priceTraded = priceTraded;
	}

	public String getChangeDirection() {
		return changeDirection;
	}

	public void setChangeDirection(String changeDirection) {
		this.changeDirection = changeDirection;
	}

	public double getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(double changeAmount) {
		this.changeAmount = changeAmount;
	}
}
