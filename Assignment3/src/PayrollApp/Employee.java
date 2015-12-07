package PayrollApp;

/**
 * Constructs a regular employee with the attributes provided by the user
 * @author Mark Thibault
 */
public class Employee
{

    /**
     * Id of the employee
     */
    protected int _employeeId;

    /**
     * Name of the employee
     */
    protected String _employeeName;

    /**
     * The hours per week the employee has worked
     */
    protected int _hoursPerWeek;

    /**
     * The hourly salary of the employee
     */
    protected double _hourlySalary;
    
    /**
     * Type of employee, regular, manager or director
     */
    protected String _employeeType;
    
    /**
     * The title of the employee
     */
    protected String _employeeTitle;
    
    protected int _manager;

    /**
     * Creates an employee
     *
     * @param id of the employee
     * @param name of the employee
     */
    public Employee(int id, String name)
    {
        _employeeId = id;
        _employeeName = name;
    }

    /**
     * Retrieves the Id of the employee
     *
     * @return the employeeId
     */
    public int getEmployeeId()
    {
        return _employeeId;
    }

    /**
     * Sets the Id of the employee
     *
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(int employeeId)
    {
        _employeeId = employeeId;
    }

    /**
     * Retrieves the name of the employee
     *
     * @return the employeeName
     */
    public String getEmployeeName()
    {
        return _employeeName;
    }

    /**
     * Sets the name of the employee
     *
     * @param employeeName the employeeName to set
     */
    public void setEmployeeName(String employeeName)
    {
        _employeeName = employeeName;
    }

    /**
     * Retrieves the hours per week the employee has worked
     *
     * @return the hoursPerWeek the employee has worked
     */
    public int getHoursPerWeek()
    {
        return _hoursPerWeek;
    }

    /**
     * Sets the hours per week the employee has worked
     *
     * @param hoursPerWeek the hoursPerWeek to set
     */
    public void setHoursPerWeek(int hoursPerWeek)
    {
        _hoursPerWeek = hoursPerWeek;
    }

    /**
     * Retrieves the hourly salary of the employee
     *
     * @return the _hourlySalary
     */
    public double getHourlySalary()
    {
        return _hourlySalary;
    }

    /**
     * Sets the hourly salary of the employee
     *
     * @param hourlySalary the hourlySalary to set
     */
    public void setHourlySalary(double hourlySalary)
    {
        _hourlySalary = hourlySalary;
    }   

    /**
     * @return the type of employee
     */
    public String getEmployeeType()
    {
        return _employeeType;
    }

    /**
     * @param employeeType the type of employee to set
     */
    public void setEmployeeType(String employeeType)
    {
        _employeeType = employeeType;
    }
    
    /**
     * Return the type of employee (regular, manager, director)
     * @return the typr of employee
     */
    public String getEmployeeTitle(){
        return _employeeTitle;
    }
    
    /**
     * Set the manager of the employee
     * @param manager - the manager of the employee
     */
    public void setManager(int manager){
        _manager = manager;
    }
    
    /**
     * Return the manager of the employee
     * @return 
     */
    public int getManager(){
        return _manager;
    }

    /**
     * Calculates the annual salary for an employee
     *
     * @return the annual salary
     */
    public double calculateAnnualSalary()
    {
        double salary;
        
        salary = (_hoursPerWeek * _hourlySalary) * 52;
        return salary;
    }

}
