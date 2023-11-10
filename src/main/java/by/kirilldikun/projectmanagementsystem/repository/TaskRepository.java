package by.kirilldikun.projectmanagementsystem.repository;

import by.kirilldikun.projectmanagementsystem.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findAllByNameContainsIgnoreCase(String name, Pageable pageable);

    @Modifying
    @Query("UPDATE Task t SET t.deleted = true WHERE t.id = :id")
    void softDeleteById(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Task t SET t.deleted = true WHERE t.project.id = :projectId")
    void softDeleteByProjectId(@Param("projectId") Long projectId);
}
