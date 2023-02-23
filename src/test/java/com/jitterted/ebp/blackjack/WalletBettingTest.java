package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class WalletBettingTest {

    @Test
    void walletWithBalance12WhenBet8ThenBalanceIs4() {
        Wallet wallet = new Wallet();
        wallet.addMoney(12);

        wallet.bet(8);

        assertThat(wallet.balance())
                .isEqualTo(12 - 8);
    }

    @Test
    void walletWithBalance27Bet7And9ThenBalanceIs11() {
        Wallet wallet = new Wallet();
        wallet.addMoney(27);

        wallet.bet(7);
        wallet.bet(9);

        assertThat(wallet.balance())
                .isEqualTo(27 - 7 - 9);
    }

    @Test
    void betFullBalanceThenWalletIsEmpty() {
        Wallet wallet = new Wallet();
        wallet.addMoney(73);

        wallet.bet(73);

        assertThat(wallet.isEmpty())
                .isTrue();
    }
}
