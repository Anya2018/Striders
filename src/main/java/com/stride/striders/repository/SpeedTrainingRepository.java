package com.stride.striders.repository;

import com.stride.striders.entity.SpeedTraining;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SpeedTrainingRepository extends CrudRepository<SpeedTraining, Integer> {
}
