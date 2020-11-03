package com.acme.test.vlingo.infrastructure.persistence;

import java.util.ArrayList;
import java.util.Collection;

import io.vlingo.common.Completes;
import io.vlingo.lattice.query.StateStoreQueryActor;
import io.vlingo.symbio.store.state.StateStore;

import com.acme.test.vlingo.infrastructure.ProjectData;

public class ProjectQueriesActor extends StateStoreQueryActor implements ProjectQueries {

  public ProjectQueriesActor(StateStore store) {
    super(store);
  }

  @Override
  public Completes<ProjectData> projectOf(String id) {
    return queryStateFor(id, ProjectData.class, ProjectData.empty());
  }

  @Override
  public Completes<Collection<ProjectData>> projects() {
    return streamAllOf(ProjectData.class, new ArrayList<>());
  }

}
