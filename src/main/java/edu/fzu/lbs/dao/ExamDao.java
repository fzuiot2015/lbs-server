package edu.fzu.lbs.dao;

import edu.fzu.lbs.entity.po.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExamDao extends JpaRepository<Exam, Long> {
    Page<Exam> findBySubjectAndType(String subject, String type, Pageable pageable);

    /**
     * 根据科目类别和驾驶证类别随机查询一条符合条件的考题内容
     *
     * @param subject 科目类别
     * @param type    驾驶证类别
     */
    @Query(value = "SELECT * FROM exam WHERE subject=:subject AND type=:type ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Exam get(@Param("subject") String subject, @Param("type") String type);
}
