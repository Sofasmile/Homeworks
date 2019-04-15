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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "industry")
    private String industry;
    @Column(name = "level")
    private String level;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "skills")
    private Set<Developer> developers = new HashSet<>();

    @PrePersist
    public void prePersist() {
        log.info("Skill.onPrePersist()");
    }

    @PostPersist
    public void postPersist() {
        log.info("Skill.onPostPersist()");
    }

    @PreRemove
    public void preRemove() {
        log.info("Skill.onPreRemove()");
    }

    @PostRemove
    public void postRemove() {
        log.info("Skill.onPostRemove()");
    }

    @PreUpdate
    public void preUpdate() {
        log.info("Skill.onPreUpdate()");
    }

    @PostUpdate
    public void postUpdate() {
        log.info("Skill.onPostUpdate()");
    }

    @PostLoad
    public void postLoad() {
        log.info("Skill.onPostLoad()");
    }
}
