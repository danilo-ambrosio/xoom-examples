package com.acme.test.vlingo.model.project;

import io.vlingo.lattice.model.DomainEvent;

public final class ProjectRenamed extends DomainEvent {

  public final String id;
  public final String name;

  public ProjectRenamed(final ProjectState state) {
    this.id = state.id;
    this.name = state.name;
  }
}
