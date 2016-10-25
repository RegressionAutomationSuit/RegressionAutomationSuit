package util;

public class Constants {

	// XLS PATHS
	public static final String SUITE_DSTV_MOZILLA_XLS_PATH = System
			.getProperty("user.dir") + "\\src\\test\\resources\\DSTV_MOZILLA.xlsx";

	public static final String SUITE_GOTV_XLS_PATH = System
			.getProperty("user.dir") + "\\executioninfo\\SuiteRegister.xlsx";
	
	// DRIVER LOCATIONS
	public static final String CHROMEDRIVER_LOCATION = System
			.getProperty("user.dir")
			+ "\\drivers\\chromedriver_win32\\chromedriver.exe";

	public static final String IEDRIVER_LOCATION = System.getProperty("user.dir")
			+ "\\drivers\\IEDriverServer_Win32_2.47.0\\IEDriverServer.exe";

	// SHEET NAMES
	public static final String TESTCASE_SHEET = "TestCases";
	public static final String TESTSUITE_SHEET = "SuiteName";

	// COLUMN NAMES IN XLS
	public static final String COL_RUNMODENAME = "Runmode";
	public static final String TESTCASE_NAME = "TCID";
	public static final String TESTSUITE_COL_SUITENAME = "TSID";

	// RUNMODES
	public static final String RUNMODE_YES = "Y";
	public static final String RUNMODE_NO = "N";

	// ERRORS
	public static final String OPENBROWSER_ERROR = "Error - Error while opening the browser ";
	public static final String NAVIGATE_ERROR = "Error - Error while navigating to url ";
	public static final String FIND_ELEMENT_ERROR = "Error - Not able to find element ";
	public static final String LOCATOR_ERROR = "Error - Invalid Locator ";
	public static final String GENERAL_ERROR = "ERROR - FAILED KEYWORD - ";
	public static final String TEXTNOTPRESENT_ERROR = "ERROR - NOT ABLE TO LOCATE TEXT";

	// FAILURES
	public static final String ELEMENT_NOT_FOUND_FAILURE = "Failure - Element not found ";
	public static final String TITLE_NOT_MATCH_FAILURE = "Failure - Title do not match ";
	public static final String LOGIN_FAILURE = "Failure - Login not successful ";
	public static final String CLICKANDWAIT_FAILURE = "FAIL - Could not click and wait - ";
	public static final String PORTFOLIONAME_NOTPRESENT_FAILURE = "Portfolio name not present - ";
	public static final String DUPLICATE_FAILURE = "Fail - Duplicate portfolio name error was expected but NOT found for portfolio name - ";
	public static final String LOGIN_INVALID_FAILURE = "Failure - Able to login with invalid credentials";

	public static final String TEXTNOTPRESENT_FAILURE = "Failure - Text is not present on the page";

	// TEXTS
	public static final String ITEMS_PER_PAGE ="100";
	public static final String SIMPLE_ASSETGROUP_SAMEID_MESSAGE="Simple Asset Group Id already exists";
	public static final String ASSETS_ATTRIBUTE_DASHBOARD="Dashboard";
	public static final String ASSETS_ATTRIBUTE_BRANDING="Branding";
	public static final String ASSETS_ATTRIBUTE_DESIGN="Design";
	public static final String ASSETS_ATTRIBUTE_CLASSIFICATION="Classification";
	public static final String ASSETS_ATTRIBUTE_LINKSANDNOTES="Links and Notes";
	public static final String ASSETS_ATTRIBUTE_MANAGEMENT="Management";
	public static final String ASSETS_ATTRIBUTE_SERVICEFEATURES="Service Features";
	public static final String ASSETS_ATTRIBUTE_SUBDIVISIONDETAILS="Subdivision Details";
	public static final String ASSETS_ATTRIBUTE_AUDITTRAIL="Audit Trail";

	

	

}
