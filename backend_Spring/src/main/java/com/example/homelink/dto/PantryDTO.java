package com.example.homelink.dto;

public class PantryDTO {
  
  private Long id;
  private String provider;
  private String address;
  private String contactPerson;
  private String email;


  public PantryDTO(Long id, String provider, String address, String contactPerson, String email){
    this.id = id;
    this.provider = provider;
    this.address = address;
    this.contactPerson = contactPerson;
    this.email = email;
  }


  public Long getId(){
    return id;
  }

  public void setId(Long id){
    this.id = id;
  }

   public String getProvider(){
    return provider;
  }

  public void setProvider(String provider){
    this.provider = provider;
  }

  public String getAddress(){
    return address;
  }

  public void setAddress(String address){
    this.address = address;
  }

   public String getContactPerson(){
    return contactPerson;
  }

  public void setContactPerson(String contactPerson){
    this.contactPerson = contactPerson;
  }

  public String getEmail(){
    return email;
  }

  public void setEmail(String email){
    this.email = email;
  }
}
