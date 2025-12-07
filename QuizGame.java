import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class QuizGame {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===========QUIZ GAME MENU =======");
            System.out.println("=====Welcome to CUI Quiz Game====");
            System.out.println("1 - Admin Login");
            System.out.println("2 - Player Login");
            System.out.println("0 - Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch(choice) {
                case 1 : adminLogin();break;
                case 2 : playerLogin();break;
                case 0 : System.out.println("Exiting program. Goodbye!");break;
                default : System.out.println("Invalid choice!");
            }
        } while(choice != 0);
    }

    //Admin Login
    public static void adminLogin() {
        System.out.println("Enter username: ");
        String username = sc.next();

        System.out.println("Enter password: ");
        String password = sc.next();

        System.out.println("You entered Username: " + username + " | Password: " + password);

        if(username.equals("admin") && password.equals("1234")){
            System.out.println("Login Successful!");
            adminMenu();}
        else{
            System.out.println("Login failed. Try Again!");
        }
    }
    public static void adminMenu() {
        int choice;
        do {
            System.out.println("\n Admin Menu:");
            System.out.println("===Welcome to Admin Menu===");
            System.out.println("1. Add Questions");
            System.out.println("2. Edit Questions");
            System.out.println("3. View Questions");
            System.out.println("4. View Marks");
            System.out.println("0. Logout");
            System.out.print("Choice: ");
            choice = sc.nextInt();

            switch(choice) {
                case 1 : addQuestions();break;
                case 2 : editQuestions();break;
                case 3 : viewQuestions();break;
                case 4 : viewMarks();break;
                case 0 : System.out.println("Logging Out");break;
                default : System.out.println("Invalid choice!");
            }
           }   while(choice != 0);
    }
public static void addQuestions(){
    int choice;
        do {
            System.out.println("\n Select Subject:");
            System.out.println("1. Biology");
            System.out.println("2. Chemistry");
            System.out.println("3. Physics");
            System.out.println("4. English");
            System.out.println("5. Logical Reasoning");
            System.out.println("0. Back");
            System.out.print("Choice: ");
            choice = sc.nextInt();

            switch(choice) {
                case 1 : addQuestionToFile("Biology.txt");break;
                case 2 : addQuestionToFile("Chemistry.txt");break;
                case 3 : addQuestionToFile("Physics.txt");break;
                case 4 : addQuestionToFile("English.txt");break;
                case 5 : addQuestionToFile("LogicalReasoning.txt");break;
                case 0 : System.out.println("Back ");break;
                default : System.out.println("Invalid choice!");
            }
           }   while(choice != 0);
}
 public static void addQuestionToFile(String fileName) {
    try{
        sc.nextLine();

        System.out.println("Enter Question: ");
        String question = sc.nextLine();

        System.out.println("Enter first option: ");
        String optA = sc.nextLine();

        System.out.println("Enter second option: ");
        String optB = sc.nextLine();

        System.out.println("Enter third option: ");
        String optC = sc.nextLine();

        System.out.println("Enter fourth option: ");
        String optD = sc.nextLine();

        System.out.println("Enter Correct option(A-D): ");
        String Ans = sc.nextLine().toUpperCase();

        FileWriter Added = new FileWriter(fileName, true);
            Added.write(question + "," + optA + "," + optB + "," + optC + "," + optD + "," + Ans + "\n");
            Added.close();

            System.out.println("Question added successfully to " + Added + "!");


        } catch (IOException e) {
            System.out.println("There is some error!!!");
        }
    }

public static void editQuestions(){}

public static void viewQuestions(){}

public static void viewMarks(){}
    
    //Player login
      public static void playerLogin(){
        System.out.println("Enter username: ");
        String username = sc.next();

        System.out.println("Enter password: ");
        String password = sc.next();

        System.out.println("You entered Username: " + username + "\nYou entered password: " + password);

        if(username.equals("Player") && password.equals("7890")){
            System.out.println("Login Successfull!!!");
            playerMenu();
        }
          else{
            System.out.println("Login failed!! Try Again....");
          }
        }
        

        //Player Menu
        public static void playerMenu(){
            int choice ; 
            do{
                System.out.println("========Player Menu========");
                System.out.println("1. Start Quiz.");
                System.out.println("2. Want to view scores.");
                System.out.println("3. View Leaderboard");
                System.out.println("4. Logout");
                System.out.println("Enter your choice: ");
                choice = sc.nextInt();

                switch(choice){
                    case 1 : startQuiz() ; break;
                    case 2 : viewScores() ; break;
                    case 3 : viewLeaderboard() ; break;
                    case 4 : System.out.println("Logging out") ; break;
                    default : System.out.println("invalid choice!");
            }

             } while(choice!=4);   
        }
             public static void startQuiz() {}
             public static void viewScores() {}
             public static void viewLeaderboard() {}
          

    }
