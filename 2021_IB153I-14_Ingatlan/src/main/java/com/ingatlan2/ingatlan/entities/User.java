package com.ingatlan2.ingatlan.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public  class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique=true, nullable = false)
    private Long userid;
    @Column(nullable = false)
    private String fullname;
    @Column( nullable = false)
    private String email;

    private String password;

    @Column(nullable = false)
    private String phonenumber;
    @Column(nullable = false)
    private String birthdate;

    private String activation;

    private Boolean enabled;

    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name="user_id")}, inverseJoinColumns = {@JoinColumn(name="roles_id")})
    private Set<Role> roles = new HashSet<Role>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }




    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinTable(name = "favourites",
            joinColumns = {@JoinColumn(name="user_id")}, inverseJoinColumns = {@JoinColumn(name="real_estate_id")})
    private Set<Advert> real_estate = new HashSet<>();

    public Set<Advert> getAdverts() {
        return real_estate;
    }

    public void setAdverts(Set<Advert> real_estate) {
        this.real_estate = real_estate;
    }




    public User(){}

    public User(long userid, String fullname, String email, String password, String phonenumber, String birthdate) {



        this.userid = userid;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
        this.birthdate = birthdate;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userId) {
        this.userid = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwordHash) {
        this.password = passwordHash;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthDate) {
        this.birthdate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userid +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + password + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", birthDate=" + birthdate +
                '}';
    }


    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public void addRoles(String roleName) {
        if (this.roles == null || this.roles.isEmpty())
            this.roles = new HashSet<>();
        this.roles.add(new Role(roleName));
    }

}
