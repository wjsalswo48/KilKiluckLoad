package mz.sixsense.road.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "roadList")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"courseID"})})
public class Course {
    @Id
    @Column(unique = true)
    private String courseID;

    private String total_length;

    private String total_time;

    private String img_path;

    @OneToMany(mappedBy = "courseId", cascade = CascadeType.ALL)
    private List<Road> roadList = new ArrayList<>();
}
