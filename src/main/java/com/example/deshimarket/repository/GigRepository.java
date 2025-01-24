package com.example.deshimarket.repository;

import com.example.deshimarket.model.GigModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GigRepository extends JpaRepository<GigModel,Integer> {
    @Query("SELECT g FROM GigModel g WHERE LOWER(g.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(g.keywords) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<GigModel> searchByTitleOrKeywords(@Param("searchTerm") String searchTerm);
   // List<GigModel> findByPost_by(int post_id);
/*    @Query("SELECT gig_table from GigModel gig_table where concat(gig_table.id,'',gig_table.title ,'',gig_table.keywords) like %?1%")
    public List<GigModel> searchAllBy(String keyword);
    GigModel findByTitle(String title);*/
}
