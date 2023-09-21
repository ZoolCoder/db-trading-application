package de.db.product.tradingapplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class WorkflowActionParameter extends BaseEntity {

  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String type;
  @Column(nullable = false)
  private Integer paramValue;

  @ManyToOne
  @JoinColumn(name = "action_id")
  private WorkflowAction action;
}
