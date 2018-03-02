package InventoryManagementSystem;

public class Properties {
    
    //These values are used throughout the application and are consolidated here for easy, global, modification.
    
    //The title of the application.
    private static final String APPLICATION_NAME = "Inventory Management System";
    //The current version of the application.
    private static final String APPLICATION_VERSION = "v1.0";
    //The application name and version number listed together
    private static final String APPLICATION_NAME_AND_VERSION = APPLICATION_NAME.concat(" (".concat(APPLICATION_VERSION).concat(")"));
    //The header text of confirmation dialogs.
    private static final String DIALOG_HEADER = "Are you sure?";
    //The cancel dialog confirmation prompt.
    private static final String CANCEL_CONFIRMATION_PROMPT = "By clicking the OK button, you will return to the previous page and lose any unsaved changes.";
    //The delete dialog confirmation prompt.
    private static final String DELETE_CONFIRMATION_PROMPT = "By clicking the OK button, you will confirm the deletion. This action is permanent.";
    //The message presented when the user tries to submit invlaid data.
    private static final String INVALID_DATA_MESSAGE = "ERROR: Please correct your values, they contain invalid characters.";
    //The empty table placeholder label message
    private static final String TABLE_PLACEHOLDER_MESSAGE = "Use the Add button to populate this table";
    //The message that tells the user to select an element.
    private static final String TELL_USER_TO_SELECT_ELEMENT_MESSAGE = "ERROR: Please select an element from the table to perform that action.";
    
    /**
     * @return the application name
     */
    public static String getAPPLICATION_NAME() {
        return APPLICATION_NAME;
    }

    /**
     * @return the application version
     */
    public static String getAPPLICATION_VERSION() {
        return APPLICATION_VERSION;
    }
    
    /**
     * @return the application name and version
     */
    public static String getAPPLICATION_NAME_AND_VERSION() {
        return APPLICATION_NAME_AND_VERSION;
    }

    /**
     * @return the cancel confirmation prompt
     */
    public static String getCANCEL_CONFIRMATION_PROMPT() {
        return CANCEL_CONFIRMATION_PROMPT;
    }

    /**
     * @return the delete confirmation prompt.
     */
    public static String getDELETE_CONFIRMATION_PROMPT() {
        return DELETE_CONFIRMATION_PROMPT;
    }

    /**
     * @return the dialog header
     */
    public static String getDIALOG_HEADER() {
        return DIALOG_HEADER;
    }

    /**
     * @return the invalid data message
     */
    public static String getINVALID_DATA_MESSAGE() {
        return INVALID_DATA_MESSAGE;
    }
    
    /**
     * @return the table placeholder message
     */
    public static String getTABLE_PLACEHOLDER_MESSAGE() {
        return TABLE_PLACEHOLDER_MESSAGE;
    }
    
    /**
     * @return the tell user to select element message
     */
    public static String getTELL_USER_TO_SELECT_ELEMENT_MESSAGE() {
        return TELL_USER_TO_SELECT_ELEMENT_MESSAGE;
    }
}
