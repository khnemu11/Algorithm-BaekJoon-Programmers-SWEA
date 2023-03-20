import java.util.ArrayList;

/*
  링크드리스트를 이용해 CRUD하는 예제
*/

class UserSolution {
	ArrayList<ArrayList<Root>> soldiers;
	boolean isHired[];
	int version[];
	int team[];

	public void init() {
		soldiers = new ArrayList<>();
		isHired = new boolean[100001];
		version = new int[100001];
		team = new int[100001];

		for (int team = 0; team <= 5; team++) { // 팀 추가
			soldiers.add(new ArrayList<>());
		}
		for (int team = 1; team <= 5; team++) { // 점수 추가
			for (int score = 0; score <= 5; score++) {
				soldiers.get(team).add(new Root());
			}
		}
	}

	public void hire(int mID, int mTeam, int mScore) {
		version[mID]++;
		Node soldier = new Node(mID, version[mID]);
		soldiers.get(mTeam).get(mScore).tail.next = soldier;
		soldiers.get(mTeam).get(mScore).tail = soldier;
		isHired[mID] = true;
		team[mID] = mTeam;
	}

	public void fire(int mID) {
		isHired[mID] = false;
	}

	public void updateSoldier(int mID, int mScore) {
		hire(mID, team[mID], mScore);
	}

	public void updateTeam(int mTeam, int mChangeScore) {
		if (mChangeScore < 0) {
			for (int score = 1; score <= 5; score++) {
				int nextScore = score + mChangeScore < 1 ? 1 : score + mChangeScore;

				if (nextScore == score) {
					continue;
				}

				if (soldiers.get(mTeam).get(score).head.next == null) {
					continue;
				}

				soldiers.get(mTeam).get(nextScore).tail.next = soldiers.get(mTeam).get(score).head.next;
				soldiers.get(mTeam).get(nextScore).tail = soldiers.get(mTeam).get(score).tail;
				soldiers.get(mTeam).get(score).head.next = null;
				soldiers.get(mTeam).get(score).tail = soldiers.get(mTeam).get(score).head;
			}
		} else if (mChangeScore > 0) {
			for (int score = 5; score >= 1; score--) {
				int nextScore = score + mChangeScore > 5 ? 5 : score + mChangeScore;

				if (nextScore == score) {
					continue;
				}

				if (soldiers.get(mTeam).get(score).head.next == null) {
					continue;
				}

				soldiers.get(mTeam).get(nextScore).tail.next = soldiers.get(mTeam).get(score).head.next;
				soldiers.get(mTeam).get(nextScore).tail = soldiers.get(mTeam).get(score).tail;
				soldiers.get(mTeam).get(score).head.next = null;
				soldiers.get(mTeam).get(score).tail = soldiers.get(mTeam).get(score).head;
			}
		}
	}

	public int bestSoldier(int mTeam) {
		int bestmID = 0;

		for (int score = 5; score >= 1; score--) {
			if (soldiers.get(mTeam).get(score).head.next == null) {
				continue;
			}

			Node curr = soldiers.get(mTeam).get(score).head.next;

			while (curr != null) {
				if (isHired[curr.mID] && curr.version == version[curr.mID]) {
					bestmID = bestmID < curr.mID ? curr.mID : bestmID;
				}
				curr = curr.next;
			}

			if (bestmID != 0) {
				break;
			}
		}

		return bestmID;
	}
}

class Root {
	Node head;
	Node tail;

	public Root() {
		head = new Node(0, 0);
		tail = head;
	}

	@Override
	public String toString() {
		return "Root [head=" + head;
	}

}

class Node {
	int mID;
	int version;
	Node next;

	public Node(int mID, int version) {
		this.version = version;
		this.mID = mID;
		next = null;
	}

	@Override
	public String toString() {
		return "Node [mID=" + mID + ", version=" + version + ", next=" + next + "]";
	}
}
