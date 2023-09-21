package de.db.product.tradingapplication.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import de.db.product.tradingapplication.model.Workflow;
import de.db.product.tradingapplication.repository.WorkflowRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 *
 * @author Abdallah Emad.
 * @since 21-9-2023
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Testcontainers
public class JpaIntegrationTest {

  @Autowired
  private WorkflowRepository workflowRepository;

  @Container
  private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(
      "postgres:latest").withDatabaseName("testdb").withUsername("testuser")
      .withPassword("testpassword");

  @DynamicPropertySource
  static void postgresProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgresContainer::getUsername);
    registry.add("spring.datasource.password", postgresContainer::getPassword);
  }

  @Test
  public void testJpaIntegration() {
    // Given
    Workflow workflow = new Workflow();
    workflow.setActions(null);
    workflow.setSignalID(1);

    // When
    workflowRepository.save(workflow);

    // Then
    Workflow savedWorkflow = workflowRepository.findBySignalID(1);
    assertNotNull(savedWorkflow);
    assertEquals(1, savedWorkflow.getSignalID());
  }
}
