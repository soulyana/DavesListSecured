package me.soulyana.daveslist.Repositories;

import me.soulyana.daveslist.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
