package com.laboratory.infrastructure.sequence;


import com.laboratory.shared.ddd.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
class Sequence extends AbstractEntity {

    @Column(nullable = false, updatable = false)
    private String name;

    @Column(nullable = false)
    private long sequence;

    long next() {
        return ++sequence;
    }
}
