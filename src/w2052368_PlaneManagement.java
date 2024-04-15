import java.util.Scanner;
public class w2052368_PlaneManagement {

    final static boolean[] seats=new boolean[56];  //static boolean array size of 56
    final static Scanner scanner=new Scanner(System.in);
    final static Ticket[]tickets=new Ticket[56]; //static array of Ticket objects named "tickets" with size of 56

    //main method
    public static void main(String args[]) {
        seatsInitializer();
        displayMenu();

    }

    //method to initialize all seats are false,indicating they are not booked
    private static void seatsInitializer() {
        for (int i = 0; i < seats.length; i++) {
            seats[i] = false;
        }
    }

    //method to display the main menu and handle user input
    private static void displayMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Plane Management Application!");
        System.out.println("*******************************************************************");
        System.out.println("*                             MENU OPTIONS                        *");
        System.out.println("*******************************************************************");

        //infinite loop for until choose to quit
        while (true) {
            System.out.println("1:Buy a seat");
            System.out.println("2:Cancel a seat");
            System.out.println("3:Find first available seat");
            System.out.println("4:Show seating plan");
            System.out.println("5:Print tickets information and total sales");
            System.out.println("6:Search ticket");
            System.out.println("0:Quit");
            System.out.print("Choose an option :");
            int option = input.nextInt(); //read users choice


            if (option == 0) {
                System.out.println("Exiting the program.Goodbye!");
                break;
            }
            switch (option) {
                case 1:
                    buy_seat();
                    break;

                case 2:
                    cancel_seat();
                    break;

                case 3:
                    find_first_seat_available();
                    break;

                case 4:
                    show_seating_plan();
                    break;

                case 5:
                    print_tickets_information_and_total_sales();
                    break;

                case 6:
                    search_ticket();
                    break;


                default:
                    System.out.println("Error:Invalid selection.");
                    break;

            }
        }
    }




    //method to handle buying seat
    private static void buy_seat() {

        Scanner input=new Scanner(System.in);  //final eke uda dala nisa aye one ne
        System.out.print("Enter a row letter (A/B/C/D):");
        String rowLetter = input.nextLine().toUpperCase();

        //validate row letter entered by the user
        while(!(rowLetter.equals("A")||rowLetter.equals("B") || rowLetter.equals("C")||rowLetter.equals("D"))){
            System.out.print("Invalid row letter.Please enter A,B,C or D : ");

            rowLetter=input.nextLine().toUpperCase();
        }
        int maxSeatsPerRow;
        if(rowLetter.equals("A")||rowLetter.equals("D")){
            maxSeatsPerRow=14;
        }else{
            maxSeatsPerRow=12;
        }
        System.out.print("Enter seat number :");

        //validate the seat number entered by the user
        while (!input.hasNextInt()){
            System.out.print("Invalid seat number.Please enter a valid number : ");
            input.next();
        }

        //read the seat number entered by the user
        int seat_num = input.nextInt();
        if (seat_num<1||seat_num>maxSeatsPerRow){
            System.out.println("Invalid seat number.Please enter a seat number between 1 and " +maxSeatsPerRow +".");
            return;
        }
        int rowNumber;

        //determine the row number based on the row letter entered by the user
        if(rowLetter.equalsIgnoreCase("a")){
            rowNumber=1;
        }else if(rowLetter.equalsIgnoreCase("b")){
            rowNumber=2;

        }else if(rowLetter.equalsIgnoreCase("c")){
            rowNumber=3;
        }else{
            rowNumber=4;
        }

        //calculate the index of the seat in the seats array
        int index=((rowNumber-1)*14 + seat_num)-1;

        //check if the seat is available
        if(index==26 || index==27 || index==40 || index==41){
            System.out.println("Seat is not available at this location. Please choose another seat.");
        }else{
            if(seats[index]){
                System.out.println("Seat is already booked.");
            }else{
                seats[index]=true; //mark the seat as booked
                System.out.println("Seat booked successfully.");

                System.out.print("Enter your name :");
                String name=input.next();
                System.out.print("Enter your surname :");
                String sName=input.next();
                System.out.print("Enter your email :");
                String email=input.next();

                //create a person object with the provided information
                Person person=new Person(name,sName,email);

                //create a Ticket object for the booked seat
                Ticket ticket=new Ticket(rowLetter,seat_num,person);

                //store the ticket in the tickets array
                tickets[index]=ticket;

                ticket.saveTicketInfoToFile();  //save ticket information to file


//buy seat tikak wenas kra
            }
        }



    }

    //method to handle cancelling seats
    private static void cancel_seat(){
        Scanner input=new Scanner(System.in);
        System.out.print("Enter a row letter (A/B/C/D):");
        String rowLetter = input.nextLine().toUpperCase();

        //validate the row letter input
        while(!(rowLetter.equals("A")||rowLetter.equals("B") || rowLetter.equals("C")||rowLetter.equals("D"))){
            System.out.println("Invalid row letter.Please enter A,B,C or D :");

            rowLetter=input.nextLine().toUpperCase();
        }
        System.out.print("Enter seat number :");

        //validate the seat number input
        while (!input.hasNextInt()){
            System.out.println("Invalid seat number. Please enter a number.");

            input.next();
        }

        //read user info for seat number
        int seat_num = input.nextInt();

        //calculate index in the seats array based on row letter and seat number
        int rowNumber;

        if(rowLetter.equalsIgnoreCase("a")){
            rowNumber=1;
        }else if(rowLetter.equalsIgnoreCase("b")){
            rowNumber=2;

        }else if(rowLetter.equalsIgnoreCase("c")){
            rowNumber=3;
        }else{
            rowNumber=4;
        }
        int index=((rowNumber-1)*14 + seat_num)-1;

        //check if the seat is already available
        if(!seats[index]){
            System.out.println("This seat is already available.");
        }else{
            //mark the seat is available(canceled)
            seats[index]=false;
            System.out.println("successfully canceled the seat.");
        }


    }

    //method to go back to the main menu
    private static void Homepage(){
        Scanner input=new Scanner(System.in);
        System.out.println("Press Y to go homepage");
        String homePage=input.nextLine();

        //if "y" is pressed,go back to the main menu
        if(homePage.equalsIgnoreCase("y")){
            displayMenu();

        }else{
            return;
        }

    }

    //method to find the first seat available
    private static void find_first_seat_available(){
        int index;
        for(int i=0; i< seats.length;i++){
            if(!seats[i]){
                index=i;
                System.out.println("First available seat is " + index);
                break;
            }
        }

    }
    private static void show_seating_plan() {
        System.out.println("*******************************************************************");
        System.out.println("*                             SEATING PLAN                        *");
        System.out.println("*******************************************************************");

        System.out.println("(O=Available| X=Booked)\n");

        int seatsPerRow=14; //constants

        //loop through seats array to display the seating plan
        for (int i=0;i< seats.length;i++){
            //skip seats that are not available
            if(!(i==26||i==27 ||i==40 || i==41)){
                System.out.print(seats[i] ? "X" : "O");
            }
            //add new line after each row
            if ((i+1)%seatsPerRow==0){
                System.out.println();
            }else{
                System.out.print(""); //add space between seats
            }
        }
        System.out.println("");


    }

    private static void print_tickets_information_and_total_sales() {

        int totalSales=0; //initialize total sales
        System.out.println("*******************************************************************");
        System.out.println("*                TICKET INFORMATION AND TOTAL SALES               *");
        System.out.println("*******************************************************************");
        for (Ticket ticket :tickets){
            if (ticket !=null){
                ticket.printTicketInfo(); //print ticket information
                totalSales+=ticket.getPrice(); //update total sales
            }
        }
        System.out.println("Total sales :€ " + totalSales);
    }

    private static void search_ticket() {

        Scanner input=new Scanner(System.in);
        System.out.print("Enter a row letter (A/B/C/D):");
        String rowLetter = input.nextLine().toUpperCase();
        System.out.print("Enter seat number :");
        int seat_num = input.nextInt();
        int rowNumber;

        if(rowLetter.equalsIgnoreCase("a")){
            rowNumber=1;
        }else if(rowLetter.equalsIgnoreCase("b")){
            rowNumber=2;

        }else if(rowLetter.equalsIgnoreCase("c")){
            rowNumber=3;
        }else{
            rowNumber=4;
        }
        int index=((rowNumber-1)*14 + seat_num)-1;

        //check if a ticket exists for the provided seat
        if(tickets[index]!=null){
            System.out.println("Ticket information");
            tickets[index].printTicketInfo();
        }else{
            System.out.println("This seat is available.");
        }


    }
}
