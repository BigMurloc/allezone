package pl.edu.pjwstk.jaz.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

// keep it simple
@Entity
@Table(name = "users")
@SequenceGenerator(name = "user_id_sequence", initialValue = 2)
@OnDelete(action = OnDeleteAction.CASCADE)
public class UserEntity {
//todo proper authorities
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @ElementCollection
    @CollectionTable(name = "authorities", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "authorities")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "users", referencedColumnName = "id")
    private Set<String> authorities = new HashSet<>();
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Column(unique = true)
    private String pesel;

    public UserEntity() {
    }

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
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

    public String getFirstName() {
        return firstName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }
}
