package sqrt4.mijninzet.model;

import sqrt4.mijninzet.model.Beschikbaarheid.Week;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    private int active;

    private String roles = "";

    private String permissions = "";

    private String firstName;
    private String lastName;
    private String profilePic;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Week week;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Incident> incidentList;


public User(String username, String password, String roles, String permissions){
    this.username = username;
    this.password = password;
    this.roles = roles;
    this.permissions = permissions;
    this.active = 1;

}

    public User(String username, String password, String roles, String permissions, String firstName, String lastName, String profilePic) {
        this.username = username;
        this.password = password;
        this.active = 1;
        this.roles = roles;
        this.permissions = permissions;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePic = "https://marriedwiki.com/uploads/bio/2014/11/30/thumb/beyonce-knowles-260-260.jpeg";
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    protected User() {
    }

    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() { return firstName + " " + lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", roles='" + roles + '\'' +
                ", permissions='" + permissions + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", incidentList=" + incidentList +
                '}';
    }

//    public void setWeek(Week week) {
//        this.week = week;
//    }
//
//    public Week getWeek() {
//        return week;
//    }
}