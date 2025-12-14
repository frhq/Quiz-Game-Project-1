import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.InputMismatchException;
import java.io.FileNotFoundException;
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

            System.out.println("Question added successfully to " + fileName + "!");


        } catch (IOException e) {
            System.out.println("There is some error!!!");
        }catch(Exception e){
            System.out.println("Unexpected error.");
        }
    }

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
            System.out.println("Enter your name: ");
            String playerName = sc.nextLine();
   
            System.out.println("\nSelect Subject: ");
            System.out.println("1. Biology"); 
            System.out.println("2. Chemistry");
            System.out.println("3. Physics");
            System.out.println("4. English");
            System.out.println("5. Logical Reasoning");
            System.out.println("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            String filename = "";
            String subjectName = " " ;
           
            switch(choice){
              case 1 : filename = "Biology.txt" ; subjectName  = "Biology" ;break;
              case 2 : filename = "Chemistry.txt"; subjectName = "Chemistry" ; break ;
              case 3 : filename = "Physics.txt" ; subjectName = "Physics" ; break;
              case 4 : filename = "English.txt" ; subjectName = "English"; break;
              case 5 : filename = "LogicalReasoning.txt"; subjectName = "Logical Reasoning" ; break;
              default: System.out.println("Invalid subject!");
              return;
            }

            int score = 0;
            int total = 20;

            try {
              BufferedReader br = new BufferedReader(new FileReader(filename));
              String line;
              int qNo = 1;

              while((line = br.readLine()) != null && qNo <= 20) {
                String [] q = line.split(",");
        
                System.out.println("\nQ:" + qNo + "." + q[0]);
                System.out.println("A. " + q[1]);
                System.out.println("B. " + q[2]);
                System.out.println("C. " + q[3]);
                System.out.println("D. " + q[4]);

                System.out.print("Your answer: ");
                String userAns = sc.nextLine().toUpperCase();

                if(userAns.equals(q[5])){
                  score++;
                }
                qNo++;
              }
              br.close();
            }catch(IOException e){
              System.out.println("Error reading questions!");
              return;
            }

            double percentage = ((double)score / total) * 100;
            double gpa; 
            String grade;
            String status;

            if (percentage >= 85){
              gpa = 4.0;
              grade = "A";
              status = "PASS";
            }else if(percentage >= 70){
              gpa = 3.0;
              grade = "B";
              status = "PASS";
            }else if(percentage >= 50){
              gpa = 2.0;
              grade = "C";
              status = "PASS";
            }else{
              gpa = 0.0;
              grade = "F";
              status = "FAIL";
            }

            //DISPLAY RESULT
            System.out.println("\n=====RESULT=====");
            System.out.println("Player: " + playerName);
            System.out.println("Subject: " + subjectName);
            System.out.println("Score:" + score + "/" + total);
            System.out.println("Percentage: " + percentage + "%");
            System.out.println("GPA: " + gpa);
            System.out.println("Grade: " + grade);
            System.out.println("Status: " + status);

            try{
              FileWriter fw = new FileWriter("Marks.txt", true);
              fw.write("Player: " + playerName + " | Subject: " + subjectName + " | Obtained: "
                 + score + " | Total: " + total + " | Grade: " + grade + " | GPA: " + gpa + "\n");
              fw.close();
            }catch(IOException e){
              System.out.println("Error saving marks!");
            }
          }
          
          public static void viewScores(){
            System.out.println("======ALL PLAYERS SCORES======");

            try{
              BufferedReader br = new BufferedReader(new FileReader("Marks.txt"));
              String line;

              if((line = br.readLine()) == null){
                System.out.println("No scores available yet!");
              }else{
                System.out.println(line);
                while((line = br.readLine()) != null){
                    System.out.println(line);
                }
              }
              br.close();
            }catch(FileNotFoundException e){
              System.out.println("Marks.txt file not found!");
            }catch(IOException e){
              System.out.println("Error reading scores file!");
            }
          }
          public static void viewLeaderboard() {
            System.out.println("===== Leaderboard =====");

            String[] lines = new String[100];
            int[] scores = new int[100];
            int count = 0;

            try {
                BufferedReader br = new BufferedReader(new FileReader("Marks.txt"));
                String line;
                while ((line = br.readLine()) != null && count < 100) {
                    lines[count] = line;
                    
                    String[] parts = line.split("Obtained:");
                    int score = Integer.parseInt(parts[1].split(",")[0].trim());
                    scores[count] = score;
                    
                    count++;
                }
                br.close();

                if (count == 0) {
                    System.out.println("No scores available yet!");
                    return;
                }

                for (int i = 0; i < count - 1; i++) {
                    for (int j = 0; j < count - i - 1; j++) {
                        if (scores[j] < scores[j + 1]) {

                           int tempScore = scores[j];
                            scores[j] = scores[j + 1];
                            scores[j + 1] = tempScore;
                            
                            String tempLine = lines[j];
                            lines[j] = lines[j + 1];
                            lines[j + 1] = tempLine;
                        }
                    }
                }

                for (int i = 0; i < count; i++) {
                    System.out.println(lines[i]);
                }

            } catch (FileNotFoundException e) {
                System.out.println("Marks.txt file not found!");
            } catch (IOException e) {
                System.out.println("Error reading scores file!");
            }
        }


          //logout
          public static void Logout(){
            System.out.println("Logged out successfully!");
             
          }
          
        }
            