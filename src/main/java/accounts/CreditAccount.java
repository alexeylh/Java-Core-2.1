package accounts;

public class CreditAccount extends Account implements Payable {
    public CreditAccount(String accountId) {
        super(accountId);
    }

    @Override
    public String toString() {
        return this.getId() + " (кредитный, " + getAmountString() + ")";
    }

    @Override
    public String getErrorMessage() {
        return String.format("Баланс счета %s не может быть положительным.", this.toString());
    }

    @Override
    public boolean addMoney(int amount) {
        if (this.amount + amount <= 0) {
            this.amount += amount;
            System.out.println(String.format("Счёт %s пополнен на сумму %s", this.toString(), amount));
            return true;
        } else {
            System.out.println(String.format("Пополнение счёта %s на сумму %s: операция отклонена.",
                    this.toString(), amount));
            System.out.println("    " + this.getErrorMessage());
            return false;
        }
    }

    @Override
    public boolean transfer(Account account, int amount) {
        if (account.addMoney(amount)) {
            this.amount -= amount;
            System.out.println(String.format("    Сумма %s списана со счёта %s", amount, this.toString()));
            return true;
        }
        return false;
    }

    @Override
    public boolean pay(int amount) {
        this.amount -= amount;
        System.out.println(String.format("Со счёта %s проведён платёж на сумму %s", this.toString(), amount));
        return true;
    }
}
