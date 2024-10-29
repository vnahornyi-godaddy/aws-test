package agentfan.gitlab.io.awstest.products;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue()
    @Column(name = "id", updatable = false)
    private UUID id;

    @Column(name = "name")
    private String name;
}
