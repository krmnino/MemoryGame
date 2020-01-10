import java.util.Scanner;

public class Main {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		//
		Scanner keyboard = new Scanner(System.in);
		System.out.print("========== MEMORY GAME! ==========\nEnter the size of the grid:");
		String input = keyboard.nextLine();
		int[] size = get_size(input);
		if(size[0] * size[1] % 2 != 0) {
			System.out.println("There must be an even number of cards.");
			System.exit(1);
		}
		Table game = new Table(size[0], size[1]);
		System.out.println(game.toString());
		while(game.is_playing()) {
			System.out.print("Enter coords: ");
			game.card_comparison(keyboard.nextLine());
			System.out.println(game.toString());
		}
		System.out.println("Game finished! Your found all matching pairs.");
	}
	public static int[] get_size(String input) {
		int[] size = new int[2];
		int x_pos = -1;
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == 'x') {
				x_pos = i;
				break;
			}
		}
		try {
			size[0] = Integer.parseInt(input.substring(0, x_pos));
			size[1] = Integer.parseInt(input.substring(x_pos + 1, input.length()));
		}
		catch(Exception e) {
			System.out.println("The input is invalid");
			System.exit(1);
		}
		return size;
	}
}
