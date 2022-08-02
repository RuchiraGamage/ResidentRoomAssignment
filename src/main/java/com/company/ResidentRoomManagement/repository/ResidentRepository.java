package com.company.ResidentRoomManagement.repository;

import com.company.ResidentRoomManagement.model.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Long> {
    List<Resident> findByUserFirstNameContaining(String partOfName);

    /**
     * JPQL based on classes not database tables
     * @param lastname
     * @return
     */
    @Query("select r from Resident r where r.user.lastName = ?1")
    Resident findByUserLastName(String lastname);

    /**
     * run native sql query
     * @param id
     * @return
     */
    @Query(value = "select * from tbl_resident r inner join tbl_user tu on r.user_id = tu.id where tu.id = ?1",
            nativeQuery = true)
    Resident findByUserId(long id);

    /**
     * run native sql query with named params
     * @param id
     * @return
     */
    @Query(value = "select * from tbl_resident r inner join tbl_user tu on r.user_id = tu.id where tu.id = :userId",
            nativeQuery = true)
    Resident findByUserIdWithNativeNamedParam(@Param("userId") long id);

    /**
     * modify annotation use when modify any record in the database and with Transactional annotation we can ensure atomicity
     * @param desc
     * @param residentId
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "update tbl_resident set desc = ?1 where id = ?2  ",
            nativeQuery = true)
    int updateResidentDescById(String desc, long residentId);

}
