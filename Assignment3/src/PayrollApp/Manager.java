package PayrollApp;

import java.util.ArrayList;

/**
 * Constructs a manager with the attributes provided by the user, extends the
 * employee class
 * @author Mark Thibault
 */
public class Manager extends Employee
{

    /**
     * The Manager's annual bonus
     */
    private double _bonus;

    /**
     * List to hold all subordinate employees of the manager
     */
    protected ArrayList<Employee> _subordinateList;

    /**
     * Creates a manager using the employee as a base class, also initializes
     * the subordinate list
     *
     * @param id of the manager
     * @param name of the manager
     * @param bonus for the manager
     */
    public Manager(int id, String name, double bonus)
    {
        super(id, name);

        _bonus = bonus;
        _subordinateList = new ArrayList<>();
    }

    /**
     * Retrieves the bonus for the manager
     *
     * @return the bonus
     */
    public double getBonus()
    {
        return _bonus;
    }

    /**
     * Sets the bonus for the manager
     *
     * @param bonus the bonus to set
     */
    public void setBonus(double bonus)
    {
        _bonus = bonus;
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
    public void setEmployeeTitle(String title){
        _employeeTitle = title;
    }

    /**
     * Return the subordinate list for the manager
     * @return the subordinate list
     */
    public ArrayList getSubordinateList()
    {
        return _subordinateList;
    }
    
    /**
     * Sets the manager of the employee
     * @param manager - the manager of the employee
     */
    @Override
    public void setManager(int manager){
        _manager = manager;
    }

    /**
     * Calculates the annual salary for the manager. Overrides the method in the
     * Employee bass class
     *
     * @return the annual salary
     */
    @Override
    public double calculateAnnualSalary()
    {
        double salary = super.calculateAnnualSalary() + _bonus;

        return salary;
    }

    /**
     * Adds a subordinate employee to the list of subordinate employees. NOTE:
     * Other managers and directors cannot be a subordinate of a manager
     *
     * @param employee - the employee to be added to the subordinate list
     */
    public void addSubordinate(Employee employee)
    {
        //add the employee to the subordinate list
        _subordinateList.add(employee);
    }
}
