package com.acme.test.vlingo.infrastructure.persistence;

import com.acme.test.vlingo.model.project.ProjectRenamed;

import io.vlingo.common.serialization.JsonSerialization;
import io.vlingo.symbio.BaseEntry.TextEntry;
import io.vlingo.symbio.EntryAdapter;
import io.vlingo.symbio.Metadata;

public final class ProjectRenamedAdapter implements EntryAdapter<ProjectRenamed,TextEntry> {
  
  @Override
  public ProjectRenamed fromEntry(final TextEntry entry) {
    return JsonSerialization.deserialized(entry.entryData(), entry.typed());
  }

  @Override
  public TextEntry toEntry(final ProjectRenamed source, final Metadata metadata) {
    final String serialization = JsonSerialization.serialized(source);
    return new TextEntry(ProjectRenamed.class, 1, serialization, metadata);
  }

  @Override
  public TextEntry toEntry(final ProjectRenamed source, final String id, final Metadata metadata) {
    final String serialization = JsonSerialization.serialized(source);
    return new TextEntry(id, ProjectRenamed.class, 1, serialization, metadata);
  }

  @Override
  public TextEntry toEntry(final ProjectRenamed source, final int version, final String id, final Metadata metadata) {
    final String serialization = JsonSerialization.serialized(source);
    return new TextEntry(id, ProjectRenamed.class, 1, serialization, version, metadata);
  }
}
