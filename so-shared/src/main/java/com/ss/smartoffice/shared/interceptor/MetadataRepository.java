package com.ss.smartoffice.shared.interceptor;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.Metadata;

public interface MetadataRepository extends CrudRepository<Metadata, Integer> {
List<Metadata> findByauthUserId(String authUserId);
}
