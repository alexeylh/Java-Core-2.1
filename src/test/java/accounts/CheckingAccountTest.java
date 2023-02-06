package accounts;

import org.junit.jupiter.api.*;

class CheckingAccountTest {
    private CheckingAccount account;
    private CheckingAccount accountToTransfer;

    @BeforeAll
    public static void initAll() {
        System.out.println("Running BeforeAll");
    }

    @BeforeEach
    public void init() {
        System.out.println("Running init");
        this.account = new CheckingAccount("0001");
        this.accountToTransfer = new CheckingAccount("0002"); // счёт, на который переводим
    }

    @AfterEach
    public void cleanup() {
        System.out.println("Running cleanup");
        this.account = null;
        this.accountToTransfer = null;
    }

    @Test
    void addMoney() {
        System.out.println("Running addMoney");
        // given:
        int amount = 1000;
        int expected = 1000;
        // when:
        boolean result = account.addMoney(amount);
        int factAmount = account.getAmount();
        // then:
        Assertions.assertTrue(result);
        Assertions.assertEquals(expected, factAmount);
    }

    @Test
    void transfer() {
        System.out.println("Running transfer");
        int initialOnSource = 600,       // исходная сумма на счёте
                amount = 500,            // сумма перевода
                expectedOnSource = 100,  // остаток на исходном счёте после перевода
                expectedOnDest = 500;    // баланс целевого счёта после перевода
        account.addMoney(initialOnSource); // для успешного перевода необходимо иметь на счету достаточно денег
        // when:
        boolean result = account.transfer(accountToTransfer, amount);
        int sourceAmount = account.getAmount();
        int destAmount = accountToTransfer.getAmount();
        // then:
        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedOnSource, sourceAmount);
        Assertions.assertEquals(expectedOnDest, destAmount);
    }

    @Test
    void transferFailed() {
        System.out.println("Running transferFailed");
        int amount = 200,            // сумма перевода
                expectedOnSource = 0,  // остаток на исходном счёте после перевода
                expectedOnDest = 0;    // баланс целевого счёта после перевода
        // when:
        boolean result = account.transfer(accountToTransfer, amount);
        int sourceAmount = account.getAmount();
        int destAmount = accountToTransfer.getAmount();
        // then:
        Assertions.assertFalse(result);
        Assertions.assertEquals(expectedOnSource, sourceAmount);
        Assertions.assertEquals(expectedOnDest, destAmount);
    }

    @Test
    void pay() {
        System.out.println("Running pay");
        // given:
        int initialOnSource = 30,  // исходная сумма на счёте
                amount = 25,       // сумма оплаты
                expected = 5;      // остаток после оплаты
        account.addMoney(initialOnSource); // для успешного платежа необходимо иметь на счету достаточно денег
        // when:
        boolean result = account.pay(amount);
        int factAmount = account.getAmount();
        // then:
        Assertions.assertTrue(result);
        Assertions.assertEquals(expected, factAmount);
    }

    @Test
    void payFailed() {
        System.out.println("Running payFailed");
        // given:
        int amount = 25,       // сумма оплаты
                expected = 0;  // остаток после оплаты
        // when:
        boolean result = account.pay(amount);
        int factAmount = account.getAmount();
        // then:
        Assertions.assertFalse(result);
        Assertions.assertEquals(expected, factAmount);
    }
}