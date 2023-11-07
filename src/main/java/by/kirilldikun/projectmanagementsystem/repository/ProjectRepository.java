package by.kirilldikun.projectmanagementsystem.repository;

import by.kirilldikun.projectmanagementsystem.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Page<Project> findAllByNameContainsIgnoreCase(String name, Pageable pageable);

    @Modifying
    @Query("UPDATE Project p SET p.deleted = true WHERE p.id = :id")
    void softDeleteById(@Param("id") Long id);
}
