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
        String separator = "; ";

        String[] names = {
                "Andrew Kelly",
                "Name 2",
                "Matthew Chalcraft",
                "Muhammad Jamal",
                "Name 4" };

        System.out.println(String.join(separator, names));
    }
}
