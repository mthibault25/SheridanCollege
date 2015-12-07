package PayrollApp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Will initialize all input that is required from the user and will print the payroll
 * statements as requested
 * @author Mark Thibault
 */
public class Payroll
{

    /**
     * The employee list for the company that will be used
     */
    Company _company;

    /**
     * MAIN MENU OPTIONS
     */
    private static final int EMPLOYEE_INFO = 1;
    private static final int PRINT_PAYROLL = 2;
    private static final int EXIT_PAYROLL_OPTIONS = 3;

    /**
     * EMPLOYEE OPTION MENU OPTIONS
     */
    private static final int EMPLOYEE_OPTION = 1;
    private static final int MANAGER_OPTION = 2;
    private static final int DIRECTOR_OPTION = 3;
    private static final int EXIT_EMPLOYEE_TYPE = 4;

    private Scanner _in = new Scanner(System.in);

    /**
     * Attributes of an employee, manager or director
     */
    private String _name;
    private int _id;
    private double _bonus;
    private int _hoursWorked;
    private double _hourlyWage;

    /**
     * Constructor that initializes the employee list for the company that
     * payroll is working with
     *
     * @param company
     */
    public Payroll(Company company)
    {
        _company = company;
    }

    /**
     * Starts the Payroll program by displaying the required user options. User
     * navigates the menus managing their employees
     */
    public void start()
    {
        while (true)
        {
            //display the main menu and perform main actions depending on user choice
            switch (showMainMenu())
            {
                case EMPLOYEE_INFO:
                    showEmployeeMenu();
                    break;
                case PRINT_PAYROLL:
                    printPayroll();
                    break;
                case EXIT_PAYROLL_OPTIONS:
                    return;
                default:
                    //ask again of user chooses 5, instead of 1-4
                    System.out.println("Please enter a valid menu option");
                    break;
            }
        }
    }

    /**
     * Shows the main menu of the payroll application
     *
     * @return the user's choice
     */
    public int showMainMenu()
    {
        System.out.print("\nPayroll:\n\n1: Enter Employee Information\n2: Print Payroll\n"
                + "3: Exit\n\nEnter your choice: ");
        return Integer.parseInt(_in.nextLine());
    }

    /**
     * Displays a menu asking the user to choose to either create a regular
     * employee, a manager or a director
     */
    public void showEmployeeMenu()
    {
        do
        {
            //display the employee type menu and perform actions depending on user choice
            switch (showEmployeeTypeMenu())
            {
                case EMPLOYEE_OPTION:
                    enterEmployeeInfo();
                    break;
                case MANAGER_OPTION:
                    enterManagerInfo();
                    break;
                case DIRECTOR_OPTION:
                    enterDirectorInfo();
                    break;
                case EXIT_EMPLOYEE_TYPE:
                    return;
                default:
                    //ask again of user chooses 5, instead of 1-4
                    System.out.println("Please enter a valid menu option");
                    break;
            }
        }
        while (true);
    }

    /**
     * Shows the user an employee menu and asks what type of employee the user
     * is trying to create
     *
     * @return the user's choice
     */
    public int showEmployeeTypeMenu()
    {
        System.out.print("\nType of Employee:\n\n1: Employee\n2: Manager\n3: Director\n4: Exit\n\n"
                + "Enter your choice: ");
        return Integer.parseInt(_in.nextLine());
    }

    /**
     * Prompts the user for the information of an employee
     *
     */
    public void enterEmployeeInfo()
    {
        //Call on the specific method to enter a certain piece of information
        _id = enterEmployeeId("Employee");
        _name = enterEmployeeName("Employee");
        _hoursWorked = enterEmployeeHours("Employee");
        _hourlyWage = enterEmployeeWage("Employee");

        //Create the Employee object
        Employee employee = new Employee(_id, _name);

        //Set the hourly salary and the hours per week worked for the employee
        employee.setHoursPerWeek(_hoursWorked);
        employee.setHourlySalary(_hourlyWage);
        employee.setEmployeeType("Regular");

        //add the new employee to the subordinate list of a manager
        Manager reportsTo = (Manager) findManager("manager that this employee reports to");
        employee.setManager(reportsTo.getEmployeeId());
        reportsTo.addSubordinate(employee);

        //Add employee to the company
        _company.addEmployee(employee);
    }

    /**
     * Prompts the user for the information of a manager
     */
    public void enterManagerInfo()
    {
        //Call on the specific method to enter a certain piece of information
        _id = enterEmployeeId("Manager");
        _name = enterEmployeeName("Manager");
        _hoursWorked = enterEmployeeHours("Manager");
        _hourlyWage = enterEmployeeWage("Manager");

        _bonus = enterEmployeeBonus("Manager");

        //Create the Manager object
        Manager manager = new Manager(_id, _name, _bonus);

        //Set the hourly salary and the hours per week worked for the manager
        manager.setHourlySalary(_hourlyWage);
        manager.setHoursPerWeek(_hoursWorked);
        manager.setEmployeeType("Manager");

        //find the manager of this manager
        findManagerManager(manager);

        //Add employee to the company
        _company.addEmployee(manager);
    }

    /**
     * Prompts the user for the information of a director
     */
    public void enterDirectorInfo()
    {
        String title; //the director's title
        double percentageBonus; //the percentage of the base salary that will be used for the bonus

        //Call on the specific method to enter a certain piece of information
        _id = enterEmployeeId("Director");
        _name = enterEmployeeName("Director");

        System.out.print("Enter the title of the Director: ");
        title = _in.nextLine();

        _hoursWorked = enterEmployeeHours("Director");
        _hourlyWage = enterEmployeeWage("Director");
        _bonus = enterEmployeeBonus("Director");

        System.out.print("Enter the percentage of the base salary that will be used\n"
                + "to calculate the performance bonus: ");
        percentageBonus = Double.parseDouble(_in.nextLine());

        //Create the Director object
        Director director = new Director(_id, _name, _bonus, percentageBonus);

        //Set the hourly salary and the hours per week worked for the director
        director.setHourlySalary(_hourlyWage);
        director.setHoursPerWeek(_hoursWorked);
        director.setEmployeeType("Director");
        director.setEmployeeTitle(title);

        //ask for director's manager, if any
        findDirectorManager(director);

        //Add director to the company
        _company.addEmployee(director);
    }

    /**
     * Prompts user for the name of the employee
     *
     * @param type of employee (regular, manager or director)
     * @return the name of the employee
     */
    public String enterEmployeeName(String type)
    {

        System.out.print("Enter the name of the " + type + ": ");
        return _in.nextLine();
    }

    /**
     * Prompts user for the id of the employee
     *
     * @param type of employee (regular, manager or director)
     * @return the id of the employee
     */
    public int enterEmployeeId(String type)
    {
        int id;

        do
        {
            System.out.print("Enter the id of the " + type + ": ");

            try
            {
                id = Integer.parseInt(_in.nextLine());

                if (id < 100)
                {
                    System.out.println("IDs must be three digits long, starting with 100");
                }
                else if (id > 999)
                {
                    System.out.println("IDs can only be three digits long");
                }
                else
                {
                    if (_company.checkID(id) == false)
                    {
                        return id;
                    }
                    else
                    {
                        System.out.println("ID already exists. Please enter another one");
                    }
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("You can only enter a number for an id");
            }
        }
        while (true);
    }

    /**
     * Prompts user for the number of hours worked per week for the employee
     *
     * @param type of employee (regular, manager or director)
     * @return the number of hours worked for the employee
     */
    public int enterEmployeeHours(String type)
    {
        do
        {
            System.out.print("Enter the number of hours worked per week for the " + type + ": ");

            try
            {
                return Integer.parseInt(_in.nextLine());
            }
            catch (NumberFormatException e)
            {
                System.out.println("You can only enter a number for an id");
            }
        }
        while (true);
    }

    /**
     * Prompts user for the hourly wage of the employee
     *
     * @param type of employee (regular, manager or director)
     * @return the hourly wage of the employee
     */
    public double enterEmployeeWage(String type)
    {
        do
        {
            System.out.print("Enter the hourly wage of the " + type + ": ");

            try
            {
                return Double.parseDouble(_in.nextLine());
            }
            catch (NumberFormatException e)
            {
                System.out.println("You can only enter a number for an wage amount");
            }
        }
        while (true);
    }

    /**
     * Prompts user for the annual bonus of the employee
     *
     * @param type of employee (manager or director)
     * @return the bonus of the employee
     */
    public double enterEmployeeBonus(String type)
    {
        do
        {
            System.out.print("Enter the annual bonus of the " + type + ": ");

            try
            {
                return Double.parseDouble(_in.nextLine());
            }
            catch (NumberFormatException e)
            {
                System.out.println("You can only enter a number for an id");
            }
        }
        while (true);
    }

    /**
     * Adds an employee to the list of the manager's subordinates NOTE: can only
     * be an employee, not another Manager or Director
     *
     * @param manager - the manager that reports to another employee
     */
    public void findManagerManager(Manager manager)
    {
        while (true)
        {
            Employee employee = findManager("director that this manager reports to"); //the requested employee

            if (employee != null)
            {
                //add the manager to subordinate list of the given director, but check first to make sure that it is a director
                //and not another manager or a regular employee
                switch (employee.getEmployeeType())
                {
                    case "Manager":
                        System.out.println("\nA Manager cannot report to another Manager\n");
                        break;
                    case "Regular":
                        System.out.println("\nA Manager cannot report to a regular employee\n");
                        break;
                    default:
                        manager.setManager(employee.getEmployeeId());
                        Director director = (Director) employee;
                        director.addSubordinate(manager);
                        return;
                }
            }
        }
    }

    /**
     * Adds an employee to the list of subordinates for the director NOTE: can
     * only be another director
     *
     * @param director - the director that reports to another director
     */
    public void findDirectorManager(Director director)
    {
        int managerChoice; //YES or NO

        System.out.print("Does the director report to another director (1: YES, 2: NO): ");
        managerChoice = Integer.parseInt(_in.nextLine());

        //User wants to add subordinates for the manager
        if (managerChoice == 1) //1 = YES
        {
            while (true)
            {
                Employee employee = findManager("director");

                //add the manager to subordinate list of the given director, but check first to make sure that it is a director
                //and not another manager or a regular employee
                switch (employee.getEmployeeType())
                {
                    case "Manager":
                        System.out.println("\nA Director cannot report to a Manager\n");
                        break;
                    case "Regular":
                        System.out.println("\nA Director cannot report to a regular employee\n");
                        break;
                    default:
                        director.setManager(employee.getEmployeeId());
                        Director requestedDirector = (Director) employee;
                        requestedDirector.addSubordinate(director);
                        return;
                }
            }
        }
        else //the director does not report to anyone
        {
            director.setManager(0);
        }
    }

    /**
     * Finds a manager with a given name, return null if manager is not found
     *
     * @param employeeType - the type of employee that the user is looking for
     * @return the employee with the given name
     */
    public Employee findManager(String employeeType)
    {
        int givenId; //id for requested employee

        while (true)
        {
            //Ask for the id of the requested employee
            System.out.print("Enter the id of the " + employeeType + ": ");
            givenId = Integer.parseInt(_in.nextLine());

            Employee employee = _company.findEmployeeById(givenId);

            if (employee != null)
            {
                return employee;
            }
            else
            {
                System.out.println("Employee not found. Please enter a vaild name");
            }

        }
    }

    /**
     * Find a employee the employee without a manager
     *
     * @return the requested employee
     */
    public Employee findTopDirector()
    {

        //loop through comapny to find the CEO
        Employee employee = _company.findTopDirector(0);

        if (employee != null)
        {
            return employee;
        }

        //if the program gets here then the employee was not found
        return null;
    }

    /**
     * Starts the printing of a payroll statement
     */
    public void printPayroll()
    {
        System.out.println("\nCompany Payroll Report====================");

        //Start the payroll statement printing
        printTopDirector();
    }

    /**
     * Prints out the payroll information of the top director
     */
    public void printTopDirector()
    {
        Director topDirector = (Director) findTopDirector(); // find the top director
        topDirector.calculateBaseSalary(); // calculate the base salary for the director

        //The list of subordinates
        ArrayList<Employee> topDirectorSubList = topDirector.getSubordinateList();

        //print out the CEO
        System.out.printf("%s(%s): %,.0f-%,.0f\n", topDirector.getEmployeeName(), topDirector.getEmployeeTitle().toUpperCase(),
                topDirector.getBaseSalary(), topDirector.calculateMaxSalary());

        //print out the directors
        for (Employee employee : topDirectorSubList)
        {
            if (employee.getManager() == topDirector.getEmployeeId())
            {
                //Depending on the type of subordinate call the appropriare method
                switch (employee.getEmployeeType())
                {
                    case "Director":
                        Director director = (Director) employee;
                        printDirectors(director);
                    case "Manager":
                        Manager manager = (Manager) employee;
                        printManagers(manager);
                        break;
                    case "Employee":
                        printEmployees(employee);
                        break;
                    default:
                        return;
                }
            }

        }

    }

    /**
     * Prints out the payroll information for all the directors in the top
     * director's subordinate list
     *
     * @param director - the current director being printed
     */
    public void printDirectors(Director director)
    {
        director.calculateBaseSalary(); //calculate the base salary for the director
        System.out.printf("\t%s(%s): %,.0f-%,.0f\n", director.getEmployeeName(), director.getEmployeeTitle().toUpperCase(),
                director.getBaseSalary(), director.calculateMaxSalary());

        //The subordinate list
        ArrayList<Employee> directorSubList = director.getSubordinateList();

        for (Employee employee : directorSubList)
        {
            if (employee.getManager() == director.getEmployeeId())
            {
                //Depending on the type of subordinate call the appropriare method
                switch (employee.getEmployeeType())
                {
                    case "Manager":
                        Manager manager = (Manager) employee;
                        printManagers(manager);
                        break;
                    case "Employee":
                        printEmployees(employee);
                        break;
                    default:
                        return;
                }
            }
        }
    }

    /**
     * Prints out the payroll information for all the managers in the
     * subordinate lists of the directors
     *
     * @param manager - the current manager being printed
     */
    public void printManagers(Manager manager)
    {
        if ("Manager".equals(manager.getEmployeeType()))
        {
            System.out.printf("\t\t%s(%s): %,.0f\n", manager.getEmployeeName(), manager.getEmployeeType(),
                    manager.calculateAnnualSalary());

            //The subordinate list
            ArrayList<Employee> managerSubList = manager.getSubordinateList();

            for (Employee employee : managerSubList)
            {
                if (employee.getManager() == manager.getEmployeeId())
                {
                    //Call on method for every employee in the list
                    printEmployees(employee);
                }
            }
        }
    }

    /**
     * Prints out the payroll information of all regular employees in the
     * subordinate lists of either a manager or a director
     *
     * @param employee - the current employee being printed
     */
    public void printEmployees(Employee employee)
    {
        System.out.printf("\t\t\t%s: %,.0f\n", employee.getEmployeeName(), employee.calculateAnnualSalary());
    }
}
