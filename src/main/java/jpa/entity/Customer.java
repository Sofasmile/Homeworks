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
@Table(name = "customers")
public class Customer extends Model {

    @Column(name = "age")
    private Integer age;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "customers")
    private Set<Project> projects = new HashSet<>();

    @PrePersist
    public void prePersist() {
        log.info("Customer.onPrePersist()");
    }

    @PostPersist
    public void postPersist() {
        log.info("Customer.onPostPersist()");
    }

    @PreRemove
    public void preRemove() {
        log.info("Customer.onPreRemove()");
    }

    @PostRemove
    public void postRemove() {
        log.info("Customer.onPostRemove()");
    }

    @PreUpdate
    public void preUpdate() {
        log.info("Customer.onPreUpdate()");
    }

    @PostUpdate
    public void postUpdate() {
        log.info("Customer.onPostUpdate()");
    }

    @PostLoad
    public void postLoad() {
        log.info("Customer.onPostLoad()");
    }
}
