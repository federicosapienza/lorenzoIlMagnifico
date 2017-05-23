package it.polimi.ingsw.GC_26_player;



public enum PlayerStatus {
	WAITINGHISTURN{
		@Override
		public PlayerStatus nextState() {
			return PLAYING;
		}
	},	
	PLAYING {
		@Override
		public PlayerStatus nextState() {
			return WAITINGHISTURN;
		}
	},
	CHOOSINGPAYMENT {
		@Override
		public PlayerStatus nextState() {
			return PLAYING;
		}
	},
	TRADING {
		@Override
		public PlayerStatus nextState() {
			return PLAYING;
		}
	},
	SUSPENDED {  // reached when a player has not permormed an action 
		@Override
		public PlayerStatus nextState() {
			return WAITINGHISTURN;
		}
	};
	
	public abstract PlayerStatus nextState();
}


