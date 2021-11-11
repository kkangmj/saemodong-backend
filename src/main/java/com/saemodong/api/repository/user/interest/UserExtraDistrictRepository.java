package com.saemodong.api.repository.user.interest;

import com.saemodong.api.model.user.User;
import com.saemodong.api.model.user.interest.UserExtraDistrict;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExtraDistrictRepository extends JpaRepository<UserExtraDistrict, Long> {

  List<UserExtraDistrict> findAllByUser(User user);
}
