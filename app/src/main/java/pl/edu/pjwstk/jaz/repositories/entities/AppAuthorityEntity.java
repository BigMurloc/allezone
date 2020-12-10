package pl.edu.pjwstk.jaz.repositories.entities;

import javax.persistence.*;

@Entity
@Table(name = "app_authority")
public class AppAuthorityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String authority;
}
