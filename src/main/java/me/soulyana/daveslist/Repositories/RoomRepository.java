package me.soulyana.daveslist.Repositories;

import me.soulyana.daveslist.Models.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Long> {
      Iterable<Room> findAllByIsRented(boolean isRented);
}
