import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class QuizGame {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int choice = -1;
        do {
            try{
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
            }catch(InputMismatchException e){
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
                choice = -1;
            }catch(Exception e){
                System.out.println("Unexpexted error: " + e);
                sc.nextLine();
                choice = -1;
            }
        } while(choice != 0);
    }

    //Admin Login
    public static void adminLogin() {
        System.out.println("Enter username: ");
        String username = sc.next();

        System.out.println("Enter password: ");
        String password = sc.next();

        boolean loginSuccess = false;

        try {
            BufferedReader key = new BufferedReader(new FileReader("admin.txt"));
            String line;
            
            while((line = key.readLine()) != null){
                String[] index = line.split(":");
                if(index[0].equals(username) && index[1].equals(password)){
                    loginSuccess = true;
                    break;
                }
            } 
            key.close();
        } catch (Exception e) {
            System.out.println("Error reading file!");
        }
         if(loginSuccess){
        System.out.println("Login Successful!");
        adminMenu();
    } else {
        System.out.println("Login failed. Try Again!");
    }
}
    //Admin Menu
    public static void adminMenu() {
        int choice;
        do {
            try{
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
           }catch(InputMismatchException e){
              System.out.println("Invalid input! Please enter a number.");
              sc.nextLine();
              choice = -1;
           } catch(Exception e){
               System.out.println("Unexpected error in Admin Menu.");
               sc.nextLine();
               choice = -1;
           } 
         } while(choice != 0);
    }
    //Add Questions
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
        while(!Ans.matches("[A-D]")) {
           System.out.println("Invalid input! Enter A, B, C, or D:");
           Ans = sc.nextLine().toUpperCase();
        } 

        FileWriter Added = new FileWriter(fileName, true);
            Added.write(question + "," + optA + "," + optB + "," + optC + "," + optD + "," + Ans + "\n");
            Added.close();

            System.out.println("Question added successfully to " + fileName + "!");


        } catch (IOException e) {
            System.out.println("There is some error!!!");
        }catch(Exception e){
            System.out.println("Unexpected error.");
        }
    }
   //Edit Questions
public static void editQuestions(){
    int choice;
    String fileName = "";

    System.out.println("\nSelect Subject to Edit Questions:");
    System.out.println("1. Biology");
    System.out.println("2. Chemistry");
    System.out.println("3. Physics");
    System.out.println("4. English");
    System.out.println("5. Logical Reasoning");
    System.out.print("Choice: ");
    choice = sc.nextInt();
    sc.nextLine();

    switch (choice) {
        case 1: fileName = "Biology.txt"; break;
        case 2: fileName = "Chemistry.txt"; break;
        case 3: fileName = "Physics.txt"; break;
        case 4: fileName = "English.txt"; break;
        case 5: fileName = "LogicalReasoning.txt"; break;
        default:
            System.out.println("Invalid choice!");
            return;
    }

    try {
        String[] questions = new String[100];
        int count = 0;

        BufferedReader read = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = read.readLine()) != null) {
            questions[count] = line;
            count++;
        }
        read.close();

        System.out.println("\nQuestions:");
        for (int i = 0; i < count; i++) {
            String[] parts = questions[i].split(",");
            System.out.println((i + 1) + ". " + parts[0]);
        }

        System.out.print("\nEnter question number to edit: ");
        int qNo = sc.nextInt();
        sc.nextLine();

        if (qNo < 1 || qNo > count) {
            System.out.println("Invalid question number!");
            return;
        }

        System.out.println("Enter new question:");
        String question = sc.nextLine();

        System.out.println("Enter option A:");
        String optA = sc.nextLine();

        System.out.println("Enter option B:");
        String optB = sc.nextLine();

        System.out.println("Enter option C:");
        String optC = sc.nextLine();

        System.out.println("Enter option D:");
        String optD = sc.nextLine();

        System.out.println("Enter correct option (A-D):");
        String ans = sc.nextLine().toUpperCase();

        questions[qNo - 1] = question + "," + optA + "," + optB + "," + optC + "," + optD + "," + ans;


        FileWriter writer = new FileWriter(fileName);
        for (int i = 0; i < count; i++) {
            writer.write(questions[i] + "\n");
        }
        writer.close();

        System.out.println("Question updated successfully!");

    }catch(FileNotFoundException e){
        System.out.println("File not found.");
    }catch(ArrayIndexOutOfBoundsException e){
        System.out.println("Invalid question number.");
    }catch (IOException e) {
        System.out.println("Error editing question!");
    }catch(Exception e){
        System.out.println("Unexpected error.");
    }
}

   //View Questions
public static void viewQuestions(){
    int choice;
           String fileName = "";

    do{
        System.out.println("\nSelect Subject: ");
        System.out.println("1. Biology");
        System.out.println("2. Chemistry");
        System.out.println("3. Physics");
        System.out.println("4. English");
        System.out.println("5. Logical Reasoning");
        System.out.println("0. Back");
        choice = sc.nextInt();

        switch (choice){
            case 1 : fileName = "Biology.txt"; break;
            case 2 : fileName = "Chemistry.txt"; break;
            case 3 : fileName = "Physics.txt"; break;
            case 4 : fileName = "English.txt"; break;
            case 5 : fileName = "LogicalReasoning.txt"; break;
            case 0 : return;
            default:
                System.out.println("Invalid choice!");
                continue;
        }
    try{
        BufferedReader view = new BufferedReader(new FileReader(fileName));
        String line;
        int qNo = 1;

        System.out.println("\n----Questions----\n");

        while((line = view.readLine()) != null){
            String[] index = line.split(",");
            System.out.println("Q" + qNo + ". " + index[0]);
            System.out.println("   A. " + index[1]);
            System.out.println("   B. " + index[2]);
            System.out.println("   C. " + index[3]);
            System.out.println("   D. " + index[4]);
            System.out.println("   Answer: " + index[5]);
            System.out.println(); 
            qNo++;      
    }
    view.close();
    }catch(FileNotFoundException e){
        System.out.println("Question file not found.");
    }catch(ArrayIndexOutOfBoundsException e){
        System.out.println("Invalid question format.");
    }catch(IOException e){
        System.out.println("Error Reading file");
    }catch(Exception e){
        System.out.println("Unexpected error.");
    }
}while(true);
}
   // vIEW Score
public static void viewMarks(){
    try{
        BufferedReader marks = new BufferedReader(new FileReader("Marks.txt"));
        String line;

        System.out.println("\n----PLAYER MARKS----\n");

        while((line = marks.readLine()) != null){
           String[] index = line.split(",");
           System.out.println("Player: " + index[0]);
           System.out.println("Marks: " + index[1]);
           System.out.println("----------------------");
       }
       marks.close();
    }catch(FileNotFoundException e){
        System.out.println("Marks file not found.");
    }catch(ArrayIndexOutOfBoundsException e){
        System.out.println("Marks file format error");
    }catch(IOException e){
        System.out.println("Error reading file.");
    }catch(Exception e){
        System.out.println("Unexpected error.");
    }
}
    
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
             public static void startQuiz() {
                 int choice;
             do{
            System.out.println("Enter the subject you want to take the quiz of:");
            System.out.println("1.Maths");
            System.out.println("2.Physics");
            System.out.println("3.Chemistry");
            System.out.println("4.Biology");
            System.out.println("5.English");
            System.out.println("0.Want to exit the quiz");
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
            
            switch (choice) {
              case 1: mathsQuiz() ; break ;
              case 2: physicsQuiz() ; break ;
              case 3: chemistryQuiz() ; break;
              case 4: biologyQuiz() ; break ;
              case 5: englishQuiz() ; break;
              case 0: exitQuiz() ; break ;            
              default:  System.out.println("Invalid choice!"); break;
            }
          } while (choice!=0);
        }
            
        
                  
            static String [][] mcqs = new String[5][20];
            static char [][] correctAns = new char[5][20];
            
            
             public static void runQuiz(int subject) {
              
                int score = 0 ;

                for (int i = 0; i < 20; i++) {
                    System.out.println(mcqs[subject][i]);
                    System.out.print("Your answer: ");
                    char answer = sc.next().toUpperCase().charAt(0);

                    if (answer == correctAns[subject][i]) {
                        score++;
                        System.out.println("Correct!");
                    } else {
                        System.out.println("Wrong! Correct answer is: " + correctAns[subject][i]);
                    }
                    System.out.println();
                }

                System.out.println("Final Score: " + score + "/20");
            }

      
         //instructions
         public static void instructions(){
          System.out.println("======================================");
          System.out.println("          WELCOME TO THE QUIZ        ");
          System.out.println("====================================");
          System.out.println("INSTRUCTIONS:");
          System.out.println("1. 5 subjects: Maths, Physics, Chemistry, Biology, English");
          System.out.println("2. 20 questions each");
          System.out.println("3. Options: A, B, C, D");
          System.out.println("4. Each correct answer = 1 point. No negative marks");
          System.out.println("====================================\n");
         }

         public static void mathsQuiz(){
          instructions();

            mcqs[0][0]  = "Q1: The derivative of sin x is:\nA) cos x\nB) -sin x\nC) -cos x\nD) sec x";
            mcqs[0][1]  = "Q2: The solution of 2x-6=10 is:\nA) 4\nB) 6\nC) 8\nD) 2";
            mcqs[0][2]  = "Q3: The limit of (x²–4)/(x–2) as x→2 is:\nA) 0\nB) 2\nC) 4\nD) 6";
            mcqs[0][3]  = "Q4: ∫ x dx =\nA) x²/2 + C\nB) 2x + C\nC) x² + C\nD) ln x + C";
            mcqs[0][4]  = "Q5: A matrix with equal rows and columns is called:\nA) Row matrix\nB) Square matrix\nC) Null matrix\nD) Diagonal matrix";
            mcqs[0][5]  = "Q6: log₁₀100 =\nA) 1\nB) 2\nC) 3\nD) 10";
            mcqs[0][6]  = "Q7: If A = {1,2,3}, n(A) =\nA) 2\nB) 3\nC) 6\nD) 1";
            mcqs[0][7]  = "Q8: Quadratic formula is:\nA) (–b±√b)/2a\nB) (b²–4ac)/2\nC) (–b±√(b²–4ac))/2a\nD) None";
            mcqs[0][8]  = "Q9: cos²θ + sin²θ =\nA) 0\nB) 1\nC) 2\nD) 0";
            mcqs[0][9]  = "Q10: Slope of y = mx + c is:\nA) c\nB) m\nC) x\nD) y";
            mcqs[0][10] = "Q11: If f(x)=3x+2, f(2)=\nA) 8\nB) 6\nC) 4\nD) 5";
            mcqs[0][11] = "Q12: Inverse of sin is:\nA) arcsin\nB) sin⁻²\nC) sin°\nD) sin⁻¹";
            mcqs[0][12] = "Q13: 1! equals:\nA) 0\nB) 1\nC) 2\nD) undefined";
            mcqs[0][13] = "Q14: Integral of 1/x is:\nA) x\nB) 1/x\nC) ln|x|+C\nD) x⁻²";
            mcqs[0][14] = "Q15: y = x³ is:\nA) Linear\nB) Quadratic\nC) Cubic\nD) Constant";
            mcqs[0][15] = "Q16: tanθ =\nA) base/perpendicular\nB) perpendicular/base\nC) hyp/base\nD) base/hyp";
            mcqs[0][16] = "Q17: Determinant of |1 0; 0 1| is:\nA) 1\nB) 0\nC) -1\nD) 2";
            mcqs[0][17] = "Q18: Domain of √x is:\nA) All real numbers\nB) x>0\nC) x≥0\nD) x<0";
            mcqs[0][18] = "Q19: sin 90° =\nA) 0\nB) 1\nC) -1\nD) undefined";
            mcqs[0][19] = "Q20: If x ∝ y, then x/y =\nA) 0\nB) constant\nC) variable\nD) infinite";


            //correct answers
            correctAns[0][0]  = 'A';
            correctAns[0][1]  = 'C';
            correctAns[0][2]  = 'C';
            correctAns[0][3]  = 'A';
            correctAns[0][4]  = 'B';
            correctAns[0][5]  = 'B';
            correctAns[0][6]  = 'B';
            correctAns[0][7]  = 'C';
            correctAns[0][8]  = 'B';
            correctAns[0][9]  = 'B';
            correctAns[0][10] = 'A';
            correctAns[0][11] = 'D';
            correctAns[0][12] = 'B';
            correctAns[0][13] = 'C';
            correctAns[0][14] = 'C';
            correctAns[0][15] = 'B';
            correctAns[0][16] = 'B';
            correctAns[0][17] = 'C';
            correctAns[0][18] = 'B';
            correctAns[0][19] = 'B';
            
            runQuiz(0);
         }

          public static void physicsQuiz(){
            instructions(); 

            mcqs[1][0]  = "Q1: SI unit of force:\nA) Joule \nB) Watt \nC) Newton \nD) Pascal ";
            mcqs[1][1]  = "Q2: Acceleration:\nA) v/t\nB) Δv/Δt \nC) d/t \nD) t/v";
            mcqs[1][2]  = "Q3: Work is defined as:\nA) Fxd \nB) mxa\nC) mxv\nD) axt";
            mcqs[1][3]  = "Q4: 1kWh is the unit of: \nA) power\nB) Energy\nC) Force\nD) Pressure";
            mcqs[1][4]  = "Q5: Speed of light in vaccum is:\nA) 3×10⁸ m/s  \nB) 3×10² m/s \nC) 3×10⁶ m/s \nD) 3×10⁹ m/s ";
            mcqs[1][5]  = "Q6: Density=\nA) mxv\nB) m/v\nC) v/m\nD) mxa";
            mcqs[1][6]  = "Q7: The slope of velocity time graph gives: \nA) distance\nB) acceleration\nC) displacement \nD) velocity";
            mcqs[1][7]  = "Q8: Ohm's law: V= \nA) IR \nB) I/R\nC) R/I\nD) VI";
            mcqs[1][8]  = "Q9: Gravitional acceleration on Earth is: \nA) 5 m/s² \nB) 9.8 m/s² \nC) 15 m/s²\nD) 20 m/s²";
            mcqs[1][9]  = "Q10: The unit of power is: \nA) Joule \nB) Watt \nC) Newton \nD) Volt";
            mcqs[1][10] = "Q11: Convex lens is: \nA) Diverging \nB) Converging \nC) Flat \nD) None";
            mcqs[1][11] = "Q12: Momentum = \nA) mv\nB) m/v \nC) m+a\nD) v/a";
            mcqs[1][12] = "Q13: Heat transfer without medium: \nA) Conduction \nB) Convection \nC) Radiation \nD) Circulation";
            mcqs[1][13] = "Q14: Frequency unit: \nA) Hz \nB) N \nC) W \nD) C";
            mcqs[1][14] = "Q15: Charge of electron is: \nA) +e \nB) -e \nC) 0 \nD) 1C";
            mcqs[1][15] = "Q16: Unit of Pressure is: \nA) Watt \nB) Pascal \nC) Volt \nD) Henry";
            mcqs[1][16] = "Q17: 1 Ampere= \nA) 1C/s \nB) 1J/s \nC) 1N/s \nD) 1W/m";
            mcqs[1][17] = "Q18: Velocity is a:\nA) Scalar \nB) Vector \nC) Both \nD) None";
            mcqs[1][18] = "Q19: Specific heat is measured in: \nA) J/kg.K\nB) W/kg\nC) N/m\nD) J/m²";
            mcqs[1][19] = "Q20: Escape velocity on Earth= \nA) 2km/s \nB) 5km/s \nC) 11.2km/s \nD) 20km/s";

            correctAns[1][0] = 'C';
            correctAns[1][1] = 'B';
            correctAns[1][2] = 'A';
            correctAns[1][3] = 'B';
            correctAns[1][4] = 'A';
            correctAns[1][5] = 'B';
            correctAns[1][6] = 'B';
            correctAns[1][7] = 'A';
            correctAns[1][8] = 'B';
            correctAns[1][9] = 'B';
            correctAns[1][10] = 'B';
            correctAns[1][11] = 'A';
            correctAns[1][12] = 'C';
            correctAns[1][13] = 'A';
            correctAns[1][14] = 'B';
            correctAns[1][15] = 'B';
            correctAns[1][16] = 'A';
            correctAns[1][17] = 'B';
            correctAns[1][18] = 'A';
            correctAns[1][19] = 'C';

           runQuiz(1);
          }           

          public static void chemistryQuiz(){
            instructions();
 
            mcqs[2][0]  = "Q1: Atomic number equals number of:\nA) neutrons B) protons C) electrons D) ions";
            mcqs[2][1]  = "Q2: Avogadro’s number is:\nA) 6.02×10⁵ B) 6.02×10²³ C) 3.14 D) 1.66×10⁻²⁴";
            mcqs[2][2]  = "Q3: pH of neutral solution:\nA) 0 B) 5 C) 7 D) 14";
            mcqs[2][3]  = "Q4: HCl is a:\nA) base B) acid C) salt D) buffer";
            mcqs[2][4]  = "Q5: Noble gases are present in group:\nA) 1 B) 2 C) 7 D) 18";
            mcqs[2][5]  = "Q6: Isotopes have same:\nA) mass number B) atomic number C) neutrons D) none";
            mcqs[2][6]  = "Q7: NaCl is bonded by:\nA) Covalent bond B) Ionic bond C) Metallic bond D) Hydrogen bond";
            mcqs[2][7]  = "Q8: Molar mass of water:\nA) 18 g/mol B) 10 C) 20 D) 36";
            mcqs[2][8]  = "Q9: Gas law: PV =\nA) kn B) RT C) nRT D) mRT";
            mcqs[2][9]  = "Q10: Electrolysis is:\nA) chemical → electricity B) electricity → chemical C) neutralization D) redox";
            mcqs[2][10] = "Q11: Oxidation involves:\nA) gaining electrons B) losing electrons C) gaining protons D) losing neutrons";
            mcqs[2][11] = "Q12: Water is:\nA) organic B) inorganic C) metal D) salt";
            mcqs[2][12] = "Q13: Catalyst:\nA) increases rate B) decreases rate C) no effect D) stops reaction";
            mcqs[2][13] = "Q14: pH <7 indicates:\nA) base B) acid C) neutral D) salt";
            mcqs[2][14] = "Q15: CO₂ is:\nA) acidic oxide B) basic C) neutral D) amphoteric";
            mcqs[2][15] = "Q16: Hydrogen has:\nA) 0 electrons B) 1 C) 2 D) 3";
            mcqs[2][16] = "Q17: Organic chemistry is study of:\nA) metals B) salts C) carbon compounds D) water";
            mcqs[2][17] = "Q18: CH₄ is:\nA) Alkane B) Alkene C) Alkyne D) Acid";
            mcqs[2][18] = "Q19: H₂O₂ is:\nA) oxidizing agent B) reducing agent C) both D) none";
            mcqs[2][19] = "Q20: Strongest bond:\nA) Hydrogen B) Ionic C) Covalent D) Metallic";

            // --- Correct Answers ---
            correctAns[2][0]  = 'B';
            correctAns[2][1]  = 'B';
            correctAns[2][2]  = 'C';
            correctAns[2][3]  = 'B';
            correctAns[2][4]  = 'D';
            correctAns[2][5]  = 'B';
            correctAns[2][6]  = 'B';
            correctAns[2][7]  = 'A';
            correctAns[2][8]  = 'C';
            correctAns[2][9]  = 'B';
            correctAns[2][10] = 'B';
            correctAns[2][11] = 'B';
            correctAns[2][12] = 'A';
            correctAns[2][13] = 'B';
            correctAns[2][14] = 'A';
            correctAns[2][15] = 'B';
            correctAns[2][16] = 'C';
            correctAns[2][17] = 'A';
            correctAns[2][18] = 'C';
            correctAns[2][19] = 'C';

            runQuiz(2);
          }

            public static void biologyQuiz(){
              instructions();

              mcqs[3][0]  = "Q1: Cell is discovered by:\nA) Darwin B) Hooke C) Watson D) Pasteur";
              mcqs[3][1]  = "Q2: Mitochondria are sites of:\nA) photosynthesis B) respiration C) digestion D) excretion";
              mcqs[3][2]  = "Q3: Basic unit of life:\nA) tissue B) cell C) organ D) molecule";
              mcqs[3][3]  = "Q4: DNA stands for:\nA) Deoxyribonucleic acid B) Dioxyriboacid C) Dextrose nucleic acid D) None";
              mcqs[3][4]  = "Q5: Ribosomes make:\nA) fats B) proteins C) carbohydrates D) vitamins";
              mcqs[3][5]  = "Q6: Photosynthesis occurs in:\nA) mitochondria B) chloroplast C) ribosome D) Golgi";
              mcqs[3][6]  = "Q7: Heart has ____ chambers.\nA) 2 B) 3 C) 4 D) 5";
              mcqs[3][7]  = "Q8: Enzymes are:\nA) carbohydrates B) proteins C) fats D) lipids";
              mcqs[3][8]  = "Q9: Plasma is:\nA) RBCs B) WBCs C) fluid part of blood D) platelets";
              mcqs[3][9]  = "Q10: Insulin is produced by:\nA) liver B) pancreas C) stomach D) kidney";
              mcqs[3][10] = "Q11: Hemoglobin carries:\nA) nitrogen B) oxygen C) glucose D) amino acids";
              mcqs[3][11] = "Q12: Which is hereditary material?\nA) DNA B) RNA C) Protein D) Fat";
              mcqs[3][12] = "Q13: Neuron is a:\nA) muscle cell B) kidney cell C) nerve cell D) blood cell";
              mcqs[3][13] = "Q14: Human kidney filters:\nA) blood B) oxygen C) fats D) proteins";
              mcqs[3][14] = "Q15: Protein digestion starts in:\nA) mouth B) stomach C) liver D) colon";
              mcqs[3][15] = "Q16: Chlorophyll is:\nA) green pigment B) red pigment C) blue D) yellow";
              mcqs[3][16] = "Q17: Diabetes is caused by lack of:\nA) insulin B) bile C) urea D) RBC";
              mcqs[3][17] = "Q18: Largest organ:\nA) heart B) liver C) skin D) brain";
              mcqs[3][18] = "Q19: Blood pH is about:\nA) 5 B) 7.4 C) 9 D) 6";
              mcqs[3][19] = "Q20: Human body temperature:\nA) 20°C B) 30°C C) 37°C D) 42°C";

              // --- Correct Answers ---
              correctAns[3][0]  = 'B';
              correctAns[3][1]  = 'B';
              correctAns[3][2]  = 'B';
              correctAns[3][3]  = 'A';
              correctAns[3][4]  = 'B';
              correctAns[3][5]  = 'B';
              correctAns[3][6]  = 'C';
              correctAns[3][7]  = 'B';
              correctAns[3][8]  = 'C';
              correctAns[3][9]  = 'B';
              correctAns[3][10] = 'B';
              correctAns[3][11] = 'A';
              correctAns[3][12] = 'C';
              correctAns[3][13] = 'A';
              correctAns[3][14] = 'B';
              correctAns[3][15] = 'A';
              correctAns[3][16] = 'A';
              correctAns[3][17] = 'C';
              correctAns[3][18] = 'B';
              correctAns[3][19] = 'C';
            
             runQuiz(3);
          }
          
          public static void englishQuiz(){
            instructions();

            mcqs[4][0]  = "Q1: Synonym of 'rapid':\nA) slow B) quick C) dull D) late";
            mcqs[4][1]  = "Q2: Antonym of 'brave':\nA) coward B) bold C) strong D) angry";
            mcqs[4][2]  = "Q3: Choose the correct spelling:\nA) Acomodate B) Accommodate C) Accomodate D) Acommmodate";
            mcqs[4][3]  = "Q4: She ___ going to school.\nA) is B) are C) were D) be";
            mcqs[4][4]  = "Q5: Past tense of 'run':\nA) runned B) running C) ran D) run";
            mcqs[4][5]  = "Q6: 'He plays well.' — Identify the adverb.\nA) He B) plays C) well D) none";
            mcqs[4][6]  = "Q7: Passive of 'He wrote a letter':\nA) Letter is written B) Letter wrote him C) A letter is writing D) A letter was written";
            mcqs[4][7]  = "Q8: Plural of 'child':\nA) childs B) children C) childes D) childs’";
            mcqs[4][8]  = "Q9: Correct preposition: 'She is good ___ math.'\nA) in B) at C) on D) of";
            mcqs[4][9]  = "Q10: Choose the correct article: '___ apple a day keeps the doctor away.'\nA) A B) An C) The D) No article";
            mcqs[4][10] = "Q11: Which is a noun?\nA) quickly B) happiness C) run D) go";
            mcqs[4][11] = "Q12: 'They will eat' → tense?\nA) Present B) Past C) Future D) Continuous";
            mcqs[4][12] = "Q13: Synonym of 'angry':\nA) furious B) soft C) calm D) weak";
            mcqs[4][13] = "Q14: Correct sentence:\nA) He go to school B) He goes to school C) He going school D) He is go school";
            mcqs[4][14] = "Q15: Opposite of 'big':\nA) tall B) short C) small D) heavy";
            mcqs[4][15] = "Q16: 'Wow!' is a/an:\nA) adjective B) verb C) interjection D) noun";
            mcqs[4][16] = "Q17: Choose correct:\nA) It are raining B) It is raining C) It raining D) Raining it is";
            mcqs[4][17] = "Q18: 'Eat' → noun:\nA) eater B) eating C) ate D) edible";
            mcqs[4][18] = "Q19: Indirect of 'He said, ‘I am happy’':\nA) He said he was happy B) He said that I am happy C) He said he is happy D) He said I was happy";
            mcqs[4][19] = "Q20: Choose correct:\nA) She didn’t came\nB) She didn’t come\nC) She don’t come\nD) She not come";

            correctAns[4][0]  = 'B';
            correctAns[4][1]  = 'A';
            correctAns[4][2]  = 'B';
            correctAns[4][3]  = 'A';
            correctAns[4][4]  = 'C';
            correctAns[4][5]  = 'C';
            correctAns[4][6]  = 'D';
            correctAns[4][7]  = 'B';
            correctAns[4][8]  = 'B';
            correctAns[4][9]  = 'B';
            correctAns[4][10] = 'B';
            correctAns[4][11] = 'C';
            correctAns[4][12] = 'A';
            correctAns[4][13] = 'B';
            correctAns[4][14] = 'C';
            correctAns[4][15] = 'C';
            correctAns[4][16] = 'B';
            correctAns[4][17] = 'B';
            correctAns[4][18] = 'A';
            correctAns[4][19] = 'B';
             
            runQuiz(4);
             }
             public static void viewScores() {}
             public static void viewLeaderboard() {}
          

    }

