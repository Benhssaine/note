package com.note.note.repositories;

import com.note.note.models.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {



    Page<Group> findByNameIgnoreCaseContains(PageRequest of, String name);

    Page<Group> findByNameIgnoreCaseContainsAndUsersId(PageRequest pageRequest, String name, Long userId);

    @Query("SELECT g FROM Group g JOIN g.users u WHERE u.id = :userId")
    Page<Group> findAllByUsersId(Pageable pageable, @Param("userId") Long userId);

}
