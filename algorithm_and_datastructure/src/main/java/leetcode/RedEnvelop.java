package leetcode;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

@Getter
@Setter
public class RedEnvelop {
	private Integer totalAmount;
	private Integer totalNum;
	private BigDecimal[] everyEnvelop;
	private DecimalFormat format = new DecimalFormat();
	public RedEnvelop(double totalAmount,int totalNum){
		this.totalAmount = (int) (totalAmount * 100);
		this.totalNum = totalNum;
		everyEnvelop = new BigDecimal[totalNum];
	}

	/** 2倍均值法 **/
	public BigDecimal[] sendEnvelop(){
		int remainAmount = totalAmount;
		int remainNum = totalNum;
		for (int i = 0;i < totalNum;i++){
			if (i == totalNum - 1){
				everyEnvelop[i] = BigDecimal.valueOf((double)remainAmount/100);
				break;
			}
			int value = BigDecimal.valueOf(Math.random() * ((double)remainAmount/remainNum*2)).setScale(0, RoundingMode.HALF_EVEN).intValue();
			if (value == 0)
				value = 1;
			everyEnvelop[i] = BigDecimal.valueOf((double)value/100);
			remainAmount = remainAmount - value;
			remainNum--;
		}
		return everyEnvelop;
	}


	public static void main(String[] args) {

		int times = 10000;
		int n = times;
		int total = 10;
		BigDecimal[] sta = new BigDecimal[total];
		Arrays.fill(sta,BigDecimal.ZERO);
		while(n-- > 0){
			RedEnvelop redEnvelop = new RedEnvelop(50.00, total);
			BigDecimal[] bigDecimals = redEnvelop.sendEnvelop();
			BigDecimal sum = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
			for (int i = 0; i < bigDecimals.length; i++) {
				//System.out.println(bigDecimals[i]);
				sum = sum.add(bigDecimals[i]);
				sta[i] = sta[i].add(bigDecimals[i]);
			}
			if (sum.doubleValue() == (double) redEnvelop.totalAmount / 100) {
			} else throw new IllegalArgumentException();
		}
		for (BigDecimal a : sta){
			System.out.println(a.divide(BigDecimal.valueOf(times)));
		}
	//统计结果表明 ，当红包次数足够多时，10个人，平均每个人能拿到的红包均值是5元，满足每个人公平的概率
	}
}
