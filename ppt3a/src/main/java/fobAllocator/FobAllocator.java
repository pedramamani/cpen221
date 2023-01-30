package fobAllocator;

import java.util.*;

public class FobAllocator {

	
	/**
	 * Count the number of pairings in a string
	 * 
	 * @param dungeon the String to count "pairings" in
	 * @return the number of "pairings" in s
	 */
	public static long numberOfAllocations(List<Door> dungeon) {
		int countFobs = 0;
		int count = 0;
		Set<Door> opennedDoors = new HashSet<>();
		Set<Integer> rooms = new HashSet<>();
		Set<Door> lockedDoors = new HashSet<>();

		for (Door door : dungeon) {
			if (door.locked) {
				lockedDoors.add(door);
			} else {
				opennedDoors.add(door);
			}
			rooms.add(door.room1);
			rooms.add(door.room2);
		}

		for (Set<Integer> fobbed : fobRooms(rooms, new HashSet<>())) {
			if (isSolvable(new HashSet<>(), fobbed, lockedDoors, opennedDoors, countFobs, 0,
					-1, rooms.size(), -1, false, false, true)) {
				count++;
			}
		}
		return count;
	}

	private static Set<Set<Integer>> fobRooms(Set<Integer> rooms, Set<Set<Integer>> perms) {
		if (rooms.size() == 0) {
			return perms;
		}

		for (Integer room : rooms) {
			Set<Set<Integer>> permsCopy = new HashSet<>(perms);
			for (Set<Integer> perm : permsCopy) {
				perm.add(room);
			}
			permsCopy.addAll(perms);
			permsCopy.add(new HashSet<>(room));
			rooms.remove(room);
			return fobRooms(rooms, permsCopy);
		}

		return new HashSet<>();
	}

	private static boolean isSolvable(Set<Integer> circledRooms, Set<Integer> fobbedRooms, Set<Door> lockedDoors, Set<Door> opennedDoors,
									  int countFobs, int currentRoom, int prevRoom, int countRooms, int opennedRoom,
									  boolean gotFob, boolean openDoor, boolean goToRoom) {
		if (currentRoom == countRooms - 1) {
			return true;
		}

		if (gotFob) {
			circledRooms.clear();
			for (Door door : lockedDoors) {
				if (door.room1 == currentRoom) {
					countFobs--;
					opennedDoors.add(door);
					lockedDoors.remove(door);
					opennedRoom = door.room2;
					gotFob = false;
					openDoor = true;
					return isSolvable(circledRooms, fobbedRooms, lockedDoors, opennedDoors, countFobs, currentRoom,
							prevRoom, countRooms, opennedRoom, gotFob, openDoor, goToRoom);
				}
				if (door.room2 == currentRoom) {
					countFobs--;
					opennedDoors.add(door);
					lockedDoors.remove(door);
					opennedRoom = door.room1;
					gotFob = false;
					openDoor = true;
					return isSolvable(circledRooms, fobbedRooms, lockedDoors, opennedDoors, countFobs, currentRoom,
							prevRoom, countRooms, opennedRoom, gotFob, openDoor, goToRoom);
				}
			}
			for (Door door : opennedDoors) {
				if (door.room1 == currentRoom) {
					prevRoom = door.room1;
					currentRoom = door.room2;
					gotFob = false;
					goToRoom = true;
					circledRooms.add(currentRoom);
					circledRooms.add(prevRoom);
					return isSolvable(circledRooms, fobbedRooms, lockedDoors, opennedDoors, countFobs, currentRoom,
							prevRoom, countRooms, opennedRoom, gotFob, openDoor, goToRoom);
				}
				if (door.room2 == currentRoom) {
					prevRoom = door.room2;
					currentRoom = door.room1;
					gotFob = false;
					goToRoom = true;
					circledRooms.add(currentRoom);
					circledRooms.add(prevRoom);
					return isSolvable(circledRooms, fobbedRooms, lockedDoors, opennedDoors, countFobs, currentRoom,
							prevRoom, countRooms, opennedRoom, gotFob, openDoor, goToRoom);
				}
			}
		}

		if (openDoor) {
			circledRooms.clear();
			circledRooms.add(opennedRoom);
			circledRooms.add(currentRoom);
			prevRoom = currentRoom;
			currentRoom = opennedRoom;
			openDoor = false;
			goToRoom = true;
		}

		if (goToRoom) {
			if (countFobs > 0) {
				for (Door door : lockedDoors) {
					if (door.room1 == currentRoom) {
						countFobs--;
						opennedDoors.add(door);
						lockedDoors.remove(door);
						opennedRoom = door.room2;
						goToRoom = false;
						openDoor = true;
						return isSolvable(circledRooms, fobbedRooms, lockedDoors, opennedDoors, countFobs, currentRoom,
								prevRoom, countRooms, opennedRoom, gotFob, openDoor, goToRoom);
					}
					if (door.room2 == currentRoom) {
						countFobs--;
						opennedDoors.add(door);
						lockedDoors.remove(door);
						opennedRoom = door.room1;
						goToRoom = false;
						openDoor = true;
						return isSolvable(circledRooms, fobbedRooms, lockedDoors, opennedDoors, countFobs, currentRoom,
								prevRoom, countRooms, opennedRoom, gotFob, openDoor, goToRoom);
					}
				}
			}
			for (Door door : opennedDoors) {
				if (door.room1 == currentRoom && !circledRooms.contains(door.room2)) {
					prevRoom = door.room1;
					currentRoom = door.room2;
					circledRooms.add(currentRoom);
					return isSolvable(circledRooms, fobbedRooms, lockedDoors, opennedDoors, countFobs, currentRoom,
							prevRoom, countRooms, opennedRoom, gotFob, openDoor, goToRoom);
				}
				if (door.room2 == currentRoom && !circledRooms.contains(door.room1)) {
					prevRoom = door.room2;
					currentRoom = door.room1;
					circledRooms.add(currentRoom);
					return isSolvable(circledRooms, fobbedRooms, lockedDoors, opennedDoors, countFobs, currentRoom,
							prevRoom, countRooms, opennedRoom, gotFob, openDoor, goToRoom);
				}
			}
		}

		return false;
	}

}

class Door {
	public int room1;
	public int room2;
	public boolean locked;

	public Door(int room1, int room2, boolean locked) {
		this.room1  = room1;
		this.room2  = room2;
		this.locked = locked;
	}
}
