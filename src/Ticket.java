import java.io.FileWriter;
import java.io.IOException;
public class Ticket{
    //field to store ticket information
    private String row;
    private int seat;
    private Person person;

    //constructor to initialize a Ticket object with row,seat and person
    public Ticket(String row,int seat,Person person){
        this.row=row;
        this.seat=seat;
        this.person=person;
    }
    //getters and setters
    public String getRow(){
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }
    public void setSeat(int seat){
        this.seat=seat;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person=person;
    }
    //printing tickets info
    public void printTicketInfo(){
        System.out.println("Row :" + row);
        System.out.println("Seat :" + seat);
        System.out.println("Ticket holder information :");
        person.printPersonInfo();
    }
    //ticket pricing
    public int getPrice(){
        if(row.equals("A") || row.equals("D")) {
            if (seat >= 1 && seat <= 5) {
                return 200; //price for row A and D seats 1-5
            } else if (seat >= 6 && seat <= 9) {
                return 150; //price for row A and D seats 6-9
            } else {
                return 180;//price for row A and D seats 10-14
            }
        }else{
            if(seat>=1 && seat<=9){
                return 180; //price for row B and C seats 1-9
            }else{
                return 150;//price for row B and C seats 10-12
            }
        }
    }



    //ticket info save to file
    public void saveTicketInfoToFile(){
        String fileName=row+seat+".txt"; //making file name
        try{
            FileWriter writer=new FileWriter(fileName);
            //writing ticket information to the file
            writer.write("Ticket Information :\n");
            writer.write("Row : " + row + "\n");
            writer.write("Seat : " + seat +"\n");
            writer.write("Ticket Holder Information : " + "\n");
            writer.write("Name :" + person.getName() +"\n");
            writer.write("Surname :" + person.getSurname() +"\n");
            writer.write("Email :" + person.getEmail() +"\n");
            writer.close();
            System.out.println("Ticket information saved to file :" + fileName);
        }catch (IOException e){
            System.out.println("An error occured while saving ticket information to file. " + e.getMessage());
        }
    }
}