public class Person {
    //fields to store person's information
    private String name;
    private String surname;
    private String email;

    //constructor to initialize a person object with name,surname and email
    public Person(String name,String surname,String email){
        this.name=name;
        this.surname=surname;
        this.email=email;
    }


    //getters and setters
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    //method to print person info
    public void printPersonInfo(){
        System.out.println("Name :" + name);
        System.out.println("Surname :" + surname);
        System.out.println("Email :" + email);
    }

}