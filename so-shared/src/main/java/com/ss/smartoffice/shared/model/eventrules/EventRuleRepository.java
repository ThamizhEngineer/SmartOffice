package com.ss.smartoffice.shared.model.eventrules;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EventRuleRepository extends CrudRepository<EventRule, Integer> {
List<EventRule> findByEventTypeAndEventCategory(String eventType,String eventCategory);
}
