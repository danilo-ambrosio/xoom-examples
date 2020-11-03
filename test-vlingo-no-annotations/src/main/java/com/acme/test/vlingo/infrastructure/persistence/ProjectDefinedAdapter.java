package com.acme.test.vlingo.infrastructure.persistence;

import com.acme.test.vlingo.model.project.ProjectDefined;

import io.vlingo.common.serialization.JsonSerialization;
import io.vlingo.symbio.BaseEntry.TextEntry;
import io.vlingo.symbio.EntryAdapter;
import io.vlingo.symbio.Metadata;

public final class ProjectDefinedAdapter implements EntryAdapter<ProjectDefined,TextEntry> {
  
  @Override
  public ProjectDefined fromEntry(final TextEntry entry) {
    return JsonSerialization.deserialized(entry.entryData(), entry.typed());
  }

  @Override
  public TextEntry toEntry(final ProjectDefined source, final Metadata metadata) {
    final String serialization = JsonSerialization.serialized(source);
    return new TextEntry(ProjectDefined.class, 1, serialization, metadata);
  }

  @Override
  public TextEntry toEntry(final ProjectDefined source, final String id, final Metadata metadata) {
    final String serialization = JsonSerialization.serialized(source);
    return new TextEntry(id, ProjectDefined.class, 1, serialization, metadata);
  }

  @Override
  public TextEntry toEntry(final ProjectDefined source, final int version, final String id, final Metadata metadata) {
    final String serialization = JsonSerialization.serialized(source);
    return new TextEntry(id, ProjectDefined.class, 1, serialization, version, metadata);
  }
}
