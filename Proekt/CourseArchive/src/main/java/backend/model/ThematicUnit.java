package backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "thematic_units")
@Data
public class ThematicUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private UnitType type; // e.g. LECTURE, LAB, SEMINAR...

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "thematicUnit", cascade = CascadeType.ALL)
    private List<Resource> resources;
}
