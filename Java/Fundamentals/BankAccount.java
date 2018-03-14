import java.util.Random;

public class BankAccount {
    private String accountNumber;
    private Double checkingBalance;
    private Double savingsBalance;
    private static int totalAccounts;
    private static int assets;
    public BankAccount() {
        this.accountNumber = genAccount();
        this.totalAccounts += 1;
    }
    private String genAccount() {
        String account = "";
        Random rand = new Random();
        for(int i = 0; i < 10; i++) {
            int n = rand.nextInt(10);
            account = account + Integer.toString(n);
        }
        return account;
    }
    public String getAccountNumber() {
        return this.accountNumber;
    }
    public Double getChecking() {
        return this.checkingBalance;
    }
    public Double getSavings() {
        return this.savingsBalance;
    }
    public void depositChecking(Double sum) {
        this.checkingBalance += sum;
    }
    public void depositSavings(Double sum) {
        this.savingsBalance += sum;
    }
    public void withdrawChecking(Double sum) {
        if (this.checkingBalance < sum) {
            this.checkingBalance -= sum;
        }
    }
    public void withdrawSavings(Double sum) {
        if (this.savingsBalance < sum) {
            this.savingsBalance -= sum;
        }
    }
    public Double viewChecking() {
        return this.checkingBalance;
    }
    public Double viewSavings() {
        return this.savingsBalance;
    }
    public static void main(String[] args) {
        BankAccount acct = new BankAccount();
        System.out.println(acct.getAccountNumber());
    }
}