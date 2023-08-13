package com.crio.xcompany.company;

import java.util.List;
import java.util.LinkedList;


public class Employee {
    private String name;
    private Gender gender;
    private Employee manager;
    private List<Employee> reportees;

    /**
     *
     * @param name
     * @param gender
     */
    public Employee(String name, Gender gender) {
        this.name=name;
        this.gender=gender;
        this.manager=null;
        this.reportees = new LinkedList<>();
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return gender of employee
     */
    public Gender getGender() {
        return gender;
    }

    // TODO: CRIO_TASK_MODULE_XCOMPANY
    // Please define all the methods required here as mentioned in the XCompany BuildOut Milestone before implementing the logic.
    // This will ensure that the project can be compiled successfully.

    /**
     *
     * @return manager of the employee
     */
    public Employee getManager()
    {
       return this.manager;
    }

    /**
     *
     * @return List reportees
     */
    public List getDirectReports()
    {
        return this.reportees;
    }

    /**
     * Assigning manager
     * @param employee
     */
    public void assignManager(Employee employee)
    {
        this.manager = employee;
        this.manager.addReportee(this);
    }

    /**
     * Adding reportees
     * @param employee
     */
    public void addReportee(Employee employee)
    {
        reportees.add(employee);
    }

    /**
     *      We are finding if employee has manager if it has then ust return list of manager and its employees
     * else return employee and list of its reportees.
     * @return List of teammates
     */
    public List getTeamMates()
    {
      List<Employee> teamMates = new LinkedList<>();
      if(this.manager==null)
      {
        teamMates.addAll(this.getDirectReports());
      }
      else
      {
      teamMates.add(this.manager);
      teamMates.addAll(this.manager.getDirectReports());
      }
      return teamMates;
    }

    /**
     * Printing Employee information.
     * @return
     */
    @Override
    public String toString() {
        return "Employee [name=" + name + ", gender=" + gender + "]";
    }   
}
