package com.laboratory.shared.ddd;

import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractAggregateEntity extends AbstractEntity {
}
