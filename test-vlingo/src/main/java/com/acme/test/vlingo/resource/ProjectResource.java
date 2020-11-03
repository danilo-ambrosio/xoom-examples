package com.acme.test.vlingo.resource;

import io.vlingo.common.Completes;
import io.vlingo.xoom.annotation.autodispatch.*;
import io.vlingo.http.Response;

import com.acme.test.vlingo.infrastructure.persistence.ProjectQueriesActor;
import com.acme.test.vlingo.infrastructure.persistence.ProjectQueries;
import com.acme.test.vlingo.model.project.ProjectEntity;
import com.acme.test.vlingo.model.project.Project;
import com.acme.test.vlingo.infrastructure.ProjectData;

import static io.vlingo.http.Method.*;

@AutoDispatch(path="//", handlers=ProjectResourceHandlers.class)
@Queries(protocol = ProjectQueries.class, actor = ProjectQueriesActor.class)
@Model(protocol = Project.class, actor = ProjectEntity.class, data = ProjectData.class)
public interface ProjectResource {

  @Route(method = POST, path = "project/define", handler = ProjectResourceHandlers.DEFINE_PROJECT)
  @ResponseAdapter(handler = ProjectResourceHandlers.ADAPT_STATE)
  Completes<Response> defineProject(@Body final ProjectData data);

  @Route(method = POST, path = "project/rename", handler = ProjectResourceHandlers.RENAME_PROJECT)
  @ResponseAdapter(handler = ProjectResourceHandlers.ADAPT_STATE)
  Completes<Response> renameProject(@Body final ProjectData data);

  @Route(method = GET, path = "/", handler = ProjectResourceHandlers.PROJECTS)
  Completes<Response> projects();

}