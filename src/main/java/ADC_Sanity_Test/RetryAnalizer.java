package ADC_Sanity_Test;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalizer implements IRetryAnalyzer {

    int counter = 0;
    int retryLimit = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (counter < retryLimit)
        {
            counter++;
            return true;
        }
        return false;
    }
}
