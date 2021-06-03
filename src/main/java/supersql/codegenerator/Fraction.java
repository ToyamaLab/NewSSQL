package supersql.codegenerator;

/**
 * 分数の値、および計算メソッドを保持するクラス。計算メソッドを利用すると自信の分数の値が書き換わる。(引数の値は書き換わりません)
 *
 */
public class Fraction {
	/**
	 *  分子
	 */
	private int numerator;

	/**
	 *  分母
	 */
	private int denominator;

	/**分数を作る
	 * @param numerator 分子
	 * @param denominator 分母
	 */

	public Fraction(String deco) {
		int[] nums = new int[2];
		String num1 = deco.substring(0,deco.indexOf("/")).trim();
		String num2 = deco.substring(deco.indexOf("/")+1, deco.length()).trim();
		int numerator = Integer.parseInt(num1);
		int denominator = Integer.parseInt(num2);
		//分母がゼロならエラー
		if (denominator == 0) {
			throw new RuntimeException("denominator is not permission 'zero'");
		}
		this.numerator = numerator;
		this.denominator = denominator;

		reduction();
	}

	/**
	 * 約分を行う
	 */
	private void reduction() {
		int gcdi = gcdi(numerator, denominator);
		numerator = numerator / gcdi;
		denominator = denominator / gcdi;
	}

	/**
	 * 引数の分数を加える
	 * @param fraction
	 * @return
	 */
	public void addition(Fraction fraction) {
		numerator = fraction.denominator * numerator + fraction.numerator * denominator;
		denominator *= fraction.denominator;

		reduction();
	}

	/**
	 * 引数の分数を引く
	 * @param fraction 引かれる数
	 * @return
	 */
	public void subtraction(Fraction fraction) {
		numerator = fraction.denominator * numerator - fraction.numerator * denominator;
		denominator *= fraction.denominator;

		reduction();
	}

	/**
	 * 引数の分数を掛ける
	 * @param fraction
	 * @return
	 */
	public void multiplication(Fraction fraction) {
		denominator *= fraction.denominator;
		numerator *= fraction.numerator;

		reduction();
	}

	/**
	 * 引数の分数を割る
	 * @param fraction 割られる数
	 * @return
	 */
	public void division(Fraction fraction) {
		denominator *= fraction.numerator;
		numerator *= fraction.denominator;

		reduction();
	}

	public void divideby(int num) {
		denominator *= num;

		reduction();
	}

	/**
	 * 分子を返す
	 * @return
	 */
	public int getNumerator() {
		return numerator;
	}

	/**
	 * 分母を返す
	 * @return
	 */
	public int getDenominator() {
		return denominator;
	}

	@Override
	public String toString() {
		//分母が1のときは分子だけ返す
//		if (denominator == 1) {
//			return Integer.toString(numerator);
//		}
		return numerator + "/" + denominator;
	}

	/**
	 * 最大公約数を求める
	 * @param a
	 * @param b
	 * @return
	 */
	private static int gcdi(int a, int b) {
		while (b > 0) {
			int c = a;
			a = b;
			b = c % b;
		}
		return a;
	}
}