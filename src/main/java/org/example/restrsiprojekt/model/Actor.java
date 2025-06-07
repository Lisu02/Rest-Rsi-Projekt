package org.example.restrsiprojekt.model;

import java.time.LocalDate;

import static org.example.restrsiprojekt.util.GlobalUtilities.random;


public class Actor {

    private Long id;
    private String firstName;
    private String lastName;
    //@XmlSchemaType(name = "dateTime")
    private  String birthDay;
    private Country countryOfOrigin;

    public Actor(){
        this("testName","testLastname",LocalDate.now().toString(),Country.values()[random.nextInt(0,7)]);
    }

    public Actor(String firstName, String lastName, String birthDay ,Country country){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.countryOfOrigin = country;
    }

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getBirthDay() {return birthDay;}
    public void setBirthDay(String birthDay) {this.birthDay = birthDay;}

    public Country getCountryOfOrigin() {return countryOfOrigin;}
    public void setCountryOfOrigin(Country countryOfOrigin) {this.countryOfOrigin = countryOfOrigin;}
}
