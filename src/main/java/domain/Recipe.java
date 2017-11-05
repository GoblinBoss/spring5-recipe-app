package domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String direction;
    //todo
    //difficulty
    @Lob
    private Byte[] image;
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

}
