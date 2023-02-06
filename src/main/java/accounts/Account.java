package accounts;

public abstract class Account {
    private String accountId;
    protected int amount;

    public Account(String accountId) {
        this.accountId = accountId;
    }

    public String getId() {
        return this.accountId;
    }

    public int getAmount() { return amount; }

    public String getAmountString() {
        return String.format("текущий баланс: %s", this.amount);
    }

    @Override
    public String toString() {
        return getId() + " " + getAmountString();
    }

    public abstract String getErrorMessage();

    public abstract boolean addMoney(int amount);

    public abstract boolean transfer(Account account, int amount);
}
