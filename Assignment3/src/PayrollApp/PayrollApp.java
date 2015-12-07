package PayrollApp;

/**
 * The main class of the program. Used when running the PayrollAppMain
 * configuration
 *
 * @author Mark Thibault
 */
public class PayrollApp
{

    /**
     * The main program that starts the program
     *
     * @param args not used
     */
    public static void main(String[] args)
    {

        try
        {
            PayrollApp app = new PayrollApp();
            app.run();
        }
        catch (Exception e)
        {
            System.out.println("An error occurred. Please contact your ... administrator.");
        }
    }

    /**
     * Truly the main method of the application object. Invoked as soon as an
     * application object is created. Will create a predefined employee list and
     * start the payroll
     */
    private void run()
    {
        Company company = new Company();
        Payroll payroll = new Payroll(company);
        payroll.start();
    }
}
