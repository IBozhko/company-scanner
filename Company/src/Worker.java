import java.io.Serializable;

/**
 * Created by johnny on 19.06.17.
 */
public class Worker implements Serializable{

    private String name;
    private String surname;
    private double wage;
    private char sex;
    private int department;

    public Worker(String name, String surname, double wage, char sex, int department){
        this.name = name;
        this.surname = surname;
        this.wage = wage;
        this.sex = sex;
        this.department = department;
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + sex + " " + department + " " + wage + " usd";
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname(){
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getWage(){
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public char getSex(){
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getDepartment(){
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public boolean worksInDepartment(int dzial){
        return dzial == this.department;
    }
}
