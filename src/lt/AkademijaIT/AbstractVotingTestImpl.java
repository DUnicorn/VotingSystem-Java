package lt.AkademijaIT;



import lt.vtvpmc.java.voting.AbstractVotingTest;
import lt.vtvpmc.java.voting.VoteRegistry;

public class AbstractVotingTestImpl extends AbstractVotingTest{


	@Override
	protected VoteRegistry getVoteRegistry() {
		return new VoteRegistryImpl();
	}

}
