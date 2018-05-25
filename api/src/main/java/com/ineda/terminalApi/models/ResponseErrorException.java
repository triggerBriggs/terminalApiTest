package com.ineda.terminalApi.models;

public class ResponseErrorException extends Exception
{
    private int errorCode;

    /**
     * constructs an exception
     * @param errorCode a code indicating the problem. This should be one
     * of the codes specified in the TerminalApiError enum.
     * @param message describes the error in more detail
     */
    public ResponseErrorException( int errorCode, String message )
    {
        super( message );
        this.errorCode = errorCode;
    }

    /**
     * constructs an exception
     * @param errorCode a code indicating the problem. This should be one
     * of the codes specified in the TerminalApiError enum.
     * @param message describes the error in more detail
     * @param cause the underlying exception that caused this one to be thrown
     */
    public ResponseErrorException( int errorCode, String message, Throwable cause )
    {
        super( message, cause );
        this.errorCode = errorCode;
    }

    public int getErrorCode()
    {
        return errorCode;
    }

    public String getMessage()
    {
        return "Code=" + errorCode + ", " + super.getMessage();
    }
}
