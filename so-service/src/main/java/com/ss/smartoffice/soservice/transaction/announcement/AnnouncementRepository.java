package com.ss.smartoffice.soservice.transaction.announcement;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends CrudRepository<Announcement,Integer>{

	List<Announcement> findByAnnouncementCode(String announcementCode);

}
