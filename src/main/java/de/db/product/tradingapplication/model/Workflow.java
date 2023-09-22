package de.db.product.tradingapplication.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.cache.annotation.Cacheable;

/**
 * .
 *
 * @author Abdallah Emad.
 * @since 21-9-2023
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Cacheable("workflows")
public class Workflow extends BaseEntity {

  @Column(nullable = false, unique = true)
  private int signalID;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "workflow")
  private List<WorkflowAction> actions;
}