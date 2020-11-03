package com.acme.test.vlingo.model.project;

import io.vlingo.common.Completes;

public interface Project {

  Completes<ProjectState> defineProject(final String id, final String name);

  Completes<ProjectState> renameProject(final String id, final String name);

}