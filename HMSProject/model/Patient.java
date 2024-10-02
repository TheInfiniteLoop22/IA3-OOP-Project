package model;

public class Patient {
    private String name;
    private int age; 
    private String contactNumber;
    private String email;
    private String registrationId; 

    public Patient(String name, int age, String contactNumber, String email, String registrationId) {
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
        this.email = email;
        this.registrationId = registrationId;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getContactNumber() { return contactNumber; }
    public String getEmail() { return email; }
    public String getRegistrationId() { return registrationId; }
}

