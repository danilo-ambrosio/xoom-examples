package com.acme.test.vlingo.resource;

import com.acme.test.vlingo.infrastructure.ProjectData;
import io.vlingo.actors.Logger;
import io.vlingo.actors.Stage;
import io.vlingo.http.resource.Resource;
import io.vlingo.http.resource.ResourceHandler;
import static io.vlingo.http.resource.ResourceBuilder.resource;

import com.acme.test.vlingo.infrastructure.persistence.ProjectQueriesActor;
import com.acme.test.vlingo.infrastructure.persistence.ProjectQueries;
import com.acme.test.vlingo.infrastructure.persistence.QueryModelStateStoreProvider;

import io.vlingo.http.Response;
import io.vlingo.common.Completes;
import static io.vlingo.common.serialization.JsonSerialization.serialized;
import static io.vlingo.http.Response.Status.*;
import static io.vlingo.http.ResponseHeader.*;
import static io.vlingo.http.resource.ResourceBuilder.*;

public class ProjectResource extends ResourceHandler {

  private final Stage $stage;
  private final Logger $logger;
  private final ProjectQueries $queries;

  public ProjectResource(final Stage $stage) {
      this.$stage = $stage;
      this.$logger = $stage.world().defaultLogger();
      this.$queries = QueryModelStateStoreProvider.instance().projectQueries;
  }

  public Completes<Response> defineProject(final ProjectData data) {
    return project.defineProject(data.id, data.name)
      .andThenTo(state -> Completes.withSuccess(Response.of(Created, headers(of(Location, location(state.id))), serialized(ProjectData.from(state))))
      .otherwise(arg -> Response.of(NotFound, location()))
      .recoverFrom(e -> Response.of(InternalServerError, e.getMessage())));
  }

  public Completes<Response> renameProject(final ProjectData data) {
    return project.renameProject(data.id, data.name)
      .andThenTo(state -> Completes.withSuccess(Response.of(Created, headers(of(Location, location(state.id))), serialized(ProjectData.from(state))))
      .otherwise(arg -> Response.of(NotFound, location()))
      .recoverFrom(e -> Response.of(InternalServerError, e.getMessage())));
  }

  public Completes<Response> projects() {
    return $queries.projects()
            .andThenTo(data -> Completes.withSuccess(Response.of(Ok, serialized(data))))
            .otherwise(arg -> Response.of(NotFound, location()))
            .recoverFrom(e -> Response.of(InternalServerError, e.getMessage()));
  }

  @Override
  public Resource<?> routes() {
     return resource("ProjectResource",
        post("//project/define")
            .body(ProjectData.class)
            .handle(this::defineProject),
        post("//project/rename")
            .body(ProjectData.class)
            .handle(this::renameProject),
        get("/")
            .handle(this::projects)
     );
  }

  private String location() {
    return location("");
  }

  private String location(final String id) {
    return "/" + id;
  }


}