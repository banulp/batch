package com.banulp.toy.batch;

import java.time.LocalDateTime;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@XmlRootElement(name = "Customer")
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime birthdate;
}