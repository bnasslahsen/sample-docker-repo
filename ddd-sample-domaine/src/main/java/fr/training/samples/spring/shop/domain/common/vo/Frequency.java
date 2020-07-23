/**
 *
 */
package fr.training.samples.spring.shop.domain.common.vo;

import java.time.LocalDate;

/**
 *
 */
public enum Frequency {

	MONTHLY {
		@Override
		public LocalDate getNextTranfertDate(final LocalDate lastTransfertDate) {
			return lastTransfertDate.plusMonths(1);
		}
	},
	QUARTERLY {
		@Override
		public LocalDate getNextTranfertDate(final LocalDate lastTransfertDate) {
			return lastTransfertDate.plusMonths(3);
		}
	},
	SEMI_ANNUAL {
		@Override
		public LocalDate getNextTranfertDate(final LocalDate lastTransfertDate) {
			return lastTransfertDate.plusMonths(6);
		}
	},
	ANNUAL {
		@Override
		public LocalDate getNextTranfertDate(final LocalDate lastTransfertDate) {
			return lastTransfertDate.plusYears(1);
		}
	};

	public abstract LocalDate getNextTranfertDate(LocalDate lastTransfertDate);
}
