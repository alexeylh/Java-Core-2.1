/**
 * Демонстрирует работу с банковскими счетами.
 * Для простоты примера не проверяем каждую переданную сумму на отрицательные значения,
 *     в жизни это делается при контроле ввода суммы.
 * В качестве Id счёта установим 4 цифры.
 *      Для читаемости сберегательные начинаются на 1, кредитные на 2, расчётные на 3.
 *      Новый счёт заводится с нулевым балансом.
 */

import accounts.*;

class Main {
    public static void main(String[] args) {
        SavingsAccount savingAccount1 = new SavingsAccount("1001");
        SavingsAccount savingAccount2 = new SavingsAccount("1002");
        CreditAccount creditAccount = new CreditAccount("2010");
        CheckingAccount checkingAccount = new CheckingAccount("3020");

        System.out.println("Операции со СБЕРЕГАТЕЛЬНЫМИ счетами:");
        savingAccount1.addMoney(10500);
        savingAccount1.transfer(savingAccount2, 6000);
        savingAccount1.transfer(savingAccount2, 5000);

        System.out.println("Операции с КРЕДИТЬНЫМ счётом:");
        creditAccount.addMoney(100);
        creditAccount.pay(1000);
        creditAccount.transfer(savingAccount1, 2500);
        creditAccount.addMoney(3000);

        System.out.println("Операции с РАССЧЁТНЫМ счётом:");
        checkingAccount.pay(200);
        checkingAccount.addMoney(1000);
        checkingAccount.pay(500);
        checkingAccount.transfer(creditAccount, 500);
    }
}
