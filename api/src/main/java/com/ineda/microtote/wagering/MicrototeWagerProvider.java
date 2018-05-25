package com.ineda.microtote.wagering;

import com.ineda.betplus.data.Pool;
import com.ineda.betplus.data.PoolMapping;
import com.ineda.betplus.data.Ticket;
import com.ineda.betplus.payment.ToteAccount;
import com.ineda.betplus.util.ErrorCodes;
import com.ineda.betplus.wagering.*;
import com.ineda.exception.*;
import com.ineda.microtote.client.MicrototeClient;
import com.ineda.microtote.client.ToteError;
import com.ineda.microtote.models.*;
import com.ineda.opentote.cost.BetCoster;
import com.ineda.opentote.cost.Permutation;
import com.ineda.persistence.SessionFactoryBuilder;
import com.ineda.servicemgr.ManagedServiceImpl;
import com.ineda.servicemgr.ServiceCodes;
import com.ineda.servicemgr.ServiceException;
import com.ineda.util.Money;
import org.hibernate.*;
import org.apache.log4j.Logger;


import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Currency;
import java.util.List;


public class MicrototeWagerProvider extends ManagedServiceImpl implements
        WagerProvider, ErrorCodes, ServiceCodes {

    private static final String API_VERSION = "v2";

    private MicrototeClient client;
    private SessionFactory sf;

    private static final Logger log = Logger
            .getLogger(MicrototeWagerProvider.class);

    /** Variable set from the attributes in the xml config, this is loaded by the service manager before we
     * call the init() on the service manager.
     */
    private String provider;
    private Currency micrototeCurrency;
    private String endPoint;
    private String keyStorePassword;
    private String keyStore;
    private String trustStore;
    private String jerseyLogPath; // = "/app/ot.api/log/jerseyDebug.log";

    @Override
    public void init() throws ServiceException {
        client = new MicrototeClient(endPoint, API_VERSION, keyStore, trustStore, keyStorePassword, jerseyLogPath);
        log.debug("Microtote client created and ready for wagering !!");
    }

    @Override
    public boolean allowsCashWagering() {
        return true;
    }

    @Override
    public boolean allowsAccountWagering() {
        return false;
    }

    @Override
    public boolean requiresAuth() {
        return false;
    }

    @Override
    public String authType() {
        return null;
    }

    @Override
    public Bet costBet(Bet bet, Pool pool) throws InvalidBetException, WagerProviderException {
        BetCoster bc = null;
        BetOptions bo = new BetOptions(bet.getBetOptions());

        //TODO add all of these exception and throw them.
        try {
            log.debug("Trying to cost Microtote wager. BET:" + bet );
            bc = new BetCoster();
            long cost = bc.costBet(bet.getBetType(), Permutation.NONE, bet.getStake().getPenniesValue(),
                    bet.getSelection(), bo.isToteChosenSelection() );
            bet.setCost(new Money(micrototeCurrency, cost));
        } catch (InvalidPoolCodeException e) {
            log.error("Invalid Microtote pool code. BET:" + bet + "\n" + e.getMessage() );
        } catch (InvalidSelectionException e) {
            log.error("Invalid Microtote selection. BET:" + bet + "\n" + e.getMessage()  );
        } catch (InvalidStakeException e) {
            log.error("Invalid Microtote stake. BET:" + bet + "\n" + e.getMessage() );
        } catch (InvalidPermutationException e) {
            log.error("Invalid Microtote permutation. BET:" + bet + "\n" + e.getMessage() );
        } catch (NotImplementedException e) {
            log.error("Microtote not implemented exception. BET:" + bet + "\n" + e.getMessage());
        } catch (IOException e) {
            log.error("Unhandled exception. BET:" + bet + "\n" + e.getMessage() + "\n" + e.getCause() + e.getStackTrace() );
        }

        return bet;
    }

    @Override
    public Bet cashBet(Bet bet, Pool pool) throws UnimplementedException, InvalidBetException, WagerProviderException, ToteUnavailableException {

        log.debug("About to Wager Microtote cash bet. BET:" + bet.toString() );
        Wager wager = new WagerBuilder()
                .withMerchantReference(bet.getMerchantReference())
                .withPoolId(getMicrototePool(pool.getPoolOid()))
                .withSelection(checkAndMutateSelection(bet))
                .withStake(bet.getStake().getCurrencyValue().toPlainString())
                .withCost(bet.getCost().getCurrencyValue().toPlainString())
                .withCurrency(micrototeCurrency.toString())
                .build();

        log.info("MT WAGER Request data : " + wager);
        Response response = client.postWager(wager);
        WagerResponse wr = response.readEntity(WagerResponse.class);
        log.info("MT WAGER Response data : " + wr);

        try {
            if (wr.isGood()) {
                bet.setTsn(wr.getTicket().getTsn());
                log.info("GOOD Microtote Wager !! tsn : " + bet.getTsn());
                BetOptions options = new BetOptions(bet.getBetOptions());
                log.debug("Returned Bet options = " + options.toString());
                if (options.isToteChosenSelection())
                    bet.setCost(new Money(micrototeCurrency, wr.getTicket().getToteCostPennies()));
            }
        } catch (ResponseErrorException e) {
            log.error(e.getMessage());
            throw new InvalidBetException(e.getMessage());
        }

        return bet;
    }

    private String checkAndMutateSelection(Bet bet) throws InvalidBetException {

        /** If its a totechosen bet option there should be 52's in the selections */
        BetOptions bo = new BetOptions(bet.getBetOptions());
        String selection = bet.getSelection();
        if (bo.isToteChosenSelection()&&!selection.contains("52"))
            throw new InvalidBetException("Microtote 'tote chosen' bet option requires at least 1 runner in selection to be '52'");

        /** Although Microtote can handle cascading favourites i.e. Fav, 2nd Fav, 3rd Fav etc, we dont have the logic at the
         * moment to cost this so we only want selections with 1 favourite chosen.
         */
        if (selection.replaceFirst("41", "").contains("41"))
            throw new InvalidBetException("More than one favourite found in the selection.");

        /** Change the 41 for favourite to an 'F'
         * 52 for a tote pick to a 'T'
         */
        return bet.getSelection()
                .replaceAll("41","F")
                .replaceAll("52", "T");
    }


    @Override
    public Bet accountBet(Bet bet, Pool pool, ToteAccount account) throws UnimplementedException, InvalidBetException, InvalidAccountException, InsufficientFundsException, WagerProviderException, ToteUnavailableException {
        throw new UnimplementedException("Microtote account wagering functionality is not available.");
    }

    @Override
    public void cashTicket(Bet bet, Pool pool) throws UnimplementedException, InvalidBetException, WagerProviderException, ToteUnavailableException {
        throw new UnimplementedException("Microtote Individual Ticket Cashing functionality is not available.");
    }

    @Override
    public CancelResult cancelTicket(Ticket ticket, Pool pool) throws UnimplementedException, InvalidCancelException, WagerProviderException, ToteUnavailableException {

        log.debug("About to Cancel Microtote Ticket. : " + ticket );

        CancelResult result = new CancelResult();
        result.setResultType(CancelResult.ResultType.ATTEMPTED);

        log.info("MT CANCEL Request");
        Response response = client.deleteTsn(ticket.getTsn());
        CancelResponse cr = response.readEntity(CancelResponse.class);
        log.info("MT CANCEL Response data : " + cr);

        try {
            if (cr.isGood()) {
                result.setRefundAmount(ticket.getToteCost().intValue());
                result.setResultType(CancelResult.ResultType.COMPLETED);
                log.info("GOOD Microtote Cancel !! tsn:" + ticket.getTsn());
                return result;
            } else {
                if (cr.getStatus() != ToteError.ALREADY_CANCELLED.getCode()) {
                    result.setResultType(CancelResult.ResultType.ALREADY_CANCELLED);
                    log.info("ALREADY Cancelled ticket. tsn:" + ticket.getTsn());
                } else {
                    result.setResultType(CancelResult.ResultType.REFUND_CANCEL_FAIL);
                    log.info("FAILED to Cancel ticket. tsn:" + ticket.getTsn());
                }

                throw new InvalidCancelException("Ticket could not be cancelled");
            }
        } catch (ResponseErrorException e) {
            throw new InvalidCancelException(cr.getDescription());
        }
    }

    @Override
    public CancelResult cancelAccountTicket(Ticket ticket, Pool pool, ToteAccount account) throws UnimplementedException, InvalidBetException, InvalidAccountException, WagerProviderException, ToteUnavailableException {
        throw new UnimplementedException("Microtote account cancel ticket functionality is not available.");
    }

    @Override
    public Currency getCurrency() {
        return micrototeCurrency;
    }

    @Override
    public void close() {
    }

    private Session getSession() throws IOException
    {
        synchronized(this)
        {
            if (sf == null)
                sf = new SessionFactoryBuilder().build();
        }

        Session session = sf.openSession();
        return session;
    }

    @SuppressWarnings("unchceked")
    private int getMicrototePool(long poolOid) throws WagerProviderException{

        Session s=null;
        try
        {
            s = getSession();
        } catch (IOException e)
        {
            log.error(e.getMessage());
            throw new WagerProviderException(DATABASE_ERROR,
                    "IOException encountered");
        }

        Pool pool = (Pool) s.get(Pool.class, poolOid);
        PoolMapping pm = null;
        List<PoolMapping> pms = s.createQuery(
                "from PoolMapping where pool=? and provider=?").setEntity(0,
                pool).setString(1, pool.getProvider()).list();

        String msg;
        switch (pms.size())
        {
            case 0:
                msg = "Could not find PoolMapping for pool: " + pool.getPoolOid()
                        + " and provider: " + pool.getProvider();
                log.error(msg);
                throw new WagerProviderException(INVALID_DETAILS, msg);
            case 1:
                pm = pms.get(0);
                break;
            default:
                msg = "Found too many (more than 1) PoolMappings for pool: "
                        + pool.getPoolOid() + " and provider: " + pool.getProvider();
                log.error(msg);
                throw new WagerProviderException(INVALID_DETAILS, msg);
        }
        return Integer.parseInt(pm.getModulePoolKey());
    }

    /* Methods used by service manager to set the values from config. */
    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public void setKeyStorePassword(String keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }

    public void setKeyStore(String keyStore) {
        this.keyStore = keyStore;
    }

    public void setTrustStore(String trustStore) {
        this.trustStore = trustStore;
    }

    public void setCurrency(String currencyCode) {
        this.micrototeCurrency = Currency.getInstance(currencyCode);
    }

    public void setJerseyLogPath(String jerseyLogPath) {
        this.jerseyLogPath = jerseyLogPath;
    }
}
