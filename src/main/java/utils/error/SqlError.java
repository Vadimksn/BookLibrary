package utils.error;

import utils.ui.UiConstants;
import utils.ui.ViewUtil;

import java.sql.SQLException;

public class SqlError {
    public static void catchSqlException(SQLException e) {
        if (e.getErrorCode() == DatabaseError.UNIQUE_ID.getErrorCode()) {
            ViewUtil.showError(UiConstants.Dialogs.PASSPORT_DATA_ERROR);
        } else e.printStackTrace();
    }
}
