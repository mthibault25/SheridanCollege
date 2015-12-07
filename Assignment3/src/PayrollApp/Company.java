package PayrollApp;

import java.util.ArrayList;

/**
 * Constructs a company list of employees, adds employees to the list and finds
 * employees as requested
 * @author Mark Thibault
 */
public class Company
{

    private ArrayList<Employee> _employeeList;

    /**
     * Initializes the list of employees for the company
     */
    public Company()
    {
        _employeeList = new ArrayList<>();

    }

    /**
     * Adds an employee to the company list
     *
     * @param employee - the employee to be added
     */
    public void addEmployee(Employee employee)
    {
        _employeeList.add(employee);
    }

    /**
     * Returns the employee with the given ID, or null if the employee is not
     * found
     *
     * @param id - the id of the employee to return
     * @return the employee with the given id
     */
    public Employee findEmployeeById(int id)
    {
        //loop until the employee is found
        for (Employee employee : _employeeList)
        {

            //if the id given matches the id of the employee, return the employee
            if (employee.getEmployeeId() == id)
            {
                return employee;
            }
        }

        //if the program reaches here then the employee was not found
        return null;
    }

    /**
     * Find the employee at the top (i.e. CEO)
     *
     * @param top - the id of the top director
     * @return the employee with the given title
     */
    public Employee findTopDirector(int top)
    {
        //loop until the employee is found
        for (Employee employee : _employeeList)
        {

            //if the id given matches the id of the employee, return the employee
            if (employee.getManager() == top)
            {
                return employee;
            }
        }

        //if the program reaches here then the employee was not found
        return null;
    }

    /**
     * Will check the new id against the ids of the other employees in the array, if
     * the id is found to not exist the id will be accepted, otherwise the user
     * to be asked again for another id
     *
     * @param id - id to be checked
     * @return whether or not the id exists
     */
    public boolean checkID(int id)
    {
        //if no accounts exist
        if (_employeeList.isEmpty())
        {
            return false;
        }

        //if the ArrayList has accounts in it go through top see if the given
        //id already exists
        for (Employee employee : _employeeList)
        {
            //if the id is found to exist
            if (employee.getEmployeeId() == id)
            {
                return true;
            }

        }
        //if the program gets here then the id was not found and will be ok
        //to use
        return false;
    }
}
