package jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company extends Model {

    @Column(name = "address")
    private String address;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "companies")
    private Set<Project> projects = new HashSet<>();

    @PrePersist
    public void prePersist() {
        log.info("Company.onPrePersist()");
    }

    @PostPersist
    public void postPersist() {
        log.info("Company.onPostPersist()");
    }

    @PreRemove
    public void preRemove() {
        log.info("Company.onPreRemove()");
    }

    @PostRemove
    public void postRemove() {
        log.info("Company.onPostRemove()");
    }

    @PreUpdate
    public void preUpdate() {
        log.info("Company.onPreUpdate()");
    }

    @PostUpdate
    public void postUpdate() {
        log.info("Company.onPostUpdate()");
    }

    @PostLoad
    public void postLoad() {
        log.info("Company.onPostLoad()");
    }
}
