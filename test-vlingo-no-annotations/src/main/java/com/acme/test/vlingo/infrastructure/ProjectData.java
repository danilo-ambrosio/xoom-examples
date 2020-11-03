package com.acme.test.vlingo.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.acme.test.vlingo.model.project.ProjectState;

public class ProjectData {
  public final String id;
  public final String name;

  public static ProjectData from(final ProjectState state) {
    return new ProjectData(state);
  }

  public static List<ProjectData> from(final List<ProjectState> states) {
    return states.stream().map(ProjectData::from).collect(Collectors.toList());
  }

  public static ProjectData empty() {
    return new ProjectData(ProjectState.identifiedBy(null));
  }

  private ProjectData (final ProjectState state) {
    this.id = state.id;
    this.name = state.name;
  }

}
