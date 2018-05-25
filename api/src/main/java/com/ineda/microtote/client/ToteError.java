package com.ineda.microtote.client;

/**
 * List of error codes returned by the tote
 */
public enum ToteError
{
    SUCCESS(200,"SUCCESS"),

    BAD_REQUEST(400, "Bad request (usually caused when the tote has a problem deciphering request JSON, ensure it is correctly formatted)."),
    UNKOWN_MERCHANT(452, "The merchant ID was not recognised."),       //
    UNKOWN_CLIENT(453, "The client ID/name was not recognised."),         //
    UNKOWN_POOL_ID(454, "The pool ID was not recognised."),        //
    NO_PERMISSION(455, "The merchant/client does not have the correct permissions to perform the request."),         //
    INVALID_SELECTION(456, "Malformed/invalid selection."),     //
    INVALID_STAKE(457, "Malformed/invalid stake."),         //
    INVALID_COST(458, "Malformed/invalid cost."),          //
    UNSUPPORTED_CURRENCY(459, "The currency is not supported (or has not been configured) by the tote."),  //
    POOL_CLOSED(460, "The pool has closed."),           //
    BETTING_SUSPENDED(461, "Betting has been suspended on the pool."),     //
    POOL_SUSPENDED(462, "Transactions have been suspended on the pool."),        //
    STAKE_TO_LOW(463, "The stake is too low."),          //
    STAKE_TO_HIGH(464, "The stake is too high."),         //
    COST_TO_LOW(465, "The cost is too low."),           //
    COST_TO_HIGH(466, "The cost is too high."),          //
    FAVOURITE_NOT_ENABLED(467, "Favourite pick has not been enabled on the pool."),//
    TOTE_PICK_NOT_ENABLED(468, "Totepick has not been enabled on the pool."),//
    UNSUPPORTED_MUTATION(469, "Unsupported mutation."),  //
    SELECTED_RUNNER_IS_SCRATCHED(470, "A selected runner has been scratched."),//
    ALREADY_CANCELLED(471, "The ticket has already been cancelled."),     //
    CANT_FIND_TICKET_MR(472, "A ticket was not found using the provided merchant reference."),   //
    CANT_FIND_TICKET_TSN(473, "A ticket was not found using the provided TSN."),  //
    INCORRECT_PERMISSIONS(474, ""), //
    INVALID_CERT(475, "The certificate provided is invalid or has been revoked."),          //
    UNKNOWN_MEETING_ID(476, "The meeting ID was not recognised."),    //
    INVLAID_CLIENT(477, "Invalid client name."),        //
    INVALID_CLIENT_PERMISSION(478, "Invalid client permission."),
    INVALID_MERCHANT_PERMISSION(479, "Invalid merchant permission."),//
    INVLAID_CLIENT_ID(480, "Invalid client ID."),     //
    NO_POOL_PAYOUTS(481, "No payouts found for pool"),       //

    UNKNOWN_ERROR(500, "Unknown error"),

    GARBAGE_ERROR(9999, "Garbage error");

    private int code;
    private String message;

    ToteError(int code, String s)
    {
        this.code = code;
    }

    public static ToteError fromCode(int code) {
        for (ToteError t : ToteError.values()) {
            if (code==t.code)
                return t;
        }
        return GARBAGE_ERROR;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

