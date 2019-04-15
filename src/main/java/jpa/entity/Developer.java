package jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Log4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "developers")
public class Developer extends Model {
    @Column(name = "age")
    private Integer age;
    @Column(name = "gender")
    private String gender;
    @Column(name = "salary")
    private Double salary;

    @ManyToMany
    @JoinTable(name = "developer_skill",
            joinColumns = @JoinColumn(name = "developer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id")
    )
    private Set<Skill> skills = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "developers")
    private Set<Project> projects = new HashSet<>();

    @PrePersist
    public void prePersist() {
        log.info("Developer.onPrePersist()");
    }

    @PostPersist
    public void postPersist() {
        log.info("Developer.onPostPersist()");
    }

    @PreRemove
    public void preRemove() {
        log.info("Developer.onPreRemove()");
    }

    @PostRemove
    public void postRemove() {
        log.info("Developer.onPostRemove()");
    }

    @PreUpdate
    public void preUpdate() {
        log.info("Developer.onPreUpdate()");
    }

    @PostUpdate
    public void postUpdate() {
        log.info("Developer.onPostUpdate()");
    }

    @PostLoad
    public void postLoad() {
        log.info("Developer.onPostLoad()");
    }
}
