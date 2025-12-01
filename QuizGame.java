import java.util.Scanner;
public class QuizGame {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== QUIZ GAME MENU =====");
            System.out.println("1 - Admin Login");
            System.out.println("2 - Player Login");
            System.out.println("0 - Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch(choice) {
                case 1 : adminLogin();
                case 2 : playerLogin();
                case 0 : System.out.println("Exiting program. Goodbye!");
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
            System.out.println("1. Add Questions");
            System.out.println("2. Edit Questions");
            System.out.println("3. View Questions");
            System.out.println("4. View Marks");
            System.out.println("0. Logout");
            System.out.print("Choice: ");
            choice = sc.nextInt();

            switch(choice) {
                case 1 -> addQuestions();
                case 2 -> viewQuestions();
                case 3 -> editQuestions();
                case 4 -> viewMarks();
                case 0 -> System.out.println("Logging Out");
                default -> System.out.println("Invalid choice!");
            }
           }   while(choice != 0);
    }
public static void addQuestions(){}

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
