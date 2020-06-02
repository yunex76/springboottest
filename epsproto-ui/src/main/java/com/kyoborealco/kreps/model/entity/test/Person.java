package com.kyoborealco.kreps.model.entity.test;

public class Person {

    private static String PERSON_IMAGE = "<img th:src=\"@{/images/personimage.png}\" alt=\"icon\"/>";
    private static String PERSON_IMAGE_2 = "<img th:src=\"@{/images/personimage2.png}\" alt=\"icon\"/>";

    private String name;
    private String socialSecurity;
    private String birthdate;
    private String gender;
    
    @SuppressWarnings("unused")
	private String personImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(String socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPersonImage() {
        if(null != birthdate) {
            return PERSON_IMAGE;
        } else {
            return PERSON_IMAGE_2;
        }

    }
    
    public Person(String name, String socialSecurity, String birthdate, String gender) {

    	this.name = name;
    	this.socialSecurity = socialSecurity;
    	this.birthdate = birthdate;
    	this.gender = gender;
    }
    
}
