package com.acme.test.vlingo.resource;

import com.acme.test.vlingo.infrastructure.ProjectData;
import com.acme.test.vlingo.infrastructure.persistence.ProjectQueries;
import com.acme.test.vlingo.infrastructure.persistence.ProjectQueriesActor;
import com.acme.test.vlingo.model.project.Project;
import com.acme.test.vlingo.model.project.ProjectEntity;
import io.vlingo.common.Completes;
import io.vlingo.http.Response;
import io.vlingo.xoom.annotation.autodispatch.*;

import static io.vlingo.http.Method.GET;
import static io.vlingo.http.Method.POST;

@AutoDispatch(path="/api", handlers=ProjectResourceHandlers.class)
@Queries(protocol = ProjectQueries.class, actor = ProjectQueriesActor.class)
@Model(protocol = Project.class, actor = ProjectEntity.class, data = ProjectData.class)
public interface ProjectResource {

  @Route(method = POST, path = "/project/{id}/define", handler = ProjectResourceHandlers.DEFINE_PROJECT)
  @ResponseAdapter(handler = ProjectResourceHandlers.ADAPT_STATE)
  Completes<Response> defineProject(@Id final String id, @Body final ProjectData data);

  @Route(method = POST, path = "/project/rename", handler = ProjectResourceHandlers.RENAME_PROJECT)
  @ResponseAdapter(handler = ProjectResourceHandlers.ADAPT_STATE)
  Completes<Response> renameProject(@Id final String id, @Body final ProjectData data);

  @Route(method = GET, path = "/api", handler = ProjectResourceHandlers.PROJECTS)
  Completes<Response> projects();

}