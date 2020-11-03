package com.acme.test.vlingo.infrastructure.persistence;

import io.vlingo.xoom.annotation.persistence.Persistence;
import io.vlingo.xoom.annotation.persistence.Persistence.StorageType;
import io.vlingo.xoom.annotation.persistence.Projections;
import io.vlingo.xoom.annotation.persistence.Projection;
import io.vlingo.xoom.annotation.persistence.Adapters;
import io.vlingo.xoom.annotation.persistence.EnableQueries;
import io.vlingo.xoom.annotation.persistence.QueriesEntry;
import com.acme.test.vlingo.model.project.ProjectRenamed;
import com.acme.test.vlingo.model.project.ProjectDefined;
import com.acme.test.vlingo.model.project.ProjectState;

@Persistence(basePackage = "com.acme.test.vlingo", storageType = StorageType.JOURNAL, cqrs = true)
@Projections({
  @Projection(actor = ProjectProjectionActor.class, becauseOf = {ProjectDefined.class, ProjectRenamed.class})
})
@Adapters({
  ProjectRenamed.class,
  ProjectDefined.class
})
@EnableQueries({
  @QueriesEntry(protocol = ProjectQueries.class, actor = ProjectQueriesActor.class),
})
public class PersistenceSetup {


}