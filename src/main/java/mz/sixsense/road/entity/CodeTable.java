package mz.sixsense.road.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeTable {
    @Id
    @Column(unique = true)
    String CodeValueString;

    String CodeName;

}
