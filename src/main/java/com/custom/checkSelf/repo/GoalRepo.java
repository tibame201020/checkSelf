package com.custom.checkSelf.repo;

import com.custom.checkSelf.model.CheckList;
import com.custom.checkSelf.model.Goal;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface GoalRepo extends ReactiveCrudRepository<Goal, CheckList> {
}
