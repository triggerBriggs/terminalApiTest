package com.indea.microtote;

import com.ineda.betplus.data.BetType;
import com.ineda.betplus.data.Card;
import com.ineda.betplus.data.Pool;
import com.ineda.betplus.wagering.*;
import com.ineda.microtote.wagering.MicrototeWagerProvider;
import com.ineda.util.Money;
import org.apache.log4j.BasicConfigurator;
import org.junit.FixMethodOrder;
import org.junit.Test;

import org.junit.runners.MethodSorters;

import java.text.ParseException;
import java.text.SimpleDateFormat;


import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class MicrototeWagerBrokerTest {
    private static long POOL_OID = 1175690;
    private static String BET_TYPE = "WIN";
    private static int LEGS = 1;


    @Test
    public void givenAccountBet_whenWagered_thenShouldUnimplementedException() {
        boolean thrown = false;
        MicrototeWagerProvider mctWP = new MicrototeWagerProvider();

        try {
            mctWP.accountBet(null, null, null);
        } catch (UnimplementedException e) {
            thrown = true;
            assertEquals( "Not implemented: Microtote account wagering functionality is not available.", e.getMessage());
        } catch (InvalidBetException e) {
            e.printStackTrace();
        } catch (InvalidAccountException e) {
            e.printStackTrace();
        } catch (InsufficientFundsException e) {
            e.printStackTrace();
        } catch (WagerProviderException e) {
            e.printStackTrace();
        } catch (ToteUnavailableException e) {
            e.printStackTrace();
        }
        assertTrue("Account Betting should not be available.", thrown);
    }

    @Test
    public void givenAccountBet_whenCancelled_thenShouldUnimplementedException() {
        boolean thrown = false;
        MicrototeWagerProvider mctWP = new MicrototeWagerProvider();

        try {
            mctWP.cancelAccountTicket(null, null, null);
        } catch (UnimplementedException e) {
            thrown = true;
            assertEquals( "Not implemented: Microtote account cancel ticket functionality is not available.", e.getMessage());
        } catch (InvalidBetException e) {
            e.printStackTrace();
        } catch (InvalidAccountException e) {
            e.printStackTrace();
        } catch (WagerProviderException e) {
            e.printStackTrace();
        } catch (ToteUnavailableException e) {
            e.printStackTrace();
        }
        assertTrue("Account Ticket Cancel should not be available.", thrown);
    }

    @Test
    public void givenAWinBet_whenWagered_thenResponseStatusIsGood() {
        BasicConfigurator.configure();

        Pool pool = null;
        Bet bet = null;
        try {
            pool = new Pool(
                    POOL_OID,
                    new Card(),
                    new BetType(BET_TYPE,1),
                    "MCT",
                    "MICROTOTE",
                    true,
                    false,
                    true,
                    new SimpleDateFormat("yyyy-MM-dd").parse("2018-04-14"),
                    new SimpleDateFormat("yyyy-MM-dd").parse("2018-04-14")
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Pool created OK.");

        bet = new Bet(pool);
        bet.setStake(new Money("GBP", "2.00"));
        bet.setCost(new Money("GBP", "4.00"));
        try {
            bet.setSelection(pool, "1,3");
        } catch (InvalidBetException e) {
            e.printStackTrace();
        }

        MicrototeWagerProvider mctWP = new MicrototeWagerProvider();

        try {
            mctWP.cashBet(bet, pool);
        } catch (UnimplementedException e) {
            e.printStackTrace();
        } catch (InvalidBetException e) {
            e.printStackTrace();
        } catch (WagerProviderException e) {
            e.printStackTrace();
        } catch (ToteUnavailableException e) {
            e.printStackTrace();
        }

        assertThat(bet.getTsn(), notNullValue());
    }


}
