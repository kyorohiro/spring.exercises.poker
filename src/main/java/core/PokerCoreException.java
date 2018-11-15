package core;

@SuppressWarnings("serial")
public class PokerCoreException extends Exception {
	private final Type type;

	public PokerCoreException(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return this.type;
	}

	static public enum Type{
		WRONG_CARD_NAME(1);

		private final int code;

		Type(int code) {
			this.code = code;
		}

		public int toCode() {
			return code;
		}
	}

}
