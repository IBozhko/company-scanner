import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by johnny on 19.06.17.
 */
public class Staff {

    private Worker[] workers_;
    private int workersCount_;
    private Scanner fileScanner;

    public Staff(){
        workers_ = new Worker[100];
        workersCount_ = 0;
    }

    public Worker[] getWorkers_(){
        return workers_;
    }

    public void setWorkers_(Worker[] workers_){
        this.workers_ = workers_;
    }

    public int getWorkersCount_(){
        return workersCount_;
    }

    public void setWorkersCount_(int workersCount_){
            this.workersCount_ += workersCount_;
    }

    public void addWorker(Worker worker){
        if (workersCount_ <workers_.length) {
            workers_[workersCount_] = worker;
            workersCount_++;
        }
        else
            System.out.println("Too many workers");
    }

    public void addWorkerManually(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please, enter new worker data:");
            System.out.println("Name: ");
            String name = br.readLine();
            System.out.println("Surname: ");
            String surname = br.readLine();
            System.out.println("Sex: ");
            char sex = br.readLine().charAt(0);
            System.out.println("Department: ");
            int department = Integer.parseInt(br.readLine());
            System.out.println("Wage: ");
            double wage = Double.parseDouble(br.readLine());
            Worker newWorker = new Worker(name, surname, wage, sex, department);
            workers_[workersCount_] = newWorker;
            workersCount_++;
        }
        catch (IOException ioe){
            System.out.println("Oops, something's wrong - " + ioe);
        }
    }

    public void importFromTxt(String address){
        try {
            File file = new File(address);
            fileScanner = new Scanner(file);
            int amount = 0;
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] div = data.split(" ");
                String name = div[0];
                String surname = div[1];
                double wage = Double.parseDouble(div[2]);
                char sex = div[3].charAt(0);
                int department = Integer.parseInt(div[4]);
                Worker worker = new Worker(name, surname, wage, sex, department);
                workers_[workersCount_] = worker;
                amount++;
                workersCount_++;
            }
            System.out.println("Data imported successfully for " + amount + " workers from: " + file.getName());
        }
        catch (FileNotFoundException e){
            System.out.println("Wrong file name");
        }
        finally {
            try {
                fileScanner.close();
            }
            catch (NullPointerException e){
                System.out.println("Import was not possible");
            }
        }
    }

    public double averageWage(){
        double placa = 0;
        for (Worker worker : workers_) {
            if (worker == null)
                break;
            else
                placa += worker.getWage();
        }
        return placa/ workersCount_;
    }

    public double averageWage(int department){
        double totalWage = 0;
        int workersInDep = 0;
        int[] departments = new int[workers_.length];
        for (int i = 0; i < workers_.length; i++) {
            if (workers_[i]==null)
                break;
            else
                departments[i] = workers_[i].getDepartment();
        }
        for (int value : departments) {
            if (value == department)
                workersInDep++;
        }
        for (Worker worker : workers_) {
            if (worker == null)
                break;
            if (worker.getDepartment() == department)
                totalWage += worker.getWage();
        }
        return totalWage/workersInDep;
    }

    public int[] returnDepartment(){
        int[] departments = new int[workersCount_];
        for (int i = 0; i < workers_.length; i++) {
            if (workers_[i] == null) {
                break;
            }
            departments[i] = workers_[i].getDepartment();
        }
        return Arrays.stream(departments).distinct().toArray();
    }

    public void writeAll(){
        for (Worker worker : workers_) {
            if (worker == null)
                break;
            else
                System.out.println(worker);
        }
        System.out.println("Average wage: " + averageWage() + " usd");
        for (int i = 0; i < returnDepartment().length; i++) {
            System.out.println("Average wage in department " + returnDepartment()[i] + " is: " + averageWage(returnDepartment()[i]));
        }
    }

    public void writeToFile(String mejsce) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(mejsce);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (Worker worker : workers_) {
                if (worker == null) {
                    break;
                }
                objectOutputStream.writeObject(worker);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File doesn't exist");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                assert fileOutputStream != null;
                fileOutputStream.close();
                assert objectOutputStream != null;
                objectOutputStream.close();
            } catch (IOException ioe) {
                System.out.println(ioe.toString());
            }
        }
    }

    public void readFromFile(String path) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
            objectInputStream = new ObjectInputStream(fileInputStream);
            while (fileInputStream.available()>0) {
                workers_[workersCount_] = (Worker) objectInputStream.readObject();
                workersCount_++;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File doesn't exist");
        }
        catch (ClassNotFoundException | IOException e){
            System.out.println(e);
        } finally {
            try {
                assert fileInputStream != null;
                fileInputStream.close();
                assert objectInputStream != null;
                objectInputStream.close();
            } catch (IOException ioe) {
                System.out.println(ioe.toString());;
            }
        }
    }
}
