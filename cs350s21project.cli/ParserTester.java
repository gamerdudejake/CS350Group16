import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.HashSet;


public class ParserTester {

    public static void main(String[] args) {

        // !!!THIS LOOP DOESNT END FOR TESTING, YOU WILL NEED TO MANUALLY END IT!!!!

        int i = 1;
        int j = 2;
        while(i < j) {
            System.out.println("\nPlease enter command (then hit enter):");
            Parser parser = new Parser();
            parser.runParser();
        }
    }

}