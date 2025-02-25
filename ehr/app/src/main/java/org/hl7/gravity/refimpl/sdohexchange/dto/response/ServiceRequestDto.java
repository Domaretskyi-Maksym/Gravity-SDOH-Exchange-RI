package org.hl7.gravity.refimpl.sdohexchange.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hl7.gravity.refimpl.sdohexchange.dto.Validated;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class ServiceRequestDto implements Validated {

  private final String id;
  private CodingDto category;
  private CodingDto code;
  private OccurrenceResponseDto occurrence;
  private List<ConditionDto> conditions = new ArrayList<>();
  private List<GoalDto> goals = new ArrayList<>();
  private ConsentResponseDto consent;

  @Setter(AccessLevel.NONE)
  private List<String> errors = new ArrayList<>();
}
