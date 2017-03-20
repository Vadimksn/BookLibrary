package utils.error;

public enum  DatabaseError {
    UNIQUE_ID(19);

    int errorCode;

    DatabaseError(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
