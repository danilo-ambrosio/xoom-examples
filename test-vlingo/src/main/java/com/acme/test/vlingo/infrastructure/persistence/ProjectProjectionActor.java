package com.acme.test.vlingo.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;

import com.acme.test.vlingo.infrastructure.EventTypes;
import com.acme.test.vlingo.infrastructure.ProjectData;

import io.vlingo.lattice.model.DomainEvent;
import io.vlingo.lattice.model.IdentifiedDomainEvent;
import io.vlingo.lattice.model.projection.Projectable;
import io.vlingo.lattice.model.projection.StateStoreProjectionActor;
import io.vlingo.symbio.Entry;

public class ProjectProjectionActor extends StateStoreProjectionActor<ProjectData> {
  private static final ProjectData Empty = ProjectData.empty();

  private String dataId;
  private final List<IdentifiedDomainEvent> events;

  public ProjectProjectionActor() {
    super(QueryModelStateStoreProvider.instance().store);
    this.events = new ArrayList<>(2);
  }

  @Override
  protected ProjectData currentDataFor(final Projectable projectable) {
    return Empty;
  }

  @Override
  protected String dataIdFor(final Projectable projectable) {
    dataId = events.get(0).identity();
    return dataId;
  }

  @Override
  protected ProjectData merge(
      final ProjectData previousData,
      final int previousVersion,
      final ProjectData currentData,
      final int currentVersion) {

    if (previousVersion == currentVersion) {
      return currentData;
    }

    for (final DomainEvent event : events) {
      switch (EventTypes.valueOf(event.typeName())) {
        case ProjectDefined:
          return ProjectData.empty();   // TODO: implement actual merge
        case ProjectRenamed:
          return ProjectData.empty();   // TODO: implement actual merge
      default:
        logger().warn("Event of type " + event.typeName() + " was not matched.");
        break;
      }
    }

    return previousData;
  }

  @Override
  protected void prepareForMergeWith(final Projectable projectable) {
    events.clear();

    for (Entry <?> entry : projectable.entries()) {
      events.add(entryAdapter().anyTypeFromEntry(entry));
    }
  }
}
