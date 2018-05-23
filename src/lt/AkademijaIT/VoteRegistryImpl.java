package lt.AkademijaIT;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lt.vtvpmc.java.voting.Vote;
import lt.vtvpmc.java.voting.VoteRegistry;

public class VoteRegistryImpl implements VoteRegistry {

	List<Vote> votes = new ArrayList<>();
	private boolean votingIsRunning = true;

	@Override
	public String getAWinner() {
		if (votes.size() == 0) {
			throw new IllegalStateException();
		} Map<String, Long> candidates = votes.stream()
		.map(v -> v.getVoteCastFor())
		.collect(Collectors.groupingBy(s -> s, Collectors.counting()));
		/*Collectors.counting()
		 * – output is a Collector, acting on a 
		 * Stream of elements of type T, with its 
		 * finisher returning the ‘collected’ value of type Long
		 */
		long maxVotes = 0;
		 int numberOfCandidateWithMaxVotes = 0;
		String winner = "";
		for (Map.Entry<String, Long>candidate : candidates.entrySet()) {
			if(candidate.getValue() > maxVotes) {
				maxVotes = candidate.getValue();
			}
		}
		for (Map.Entry<String, Long> candidate : candidates.entrySet()) {
		if (candidate.getValue() == maxVotes) {
			numberOfCandidateWithMaxVotes++;
			winner = candidate.getKey();
		}
		if (numberOfCandidateWithMaxVotes > 1) {
			throw new IllegalStateException();
		}
		}
		return winner;
	}

	@Override
	public int getNumberOfVotesCast() {
		return votes.size();
	}

	@Override
	public boolean registerVote(Vote vote) {

		if (!votingIsRunning) {
			return false;
		}
		boolean voterVoted = false;

		for (Vote v : votes) {
			if (v.getVoterId().equals(vote.getVoterId())) {
				voterVoted = true;
			}
		}
		if (!voterVoted) {
			votes.add(vote);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void stopVotingProcess() {
		votingIsRunning = false;

	}

}
