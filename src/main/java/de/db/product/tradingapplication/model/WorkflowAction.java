package de.db.product.tradingapplication.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
public class WorkflowAction extends BaseEntity {


  @Column(nullable = false)
  private String name;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "action")
  private List<WorkflowActionParameter> parameters;

  @ManyToOne
  @JoinColumn(name = "workflow_id")
  private Workflow workflow;
}