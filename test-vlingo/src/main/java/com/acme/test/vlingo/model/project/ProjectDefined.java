package com.acme.test.vlingo.model.project;

import io.vlingo.lattice.model.DomainEvent;

public final class ProjectDefined extends DomainEvent {

  public final String id;
  public final String name;

  public ProjectDefined(final ProjectState state) {
    this.id = state.id;
    this.name = state.name;
  }
}
