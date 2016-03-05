public class SafeBankAccount
	{
	    //everything is in centimes
	public static final int FINE = 200;
	public static final int CAT1 = 1000000;
	public static final int CAT2 = 200000;
	public static final float RATECAT1 = 0.03f;
	public static final float RATECAT2 = 0.02f;
	public static final float RATECAT3 = 0.01f;

	    // Balance
	    private int balance;

	    /**
	     * Constructor for objects of class SafeBankAccount - no balance
	     */
	    public SafeBankAccount()
	    {
	        
	        balance = 0;
	    }

	    /**
	     * Constructor for objects of class SafeBankAccount - with balance
	     */
	    public SafeBankAccount(int aBalance)
	    {
	        
	        balance = aBalance;
	    }
	    
	    public int getBalance() {
	        return balance;
	    }
	    
	   public void withdraw(int amount) {
	       if (amount <= balance)
	            balance = balance - amount;
	       else 
	            balance=balance - FINE;

	        }
	        
	    public void deposit (int amount) {
	        balance = balance + amount;
	    }
	    
	    public int computeInterest() {
	    float interestRate ;
	    if (balance > CAT1)
	       interestRate = RATECAT1;
	    else if (balance > CAT2)
	             interestRate = RATECAT2;
	         else interestRate = RATECAT3;
	   return (int)Math.round(balance * interestRate);      
	}

	    public void applyInterest() {
	        balance = balance+this.computeInterest();
	    }
	 
	}

