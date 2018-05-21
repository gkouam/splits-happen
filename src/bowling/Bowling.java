/**
 * 
 */
package bowling;


public class Bowling {
	/*
	 * No treatment is done on the input data because, it supposed to be correct as the requirement said.
	 */
	
	private String bowlerResult;
	
	public String getBowlerResult() {
		return bowlerResult;
	}

	private int score;
	
	private int roll;

	public Bowling(String bowlerResult) {
		super();
		this.bowlerResult = bowlerResult;
		this.score = 0;
		this.roll = 1;
	}
	
	public int finalScore() {
		calculateScore(bowlerResult, 0);
		return score;
	}

	void calculateScore(String bowlerResult, int index){
		switch (bowlerResult.charAt(index)) {
	      case 'X':
	        //a Strike add 10 to the score
	        score += 10;
	        //here we add scores for the next 2 rolls if it is not the 10th turn
	        if (roll + 1 < bowlerResult.length()) {
	          score += convertScore(bowlerResult.charAt(index + 1), index);
	        }
	        if (roll + 2 < bowlerResult.length() && roll != bowlerResult.length() - 3) {
	          score += convertScore(bowlerResult.charAt(index + 2), index + 1);
	        }
	        break;
	      // if it's a Spare we complete the score for the current turn and add the next roll score
	      case '/':
	        if (index < bowlerResult.length()) {
	          score += 10 - convertScore(bowlerResult.charAt(index - 1));
	          if (roll + 1 != bowlerResult.length()) {
	            score += convertScore(bowlerResult.charAt(index + 1));
	          }
	        }
	        break;
	      // no score for the misses
	      case '-':
	        break;
	      default:
	        this.score += convertScore(bowlerResult.charAt(index));
	        break;
	    }

	    if (roll != bowlerResult.length()) {
	      roll += 1;
	      calculateScore(bowlerResult, index + 1);
	    }
	}
	
	 int convertScore(char score, int spareIndex) {
		    switch (score) {
		      case 'X':
		        return 10;
			case '/':
		        return 10 - convertScore(bowlerResult.charAt(spareIndex));
		      case '-':
		        return 0;
		      default:
		        return Integer.parseInt(Character.toString(score));
		    }
	 }
	 int convertScore(char score) {
		    switch (score) {
		      case 'X':
		        return 10;
		      case '-':
		        return 0;
		      default:
		        return Integer.parseInt(Character.toString(score));
		    }
	 }
	 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Bowling b1 = new Bowling("XXXXXXXXXXXX");
		System.out.println(b1.getBowlerResult()+"     score: "+b1.finalScore());
		
		Bowling b2 = new Bowling("9-9-9-9-9-9-9-9-9-9-");
		System.out.println(b2.getBowlerResult()+"     score: "+b2.finalScore());
		
		Bowling b3 = new Bowling("5/5/5/5/5/5/5/5/5/5/5");
		System.out.println(b3.getBowlerResult()+"     score: "+b3.finalScore());
		
		Bowling b4 = new Bowling("X7/9-X-88/-6XXX81");
		System.out.println(b4.getBowlerResult()+"     score: "+b4.finalScore());

	}

}
