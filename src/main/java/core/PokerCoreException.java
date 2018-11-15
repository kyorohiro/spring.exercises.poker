package core;


/**
* The class {@code PokerCoreException} and its subclasses are a form of
* {@code Exception} that has {@code Type}.
*
* You can determine the reason of Error by {@code Type}. 
*/
@SuppressWarnings("serial")
public class PokerCoreException extends Exception {
	private final Type type;
	private final String description;
	public PokerCoreException(Type type, String description) {
		this.type = type;
		this.description = description;
	}
	
	/**
	 * @return
	 *   return {code {@link Type}} object
	 */
	public Type getType() {
		return this.type;
	}

	/**
	 * @return
	 *   return description
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * {code PokerCoreException} 's reason.
	 *
	 */
	static public enum Type{
		/**
		 * if WrongCardName
		 */
		WRONG_CARD_NAME(1);

		private final int code;

		Type(int code) {
			this.code = code;
		}

		/**
		 * @return
		 * return as integer code.
		 */
		public int toCode() {
			return code;
		}
	}

}
