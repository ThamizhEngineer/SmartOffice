package com.ss.smartoffice.shared.sequence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SequenceRepository extends CrudRepository<Sequence, Integer> {
	List<Sequence>findBySequenceName(String sequenceName);
	List<Sequence>findBySequenceCode(String sequenceCode);
	
}
