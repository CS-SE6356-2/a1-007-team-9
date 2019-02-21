package assignment;

public class Main {

    public static void main(String[] args) {
        new NamePrinter().printNames();
    }
}

class NamePrinter {
    /**
     * Prints the names of the group members separated by a comma.
     */ 
    public void printNames() {
        String separator = ", ";

        String[] names = {
                "Andrew Kelly",
                "Bailey Denis",
                "Matthew Chalcraft",
                "Name 3",
                "Name 4" };

        System.out.println(String.join(separator, names));
    }
}
