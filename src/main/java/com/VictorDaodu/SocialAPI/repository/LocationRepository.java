package com.VictorDaodu.SocialAPI.repository;

import com.VictorDaodu.SocialAPI.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository <Location, Long> {
}
