package com.example.technicalTest;

public class licenseClass {
    private String fullName;
    private String softwareName;
    private String secret;
    
    public String getFullName(){
        return this.fullName;
    }
    public String getSoftwareName(){
        return this.softwareName;
    }
    public String getSecret(){
        return this.secret;
    }

    public void setSecret(String secret){
       this.secret = secret;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public void setSoftwareName(String softwareName){
        this.softwareName = softwareName;
    }

    


    
}
