package com.acme.test.vlingo.model.project;

public final class ProjectState {

  public final String id;
  public final String name;

  public static ProjectState identifiedBy(final String id) {
    return new ProjectState(id, null);
  }

  public ProjectState (final String id, final String name) {
    this.id = id;
    this.name = name;
  }

  public ProjectState defineProject(final String id, final String name) {
    return new ProjectState(id, name);
  }

  public ProjectState renameProject(final String id, final String name) {
    return new ProjectState(id, name);
  }

}
