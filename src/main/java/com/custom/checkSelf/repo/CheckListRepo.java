package com.custom.checkSelf.repo;

import com.custom.checkSelf.model.CheckList;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CheckListRepo extends ReactiveCrudRepository<CheckList, String> {
}
