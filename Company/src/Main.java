/**
 * Created by johnny on 19.06.17.
 */
public class Main {
    public static void main(String[] args) {
        Staff staff = new Staff();
        staff.addWorker(new Worker("Name1", "Surname1", 1000, 'm', 3));
        staff.addWorker(new Worker("Name2", "Surname2", 1000, 'm', 1));
        staff.addWorker(new Worker("Name3", "Surname3", 100, 'm', 1));
        staff.importFromTxt("/Users/johnny/Desktop/tes.txt");
        staff.writeToFile("/Users/johnny/Desktop/file2.ser");
        staff.readFromFile("/Users/johnny/Desktop/file2.ser");
        staff.writeAll();
    }
}
