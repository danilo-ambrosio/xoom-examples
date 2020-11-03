package com.acme.test.vlingo.resource;

import com.acme.test.vlingo.infrastructure.ProjectData;
import com.acme.test.vlingo.infrastructure.persistence.ProjectQueries;
import com.acme.test.vlingo.model.project.Project;
import com.acme.test.vlingo.model.project.ProjectState;
import io.vlingo.actors.Stage;
import io.vlingo.common.Completes;
import io.vlingo.xoom.annotation.autodispatch.Handler.Three;
import io.vlingo.xoom.annotation.autodispatch.Handler.Two;
import io.vlingo.xoom.annotation.autodispatch.HandlerEntry;

import java.util.Collection;

public class ProjectResourceHandlers {

  public static final int DEFINE_PROJECT = 0;
  public static final int RENAME_PROJECT = 1;
  public static final int PROJECTS = 2;
  public static final int ADAPT_STATE = 3;

  public static final HandlerEntry<Three<Completes<ProjectState>, Project, ProjectData>> DEFINE_PROJECT_HANDLER =
          HandlerEntry.of(DEFINE_PROJECT, (project, data) -> project.defineProject(data.id, data.name));

  public static final HandlerEntry<Three<Completes<ProjectState>, Project, ProjectData>> RENAME_PROJECT_HANDLER =
          HandlerEntry.of(RENAME_PROJECT, (project, data) -> project.renameProject(data.id, data.name));

  public static final HandlerEntry<Two<ProjectData, ProjectState>> ADAPT_STATE_HANDLER =
          HandlerEntry.of(ADAPT_STATE, ProjectData::from);

  public static final HandlerEntry<Two<Completes<Collection<ProjectData>>, ProjectQueries>> QUERY_ALL_HANDLER =
          HandlerEntry.of(PROJECTS, ProjectQueries::projects);

}