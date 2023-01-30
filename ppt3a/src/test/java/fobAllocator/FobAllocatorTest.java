package fobAllocator;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

public class FobAllocatorTest {

	@Test
	public void test1() {
		int[] rooms1 = { 0 };
		int[] rooms2 = { 1 };
		int[] locked = { 1 };
		int numdoors = 1;
		List<Door> dungeon = new ArrayList<>();
		for (int i = 0; i < numdoors; i++) {
			boolean lock = (locked[i] == 1);
			dungeon.add(new Door(rooms1[i], rooms2[i], lock));
		}
		assertEquals(1, FobAllocator.numberOfAllocations(dungeon));
	}

	@Test
	public void test2() {
		int[] rooms1 = { 0 };
		int[] rooms2 = { 1 };
		int[] locked = { 0 };
		int numdoors = 1;
		List<Door> dungeon = new ArrayList<>();
		for (int i = 0; i < numdoors; i++) {
			boolean lock = (locked[i] == 1);
			dungeon.add(new Door(rooms1[i], rooms2[i], lock));
		}
		assertEquals(2, FobAllocator.numberOfAllocations(dungeon));

	}

	@Test
	public void test3() {
		int[] rooms1 = {0, 0};
		int[] rooms2 = {1, 2};
		int[] locked = {1, 1};
		int numdoors = 2;
		List<Door> dungeon = new ArrayList<>();
		for (int i = 0; i < numdoors; i++) {
			boolean lock = (locked[i] == 1);
			dungeon.add(new Door(rooms1[i], rooms2[i], lock));
		}
		assertEquals(1, FobAllocator.numberOfAllocations(dungeon));
	}

	@Test
	public void test4() {
		int[] rooms1 = {0, 0, 0, 1, 2, 4, 5, 7, 8};
		int[] rooms2 = {1, 4, 7, 2, 3, 5, 6, 8, 9};
		int[] locked = {1, 1, 1, 0, 1, 1, 1, 1, 0};
		int numdoors = 9;
		List<Door> dungeon = new ArrayList<>();
		for (int i = 0; i < numdoors; i++) {
			boolean lock = (locked[i] == 1);
			dungeon.add(new Door(rooms1[i], rooms2[i], lock));
		}
		assertEquals(8, FobAllocator.numberOfAllocations(dungeon));
	}

	/*@Test
	public void test5() {
		int[] rooms1 = {12, 11, 1, 14, 37, 14, 8, 45, 28, 34, 21, 43, 23, 45, 29, 41, 49, 12, 18, 10, 3, 37, 30, 18, 13, 47, 40, 42, 6, 45, 45, 17, 35, 23, 27, 1, 40, 30, 44, 5, 22, 5, 48, 19, 35, 25, 19, 39, 26};
		int[] rooms2 = {22, 37, 47, 40, 20, 29, 22, 0, 8, 48, 46, 15, 31, 31, 7, 23, 36, 7, 8, 32, 6, 21, 32, 33, 2, 24, 16, 36, 34, 37, 4, 48, 38, 39, 42, 36, 4, 29, 45, 20, 9, 2, 36, 32, 14, 45, 43, 48, 46};
		int[] locked = {1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0};
		int numdoors = 48; // locked.length!
		List<Door> dungeon = new ArrayList<>();
		for (int i = 0; i < numdoors; i++) {
			boolean lock = (locked[i] == 1);
			dungeon.add(new Door(rooms1[i], rooms2[i], lock));
		}
		assertEquals(562949953421312, FobAllocator.numberOfAllocations(dungeon));
	}*/
}
