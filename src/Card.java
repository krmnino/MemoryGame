
public class Card {
	private int row_pos;
	private int col_pos;
	private int card_number;
	private boolean is_up;
	
	public Card(int row, int col, int number) {
		this.row_pos = row;
		this.col_pos = col;
		this.card_number = number;
		this.is_up = false;
	}
	
	public void set_card_number(int number) {
		this.card_number = number;
	}
	
	public void set_is_up(boolean state) {
		this.is_up = state;
	}
	
	public int get_card_number() {
		return this.card_number;
	}
	
	public boolean get_is_up(){
		return is_up;
	}
	
	public int compare_cards(Card c) {
		if(this.row_pos == c.row_pos && this.col_pos == c.col_pos) 
			return 0; //return 0 if both cards have the same coordinates (same card)
		else if(this.is_up || c.is_up) 
			return 2; // return 2 if any of the cards is facing up
		else if(this.card_number == c.card_number)
			return 1; //return 1 if they are a matching pair
		else 
			return -1; //return -1 if they are not a matching pair
	}
	
	public String toString() {
		if(this.is_up) {
			if (this.card_number < 10) 
				return " " + this.card_number;
			else
				return Integer.toString(this.card_number);
		}
		else 
			return "__";
	}
}
