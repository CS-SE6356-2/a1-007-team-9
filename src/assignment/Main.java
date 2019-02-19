package assignment;

public class Main {

    public static void main(String[] args) {
        new NamePrinter().printNames();
    }
}

class NamePrinter {
    /**
     * Prints the names of the group members separated by spaces.
     */
    public void printNames() {
        String separator = "+";

        String[] names = {
                "Andrew Kelly",
                "Name 2",
                "Name 3",
                "Name 4",
                "Name 5"};

        System.out.println(String.join(separator, names));
    }
}