package org.hl7.gravity.refimpl.sdohexchange.info;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Task;

/**
 * @author Mykhailo Stefantsiv
 */
@Getter
@AllArgsConstructor
public class TaskInfo {

  private final Task task;
  private final ServiceRequestInfo serviceRequestInfo;
  private final Organization owner;
}