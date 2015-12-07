package PayrollApp;

import java.util.Random;

/**
 * Constructs a director with the attributes provided by the user, extends the
 * manager class
 * @author Mark Thibault
 */
public class Director extends Manager
{

    /**
     * The performance percentage bonus for the director, a percentage of the
     * base salary to be added to the annual salary
     */
    private double _percentage;

    /**
     * The base salary of the director
     */
    private double _baseSalary;

    /**
     * Creates a director using the manager as a base class, also initializes
     * the list of subordinates
     *
     * @param id of the director
     * @param name of the director
     * @param bonus for the director
     * @param percentage percentage of base salary used to calculate performance
     * bonus for the director
     */
    public Director(int id, String name, double bonus, double percentage)
    {
        super(id, name, bonus);

        _percentage = percentage;
    }

    /**
     * Retrieves the performance bonus of the director
     *
     * @return the performance
     */
    public double getPerformance()
    {
        return _percentage;
    }

    /**
     * Sets the performance percentage bonus for the director
     *
     * @param performance the performance bonus to set
     */
    public void setPerformance(double performance)
    {
        _percentage = performance;
    }

    /**
     * @return the baseSalary
     */
    public double getBaseSalary()
    {
        return _baseSalary;
    }

    /**
     * @param baseSalary the baseSalary to set
     */
    public void setBaseSalary(double baseSalary)
    {
        _baseSalary = baseSalary;
    }

    /**
     * Sets the type of employee
     *
     * @param employeeType - the type of employee
     */
    @Override
    public void setEmployeeType(String employeeType)
    {
        _employeeType = employeeType;
    }
    
    /**
     * Sets the title of the employee
     * @param title of the employee
     */
    @Override
    public void setEmployeeTitle(String title){
        _employeeTitle = title;
    }

    /**
     * Set the manager of the director
     * @param manager - the manager of the director
     */
    @Override
    public void setManager(int manager){
        _manager = manager;
    }

    /**
     * Calculates the maximum annual salary for the director.
     *
     * @return the maximum salary that a director can earn for the year
     */
    public double calculateMaxSalary()
    {

        _baseSalary += _baseSalary * (_percentage / 100);

        return _baseSalary;
    }

    /**
     * Calculates the base salary for the director
     */
    public void calculateBaseSalary()
    {
        this.setBaseSalary(super.calculateAnnualSalary());
    }

    /**
     * Adds a subordinate employee to the list of subordinate employees. NOTE:
     * Directors can be a subordinate to another director
     *
     * @param employee - the employee to be added to the subordinate list
     */
    @Override
    public void addSubordinate(Employee employee)
    {
        //add the employee to the subordinate list
        _subordinateList.add(employee);
    }

    /**
     * Calculates the budget forecast for a specific director
     *
     * @param budgetType - the type of budget that the director is looking for,
     * either product or salary
     * @return the budget forecast for the director
     */
    public double createBudgetForecast(String budgetType)
    {
        if (budgetType.equalsIgnoreCase("product"))
        {
            Random random = new Random();
            double budget = random.nextInt(100000) + 10000;
            return budget;
        }
        else if (budgetType.equalsIgnoreCase("salary"))
        {
            double salary = 0;

            for (Employee employee : _subordinateList)
            {
                salary += employee.calculateAnnualSalary();
            }
            return salary;
        }
        return 0;
    }

}
