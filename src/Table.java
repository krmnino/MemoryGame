import java.util.Random;

public class Table {
	private Card[][] grid;
	private int hidden_cards;
	
	public Table(int rows, int columns) {
		this.grid = new Card[rows][columns];
		this.hidden_cards = rows * columns;
		int[] random_numbers = get_random_numbers(rows, columns);
		int count = 0;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				this.grid[i][j] = new Card(i, j, random_numbers[count]);
				count++;
			}
		}
	}
	
	private int[] get_random_numbers(int rows, int columns) {
		Random rand = new Random();
		int [] numbers = new int[rows * columns];
		int count = 1;
		for(int i = 0; i < rows * columns; i += 2) {
			numbers[i] = count;
			numbers[i+1] = count;
			count++;
		}
		for(int i = 0; i < rows * columns; i++) {
			int random_position = rand.nextInt(rows * columns);
			int temp = numbers[i];
			numbers[i] = numbers[random_position];
			numbers[random_position] = temp;
		}
		return numbers;
	}
	
	private int[] string_to_arr(String coord) {
		int[] coord_arr = new int[2]; //row index 0; column index 1
		int comma_pos = -1;
		for(int i = 0; i < coord.length(); i++) {
			if(coord.charAt(i) == ',') {
				comma_pos = i;
				break;
			}
		}
		if(comma_pos == -1) {
			coord_arr[0] = -1;
			coord_arr[1] = -1;
			return coord_arr;
		}
		else {
			try {
				coord_arr[0] = Integer.parseInt(coord.substring(0, comma_pos)) - 1;
				coord_arr[1] = Integer.parseInt(coord.substring(comma_pos + 1, coord.length())) -1;
			}
			catch(Exception e) {
				System.out.println("Invalid coordinate input. Try again.");
				coord_arr[0] = -1;
				coord_arr[1] = -1;
				return coord_arr;
			}
			return coord_arr;
		}
	}
	
	public void card_comparison(String coords) {
		int esp_pos = -1;
		int[] card1;
		int[] card2;
		for(int i = 0; i < coords.length(); i++) {
			if(coords.charAt(i) == ' ') {
				esp_pos = i;
				break;
			}
		}
		if(esp_pos == -1) {
			System.out.println("Wrong input. Coordinates input format: 1,4 2,5");
			return;
		}
		else {
			card1 = string_to_arr(coords.substring(0, esp_pos));
			card2 = string_to_arr(coords.substring(esp_pos + 1, coords.length()));
			if(card1[0] > this.grid.length || card2[0] > this.grid.length ||
					card1[1] > this.grid[0].length || card2[1] > this.grid[0].length){
				System.out.println("Some coordinates are out of range. Try again with coordiates within the range of the grid.");
				return;
			}
			int compare_cards_result = this.grid[card1[0]][card1[1]].compare_cards(this.grid[card2[0]][card2[1]]);
			switch(compare_cards_result) {
			case(-1):
				System.out.println("Not a matching pair. Try again!");
				this.grid[card1[0]][card1[1]].set_is_up(true);
				this.grid[card2[0]][card2[1]].set_is_up(true);
				System.out.println(this.toString());
				this.grid[card1[0]][card1[1]].set_is_up(false);
				this.grid[card2[0]][card2[1]].set_is_up(false);
				break;
			case(0):
				System.out.println("Coordinates are the same. Try again with different coordinates.");
				break;
			case(1):
				System.out.println("Found a matching pair!");
				this.grid[card1[0]][card1[1]].set_is_up(true);
				this.grid[card2[0]][card2[1]].set_is_up(true);
				this.hidden_cards -= 2;
				break;
			case(2):
				System.out.println("Some of the cards you selected is already facing up. Try again.");
				break;
			default:
				System.exit(1);
			}
		} 
	}
	
	public boolean is_playing() {
		if(this.hidden_cards != 0) 
			return true;
		else 
			return false;
	}
	
	public String toString() {
		String out = ".";
		for(int i = 0; i < this.grid[0].length; i++)
			out += "__.";
		out += "\n|";
		for(int i = 0; i < this.grid.length; i++) {
			for(int j = 0; j < this.grid[i].length; j++) {
				if(j == this.grid[i].length - 1) {
					out += this.grid[i][j].toString();
					if(i != this.grid.length - 1)
						out += "|\n|";
					else
						out += "|";
				}
				else 
					out += this.grid[i][j].toString() + "|";
			}
		}
		return out;
	}
}
