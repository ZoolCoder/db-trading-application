package de.db.product.tradingapplication.repository;

import de.db.product.tradingapplication.model.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * .
 *
 * @author Abdallah Emad.
 * @since 21-9-2023
 */
public interface WorkflowRepository extends JpaRepository<Workflow, Long> {
  @Query("SELECT w FROM Workflow w WHERE w.signalID = :signalID")
  Workflow findBySignalID(int signalID);
}