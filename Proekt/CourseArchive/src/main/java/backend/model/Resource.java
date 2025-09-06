package backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "resources")
@Data
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String fileType; // pdf, docx, zip, image, etc.
    private String url; // патека каде што е снимено

    @Enumerated(EnumType.STRING)
    private ResourceType resourceType; // TASK, SOLUTION, EXAMPLE, REPOSITORY

    @ManyToOne
    @JoinColumn(name = "thematic_unit_id")
    private ThematicUnit thematicUnit;
}

