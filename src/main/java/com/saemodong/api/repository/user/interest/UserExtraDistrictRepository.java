package com.saemodong.api.repository.user.interest;

import com.saemodong.api.model.user.interest.UserExtraDistrict;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExtraDistrictRepository
    extends JpaRepository<UserExtraDistrict, Long>, UserInterestBaseRepository<UserExtraDistrict> {}
