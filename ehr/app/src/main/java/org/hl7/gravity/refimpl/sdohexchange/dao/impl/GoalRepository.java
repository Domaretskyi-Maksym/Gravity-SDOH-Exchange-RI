package org.hl7.gravity.refimpl.sdohexchange.dao.impl;

import ca.uhn.fhir.rest.api.Constants;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.StringClientParam;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Goal;
import org.hl7.gravity.refimpl.sdohexchange.dao.FhirRepository;
import org.hl7.gravity.refimpl.sdohexchange.fhir.SDOHProfiles;
import org.springframework.stereotype.Component;

@Component
public class GoalRepository extends FhirRepository<Goal> {

  public GoalRepository(IGenericClient ehrClient) {
    super(ehrClient);
  }

  public Bundle findByPatient(String patientId) {
    return getClient().search()
        .forResource(getResourceType())
        .sort()
        .descending(Constants.PARAM_LASTUPDATED)
        .where(Goal.PATIENT.hasId(patientId))
        .where(new StringClientParam(Constants.PARAM_PROFILE).matches()
            .value(SDOHProfiles.GOAL))
        .returnBundle(Bundle.class)
        .execute();
  }

  @Override
  public Class<Goal> getResourceType() {
    return Goal.class;
  }
}