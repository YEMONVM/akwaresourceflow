package com.akwaresourceflow.repositories;

import com.akwaresourceflow.enums.Role;
import com.akwaresourceflow.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
    ArrayList<AppUser> findByRole(Role role);

    @Query("SELECT au FROM AppUser au JOIN au.employee e WHERE e.station.id = :stationId")
    List<AppUser> findByStationId(@Param("stationId") Long stationId);
}
