package assignment;

public class Main {

    public static void main(String[] args) {
        new NamePrinter().printNames();
        //run the blackJack game in the Game_Main.java in resources
    }
}

class NamePrinter {
    /**
     * Prints the names of the group members separated by a comma.
     */ 
    public void printNames() {
        String separator = "; ";

        String[] names = {
                "Andrew Kelly",
                "Bailey Denis",
                "Matthew Chalcraft",
                "Muhammad Jamal"
                 };

        System.out.println(String.join(separator, names));
    }
}
