package com.acme.test.vlingo.infrastructure.persistence;

import java.util.Collection;
import io.vlingo.common.Completes;

import com.acme.test.vlingo.infrastructure.ProjectData;

public interface ProjectQueries {
  Completes<ProjectData> projectOf(String id);
  Completes<Collection<ProjectData>> projects();
}