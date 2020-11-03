package com.acme.test.vlingo.model.project;

import io.vlingo.common.Completes;

import io.vlingo.lattice.model.sourcing.EventSourced;

public final class ProjectEntity extends EventSourced implements Project {
  private ProjectState state;

  public ProjectEntity(final String id) {
    super(id);
    this.state = ProjectState.identifiedBy(id);
  }

  static {
    EventSourced.registerConsumer(ProjectEntity.class, ProjectDefined.class, ProjectEntity::applyProjectDefined);
    EventSourced.registerConsumer(ProjectEntity.class, ProjectRenamed.class, ProjectEntity::applyProjectRenamed);
  }

  public Completes<ProjectState> defineProject(final String id, final String name) {
    return apply(new ProjectDefined(state), () -> state);
  }

  public Completes<ProjectState> renameProject(final String id, final String name) {
    return apply(new ProjectRenamed(state), () -> state);
  }

  private void applyProjectDefined(final ProjectDefined event) {
    //TODO: Handle ProjectDefined here
  }

  private void applyProjectRenamed(final ProjectRenamed event) {
    //TODO: Handle ProjectRenamed here
  }

}
