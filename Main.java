import java.util.Scanner;
public class Main{
    private static String [] taskTitles = new String[50];
    private static String [] taskDescriptions = new String[50];
    private static String [] taskStatues = new String[50];
    private static String [] taskstudentName = new String[50];
    private static int taskcount = 0;
    private static Scanner scanner = new Scanner(System.in);
    public static void main (String[] args){
        printwelcome();
        boolean running = true;
        while(running){
            displayMenu();
            System.out.print("Enter your choice:");
            String input = scanner.nextLine();
            switch(input){
                case"1":
                  addtask();
                  break;
                case"2":
                   viewTasks();
                   break;
                case "3":
                   searchTasks();
                   break;
                case "4":
                   updatetaskstatus();
                   break;
                case "5":
                   running=false;
                   System.out.println("\n Thank you for using the Task Mangement System");
                   System.out.println("GOODBYE!");
                   break;
                   default:
                       System.out.println("\n Invalid choice! please enter 1,2,3,4 or 5\n");
            }

        }
scanner.close();  
    }
    private static void printwelcome(){
        System.out.println("==========================================");
        System.out.println("Welcome to student Task Progress Mangement");
        System.out.println("==========================================");
        System.out.println();
    }
    private static void displayMenu(){
        System.out.println("===== Task Mangement Menu =======");
        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Search Tasks");
        System.out.println("4. Update task");
        System.out.println("5. Exit");
    }
    private static String addtask(String title, String studendName){
        if(taskcount>=50){
            return"Error: Task storage is full";
        }
        taskTitles[taskcount] = title;
        taskDescriptions[taskcount] = "No description";
        taskStatues[taskcount] = "Pending";
        taskstudentName[taskcount]= studendName;
        taskcount++;
        return "Task added successfully";
    }
    private static String addtask(String title, String descriptions, String studentName){
        if(taskcount>=50){
            return "Error: Task Storage is full";
        }
        taskTitles[taskcount] = title;
        taskDescriptions[taskcount] = "Description";
        taskStatues[taskcount] = "Pending";
        taskstudentName[taskcount] = studentName;
        taskcount++;
        return "Task added Successfully with description";
    }
    private static void addtask(){
        System.out.println("\n=== Add New Task===");
        System.out.println("Enter student name:");
        String studentname = scanner.nextLine();
        System.out.println("Enter Task Title:");
        String tasktitle = scanner.nextLine();
        System.out.println("Enter Description for pass entry to skip:");
        String description = scanner.nextLine();
        String result;
        if(description.isEmpty()){
            result = addtask(tasktitle, studentname);
        }else{
            result = addtask(tasktitle,description,studentname);
        }
        System.out.println("\n"+ result);
        System.out.println();
    }
    private static void viewTasks(){
        System.out.println("\n==== All Task ====");
        if(taskcount==0){
        System.out.println("(NO tasks found, Add some task first\n)");
        }
        for(int i=0;i<taskcount;i++){
            StringBuilder sb = new StringBuilder();
            sb.append(i+1).append(".");
            sb.append("[").append(taskStatues[i]).append("]");
            sb.append("-").append(taskstudentName[i]);
            System.out.println(sb.toString());
        if(taskDescriptions[i].equals("No description")){
            System.out.println("Description!"+ taskDescriptions[i]);
        }
        }
        System.out.println("\n Total Tasks:"+ taskcount +"\n");
    }
    private static void searchTasks(){
        System.out.println("\n==== Search Tasks ====");
        System.out.println("Enter search keywords:");
        String keyword = scanner.nextLine().toLowerCase();
        System.out.println("\n==== Search result for"+ keyword +"====");
        int found = 0;
        for(int i=0; i<taskcount; i++){
            if(taskTitles[i].toLowerCase().contains(keyword)){
                taskDescriptions[i].toLowerCase().contains(keyword);
                taskstudentName[i].toLowerCase().contains(keyword);
                System.out.println((i+1)+"["+ taskStatues[i]+"]" + taskTitles[i] +"-" + taskstudentName[i]);
                found++;
            }
        }
        if(found==0){
            System.out.println("No tasks found matching"+ keyword + " ");
        }else{
            System.out.println("\n found" + found +" task(s)");
        }
        System.out.println();
    }
    private static void updatetaskstatus(){
        System.out.println("\n ==== Update Task Status ===");
        if (taskcount==0){
            System.out.println("No tasks to update \n");
            return;
        }
        viewTasks();
        System.out.println("Enter task number to update: ");
        String input = scanner.nextLine();
        try{
            int taskNum = Integer.parseInt(input);
            if(taskNum<1||taskNum>taskcount){
                System.out.println("Invalid task number.\n");
                return;
            }
            System.out.println("\n Select the new status:");
            System.out.println("1. Pending");
            System.out.println("2. In progress");
            System.out.println("3. Completed");
            System.out.println("Enter choice: ");
            String statusChoice = scanner.nextLine();
            switch(statusChoice){
                case"1":
                  taskStatues[taskNum-1]= "Pending";
                  break;
                case "2":
                  taskStatues[taskNum-1]= "In progress";
                  break;
                case"3":
                  taskStatues[taskNum-1]= "Completed";
                  break;
                default:
                System.out.println("Invalid choice!status not updated.\n");
                return;
            }
            System.out.println("Status updated successfully");
        }catch(NumberFormatException e){
            System.out.println("Please enter a valid number \n");
        }
    }
}
    
