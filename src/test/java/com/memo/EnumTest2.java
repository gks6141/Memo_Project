package com.memo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

public class EnumTest2 {

	public enum CalcType{
		//열거형 정의
		CALC_A(value -> value),
		CALC_B(value -> value * 10),
		CALC_C(value -> value * 3),
		CALC_ETC(value -> 0);
		
		//필드 정의 => enum에 정의된 function
		private Function<Integer, Integer> expression;
		
		// 생성자
		CalcType(Function<Integer, Integer> expression){
			this.expression = expression;
		}
		
		//계산 전용 method
		public int calculate(int value) {
			return expression.apply(value);
		}
	}	
		@Test
		void 계산테스트() {
			//given
			CalcType ctype = CalcType.CALC_B;
			
			// when
			int result = ctype.calculate(500);
			
			//then
			assertEquals(5000, result);
		}
		
	
}
